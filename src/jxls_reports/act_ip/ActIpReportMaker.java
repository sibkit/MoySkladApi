package jxls_reports.act_ip;

import jxls_reports.JxlsReportMaker;
import jxls_reports.MoneyToWords;
import moysklad.core.MsDataSet;
import moysklad.entities.common.MsProduct;
import moysklad.entities.products.MsMove;
import moysklad.entities.products.MsMovePosition;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ActIpReportMaker extends JxlsReportMaker {

    BigDecimal getPrice(String productName) {
        return switch (productName) {
            case "БП 90W 1600mA [AC-220] Ledmaster.Imp" -> new BigDecimal("498.32");
            case "Заготовка термодатчика N146/147 v.1 (Тайвань)" -> new BigDecimal("38.58");
            case "Блок BZ-003 без корпуса" -> new BigDecimal("52.13");
            case "SMD BR-600" -> new BigDecimal("22.33");
            case "Светильник светодиодный BR-6041" -> new BigDecimal("38.15");
            case "Светодиодная плата M-45-N v.3.7 (CREE XTE 12шт.)" -> new BigDecimal("480.74");
            case "BR-600 Лампа светодиодная" -> new BigDecimal("29.10");
            case "Светодиодная плата F-30-W v.1.0 (OSRAM DURIS S5 -28in1)" -> new BigDecimal("368.19");
            case "Заготовка БП 90W 190mA [AC 220] Ledmaster.FRS без корпуса" -> new BigDecimal("220.20");
            case "Заготовка БП 30W 190mA [AC 220] Ledmaster.FRS [без диммирования/без корпуса]" -> new BigDecimal("660.60");
            case "Печатная плата B-146/147Ni" -> new BigDecimal("200.15");
            case "Заготовка блока управления B-130 без корпуса" -> new BigDecimal("286.62");
            default -> new BigDecimal("183.74");
        };
    }

    MsDataSet dataSet;
    public  void initialize(MsDataSet dataSet) {
        this.dataSet = dataSet;
    }

    private List<ActIpItem> generateItems(MsDataSet dataSet, Date startDate, Date endDate) {
        Map<UUID, MsProduct> productsMap = new HashMap<>();
        for(MsProduct p : dataSet.getEntities(MsProduct.class))
            productsMap.put(p.getId(),p);

        UUID ipfStoreId = UUID.fromString("6088bc78-7af9-11eb-0a80-098e0000c0d8");

        List<UUID> actualMoveIds = new ArrayList<>();
        for(MsMove move : dataSet.getEntities(MsMove.class)) {
            if (!((move.getMoment().after(startDate) || move.getMoment().equals(startDate)) &&
                    (move.getMoment().before(endDate)||move.getMoment().equals(endDate)))) continue;
            if (move.getSourceStoreId().equals(ipfStoreId))
                actualMoveIds.add(move.getId());
        }
        Map<UUID, ActIpItem> itemsMapByProductId = new HashMap<>();
        for(MsMovePosition mp : dataSet.getEntities(MsMovePosition.class)) {

            if(actualMoveIds.contains(mp.getMoveId())) {
                if(!itemsMapByProductId.containsKey(mp.getProductId())) {
                    //dataSet.getEntities(MsProduct.class).
                    ActIpItem item = new ActIpItem();
                    item.setQty(mp.getQuantity());
                    var product = productsMap.get(mp.getProductId());
                    item.setName(product.getName());
                    item.setPrice(getPrice(product.getName()));
                    itemsMapByProductId.put(product.getId(),item);
                }
                else
                {
                    var item = itemsMapByProductId.get(mp.getProductId());
                    item.setQty(item.getQty().add(mp.getQuantity()));
                }
            }
        }

        var itemNumber =1;
        List<ActIpItem> actItems = new ArrayList<>();
        for(ActIpItem item: itemsMapByProductId.values())
        {
            item.setNumber(itemNumber);
            item.setSum(item.getPrice().multiply(item.getQty()));
            actItems.add(item);
            itemNumber++;
        }
        return  actItems;
    }

    public  void makeReport(Date startDate, Date endDate) throws Exception {
        var actItems = generateItems(dataSet, startDate, endDate);
        BigDecimal actSum = new BigDecimal(0);
        int servicesCount = 0;
        for(ActIpItem item: actItems){
            actSum = actSum.add(item.getSum());
            servicesCount+=item.getQty().intValue();
        }


        try(InputStream is = new FileInputStream("act_ip_template.xlsx")) {
            try (OutputStream os = new FileOutputStream("act_ip_output.xlsx")) {

                Transformer transformer = PoiTransformer.createTransformer(is, os);
                XlsCommentAreaBuilder.addCommandMapping("autoSize", AutoSizeCommand.class);
                AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
                List<Area> xlsAreas = areaBuilder.build();
                Area xlsArea = xlsAreas.get(0);


                Context context = new Context();
                context.putVar("act_items", actItems);
                context.putVar("act_date", "31.11.2022");
                context.putVar("act_sum",actSum.doubleValue());
                context.putVar("services_count", actItems.size());
                var sumInWords = MoneyToWords.inwords(actSum.doubleValue());
                sumInWords = sumInWords.substring(0,1).toUpperCase()+sumInWords.substring(1);
                context.putVar("act_sum_in_words",sumInWords);
                xlsArea.applyAt(new CellRef("Стр1!A1"), context);
                context.getConfig().setIsFormulaProcessingRequired(false);

                ((PoiTransformer) transformer).getWorkbook().write(os);

                //context.putVar("employees", employees);
                //JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
