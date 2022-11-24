package jxls_reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.Size;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRowImpl;

import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActIpReportMaker {
    public static void makeReport() throws Exception {
        var str = MoneyToWords.inwords(4567456.75);
        String s1 = str.substring(0, 1).toUpperCase();
        var str2 = s1 + str.substring(1);
        System.out.println(str2);
        System.out.println("--ok--");


        List<Employee> employees = generateSampleEmployeeData();
        try(InputStream is = new FileInputStream("D:\\temp\\test_autosize.xlsx")) {
            try (OutputStream os = new FileOutputStream("D:\\temp\\test_autosize_output_2.xlsx")) {
                Transformer transformer = PoiTransformer.createTransformer(is, os);
                XlsCommentAreaBuilder.addCommandMapping("autoSize", AutoSizeCommand.class);
                AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
                List<Area> xlsAreas = areaBuilder.build();
                Area xlsArea = xlsAreas.get(0);


                Context context = new Context();

                context.putVar("test", "very very very long string which should to be wrapped");
                xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
                context.getConfig().setIsFormulaProcessingRequired(false);
                ((PoiTransformer) transformer).getWorkbook().write(os);

                //context.putVar("employees", employees);
                //JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Employee> generateSampleEmployeeData() throws ParseException {
        SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
        List<Employee> result = new ArrayList<>();
        for(int i=0;i<10;i++) {
            Employee e = new Employee();
            e.setName("Employee of employment factory "+i);

            e.setBirthDate(sm.parse("10-1"+i+"-1982"));
            e.setBonus(BigDecimal.valueOf(i));
            e.setPayment(BigDecimal.valueOf(i*10));
            result.add(e);
        }

        return result;
    }

    public static class AutoSizeCommand extends AbstractCommand {
        private Area area;

        @Override
        public String getName() {
            return "autoSize";
        }

        @Override
        public Size applyAt(CellRef cellRef, Context context) {
            Size size = area.applyAt(cellRef, context);

            PoiTransformer transformer = (PoiTransformer) area.getTransformer();
            Workbook workbook = transformer.getWorkbook();
            Row row = workbook.getSheet(cellRef.getSheetName()).getRow(cellRef.getRow());
            row.setHeight((short) -1);
            removeDyDescentAttr(row);
            Cell cell = row.getCell(cellRef.getCol());
            cell.getCellStyle().setWrapText(true);
            return size;
        }

        @Override
        public Command addArea(Area area) {
            super.addArea(area);
            this.area = area;
            return this;
        }

        private void removeDyDescentAttr(Row row) {
            if (row instanceof XSSFRow) {
                XSSFRow xssfRow = (XSSFRow) row;
                CTRowImpl ctRow = (CTRowImpl) xssfRow.getCTRow();
                QName dyDescent = new QName("http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac");
                if (ctRow.get_store().find_attribute_user(dyDescent) != null) {
                    ctRow.get_store().remove_attribute(dyDescent);
                }
            } else {
                System.out.println("This method applicable only for xlsx-templates");
            }
        }
    }
}
