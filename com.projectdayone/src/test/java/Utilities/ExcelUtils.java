package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static String path;

    /**
     * This method will read excel with fileName AND sheetName then will return Sheet
     *
     * @param fileName
     * @param sheetName
     * @return
     */
    public static Sheet openExcelFile(String fileName, String sheetName) {
        path = "src/test/resources/Data/" + fileName + ".xlsx";
        File file = new File(path);
        try {
            FileInputStream input = new FileInputStream(file);
            workbook = new XSSFWorkbook(input);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(fileName + "excel file does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sheet;
    }

    /**
     * This method will retrun the value in provided rowNum and cellNum in String type
     *
     * @param rowNum
     * @param cellNum
     * @return
     */
    public static String getValue(int rowNum, int cellNum) {
        String value = sheet.getRow(rowNum).getCell(cellNum).toString();
        return value;
    }

    /**
     * This method will set new value for provided row and Cell number
     *
     * @param rowNum
     * @param cellNum
     * @param newValue
     */
    public static void setValue(int rowNum, int cellNum, String newValue) {
        row = sheet.getRow(rowNum);
        cell = row.getCell(cellNum);
        if (cell == null) {
            cell = row.createCell(cellNum);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(newValue);
        } else {
            cell.setCellValue(newValue);
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            System.out.println("file does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method will store all values to list from provided rowNum
     *
     * @param rowNum
     * @return
     */
    public static List<String> getRowValues(int rowNum) {
        List<String> values = new ArrayList<>();
        row = sheet.getRow(rowNum);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            values.add(row.getCell(i).toString());
        }
        return values;
    }
}
