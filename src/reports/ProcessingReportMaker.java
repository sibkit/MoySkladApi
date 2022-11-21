package reports;

import moysklad.configuration.MsConfiguration;
import moysklad.core.MsDataLoader;
import moysklad.core.MsDataSet;
import moysklad.entities.common.MsProduct;
import moysklad.entities.production.MsProcessing;
import moysklad.entities.production.MsProcessingPlan;
import moysklad.entities.production.MsProcessingPositionResult;
import moysklad.core.MsQuery;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class ProcessingReportMaker implements ReportMaker
{

    private List<ReportPart> reportParts = new ArrayList<>();
    private MsDataSet dataSet;

    ReportPart getReportPart(int year, int month)
    {
        for (ReportPart rp : reportParts)
            if (rp.getMonth() == month && rp.getYear() == year)
                return rp;

        ReportPart rp = new ReportPart();
        rp.setMonth(month);
        rp.setYear(year);
        reportParts.add(rp);
        return rp;
    }


    ReportPart getReportPart(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        ReportPart rp = getReportPart(year,month);
        return rp;
    }

    GroupItem getFinishedItem(Date date, String productName, String code, String groupPath)
    {
        ReportPart rp = getReportPart(date);

        for (GroupItem gi : rp.getFinishedItems())
            if (gi.getProductName().equals(productName))
                return gi;
        GroupItem gi = new GroupItem();
        gi.setProductName(productName);
        gi.setQuantity(new BigDecimal(0));
        gi.setCode(code);
        gi.setGroupPath(groupPath);
        rp.getFinishedItems().add(gi);
        return gi;
    }

    GroupItem getSemifinishedItem(Date date, String productName, String code, String groupPath)
    {
        ReportPart rp = getReportPart(date);

        for (GroupItem gi : rp.getSemifinishedItems())
            if (gi.getProductName().equals(productName))
                return gi;
        GroupItem gi = new GroupItem();
        gi.setProductName(productName);
        gi.setQuantity(new BigDecimal(0));
        gi.setCode(code);
        gi.setGroupPath(groupPath);
        rp.getSemifinishedItems().add(gi);
        return gi;
    }

    /*
    MsDataLoader getDataManager()
    {
        MsConfiguration cfg = MsConfiguration.createConfiguration();
        cfg.setAccountId("13a89ca6-38ea-11e9-9109-f8fc00011c68");
        //cfg.setServerApiUrl("https://online.moysklad.ru/api/remap/1.2/entity");
        cfg.setServerApiUrl("https://online.moysklad.ru/api/remap/1.2");
        MsDataLoader dm = new MsDataLoader(cfg, new MsDataSet());
        return dm;
    }
*/


    @Override
    public void initialize(MsDataSet dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public void print() {

        Map<UUID, MsProduct> productsMap = new HashMap<>();
        for(MsProduct p : dataSet.getEntities(MsProduct.class))
            productsMap.put(p.getId(),p);

        Map<UUID, MsProcessingPlan> processingPlansMap = new HashMap<>();
        for(MsProcessingPlan pp : dataSet.getEntities(MsProcessingPlan.class))
            processingPlansMap.put(pp.getId(),pp);

        System.out.println("Загрузка тех.карт...");

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("########.##", dfs);
        df.setGroupingUsed(false);





        for(MsProcessing pc: dataSet.getEntities(MsProcessing.class))
        {
            List<MsProcessingPositionResult> processingResults =dataSet.getEntities(MsProcessingPositionResult.class).stream().filter(pr -> pr.getProcessingId().equals(pc.getId())).toList();

            for(MsProcessingPositionResult ppr : processingResults)
            {
                MsProduct p = productsMap.get(ppr.getProductId());
                MsProcessingPlan pp = processingPlansMap.get(pc.getProcessingPlanId());

                switch (ProductTypeIdentifier.identify(p.getName(), pp.getName()))
                {
                    case FINISHED: {
                        GroupItem fgi = getFinishedItem(pc.getMoment(),p.getName(),p.getCode(), p.getPathName());
                        fgi.setQuantity(fgi.getQuantity().add(ppr.getQuantity()));

                        break;
                    }
                    case SEMIFINISHED: {
                        GroupItem fgi = getSemifinishedItem(pc.getMoment(),p.getName(),p.getCode(), p.getPathName());
                        fgi.setQuantity(fgi.getQuantity().add(ppr.getQuantity()));

                        break;
                    }
                    case REPAIR: {
                        GroupItem ri = getReportPart(pc.getMoment()).getRepairsItems();
                        ri.setQuantity(ri.getQuantity().add(ppr.getQuantity()));
                    }
                }
            }
        }

        String[] months = new String[]{"Январь","Февраль","Март","Апрель","Май","Июнь", "Июль", "Август","Сентябрь","Октябрь","Ноябрь", "Декабрь"};

        for(int y =2019;y<2021;y++)
        {
            for(int m=0;m<=11;m++)
            {
                ReportPart rp = getReportPart(y,m);
                System.out.println(" "+months[m]+" "+y);

                rp.getFinishedItems().sort(new Comparator<GroupItem>() {
                    @Override
                    public int compare(GroupItem o1, GroupItem o2) {
                        return o1.getProductName().compareTo(o2.getProductName());
                    }
                });

                rp.getSemifinishedItems().sort(new Comparator<GroupItem>() {
                    @Override
                    public int compare(GroupItem o1, GroupItem o2) {
                        return o1.getProductName().compareTo(o2.getProductName());
                    }
                });

                for(GroupItem gi : rp.getFinishedItems())
                {
                    //System.out.println(e.getKey()+"&"+df.format(e.getValue())+"&Готовая продукция");
                    System.out.println(gi.getCode()+"&"+gi.getProductName()+"&"+"ГП&"+df.format(gi.getQuantity()));
                }

                for(GroupItem gi : rp.getSemifinishedItems())
                {
                    //System.out.println(e.getKey()+"&"+df.format(e.getValue())+"&Готовая продукция");
                    System.out.println(gi.getCode()+"&"+gi.getProductName()+"&"+"ПФ&"+df.format(gi.getQuantity()));
                }

                System.out.println("-"+"&Ремонты&"+"Ремонты&"+df.format(rp.getRepairsItems().getQuantity()));

            }
        }
    }
}
