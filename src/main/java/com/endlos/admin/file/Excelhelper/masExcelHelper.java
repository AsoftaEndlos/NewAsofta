package com.endlos.admin.file.Excelhelper;

import com.endlos.admin.file.model.Masterdatamodel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class masExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"ITEM_id", "BARCODE", "MATERIAL_TYPE", "MATERIAL", "ITEM_DESCRIPTION", "ITEM_VOLUME",
            "ITEM_WEIGHT", "ITEM _VALUE", "DATA_ACQUISITION"};
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {
        System.out.println("excel");
        return TYPE.equals(file.getContentType());
    }

    /*
     * public static ByteArrayInputStream tutorialsToExcel(List<Masterdatamodel>
     * tutorials) {
     *
     * try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new
     * ByteArrayOutputStream();) { Sheet sheet = workbook.createSheet(SHEET);
     *
     * // Header Row headerRow = sheet.createRow(0);
     *
     * for (int col = 0; col < HEADERs.length; col++) { Cell cell =
     * headerRow.createCell(col); cell.setCellValue(HEADERs[col]); }
     *
     * int rowIdx = 1; for (Masterdatamodel tutorial : tutorials) { Row row =
     * sheet.createRow(rowIdx++); System.out.println(tutorial);
     * row.createCell(0).setCellValue(tutorial.getId());
     * row.createCell(1).setCellValue(tutorial.getBarcode());
     * row.createCell(2).setCellValue(tutorial.getMaterialtype());
     * row.createCell(3).setCellValue(tutorial.getMaterial());
     * row.createCell(4).setCellValue(tutorial.getItemdescription());
     * row.createCell(5).setCellValue(tutorial.getItemvolume());
     * row.createCell(6).setCellValue(tutorial.getItemweight());
     * row.createCell(7).setCellValue(tutorial.getItemvalue());
     * row.createCell(8).setCellValue(tutorial.getDataacquisition()); }
     *
     * workbook.write(out); return new ByteArrayInputStream(out.toByteArray()); }
     * catch (IOException e) { throw new
     * RuntimeException("fail to import data to Excel file: " + e.getMessage()); } }
     */
    public static List<Masterdatamodel> excelToTutorials(InputStream is) {
        try {
            System.out.println("enter try catch");
            XSSFWorkbook workbook = new XSSFWorkbook(is);    //XSSFWorkbook(is);
            System.out.println("hello workbook");
            Sheet sheet = workbook.getSheet(SHEET);//getSheetAt(0);
            System.out.println("mastersheet" + masExcelHelper.SHEET);
            if (sheet == null) {
                sheet = workbook.createSheet("Tutorials");
                for (int sn = 0; sn < workbook.getNumberOfSheets(); sn++) {
                    System.out.println("Sheet masterdata " + sn + " is called " + workbook.getSheetName(sn));
                }
            }
            System.out.println("shheet assigned");
            Iterator<Row> rows = sheet.rowIterator();
            System.out.println("complete");

            List<Masterdatamodel> tutorials = new ArrayList<Masterdatamodel>();
            System.out.println(tutorials);
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                System.out.println("current row" + currentRow);
                // skip header
                if (rowNumber == 0) {
                    System.out.print("row number " + rowNumber);
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Masterdatamodel tutorial = new Masterdatamodel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
//          "ITEM_ID", "BARCODE", "MATERIAL_TYPE", "MATERIAL", "ITEM_DESCRIPTION", "ITEM_VOLUME",
//			"ITEM_WEIGHT", "ITEM _VALUE", "DATA_ACQUISITION"
                    switch (cellIdx) {
                        case 0:
                            tutorial.setItemid((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            tutorial.setBarcode((long) currentCell.getNumericCellValue());
                            break;

                        case 2:
                            tutorial.setMaterialtype((int) currentCell.getNumericCellValue());
                            break;

                        case 3:
                            tutorial.setMaterial(currentCell.getStringCellValue());
                            break;

                        case 4:
                            tutorial.setItemdescription(currentCell.getStringCellValue());
                            break;

                        case 5:
                            tutorial.setItemvolume((int) currentCell.getNumericCellValue());
                            break;

                        case 6:
                            tutorial.setItemweight((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            tutorial.setItemvalue((float) currentCell.getNumericCellValue());
                        case 8:
                            tutorial.setDataacquisition((int) currentCell.getNumericCellValue());

                        default:
                            break;
                    }

                    cellIdx++;
                }

                tutorials.add(tutorial);
            }

            workbook.close();

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
