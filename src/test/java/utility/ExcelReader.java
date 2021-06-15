package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public String path;
    public String sheetName;
    public FileInputStream fis;
    public Workbook book;
    public XSSFWorkbook wb = null;
    public Sheet sheet1;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;

    public ExcelReader() {
    }

    public ExcelReader(String path) {
        this.path = path;
        File src = new File(path);
        try {
            fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelReader(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
        File src = new File(path);
        try {
            fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // get data excellfile b
    public Object[][] getData(String path, String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet1 = book.getSheet(sheetName);
        Object data[][] = new Object[sheet1.getLastRowNum()][sheet1.getRow(0).getLastCellNum()];
        for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
            for (int j = 0; j < sheet1.getRow(0).getLastCellNum(); j++)
            {
                data[i-1][j] = sheet1.getRow(i).getCell(j).toString();
            }
        }
        return data;
    }

    public int getRowCountLastRow(String sheetName)
    {
        int index = wb.getSheetIndex(sheetName);
        if(index==-1)
        {
            return 0;
        }
        else {
            sheet = wb.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }
    }


    //Get number of rows in file xlsx
    public int getRowCount() {
        int rowCount = 0;
        try {
            rowCount = sheet.getPhysicalNumberOfRows();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rowCount;
    }

    //Get number of column in file xlsx
    public int getColCount() {
        int colCount = 0;
        try {
            colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println("number of sheet is : " + colCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return colCount;
    }

    // get data from file xlsx but only 1 specified row
/*    public String getStringData(int sheetIndex, int row, int column) {
        return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
    }

    public String getStringData(String sheetName, int row, int column) {
        return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }

    public Double getNumeric(String sheetName, int row, int column) {
        return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
    }*/

    public String getCellDataString(int rowNum, int colNum) {
        String cellData = null;
        try {
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cellData;
    }

    public String getCellDataString(String sheetName, String colName,int rowNum) {
        FileInputStream file = null;
        int index = wb.getSheetIndex(sheetName);
        int col_Num = -1;
        try {
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(index);
        row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            // System.out.println(row.getCell(i).getStringCellValue().trim());
            if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                col_Num = i;
        }
        if (col_Num == -1)
            return "";

        sheet = wb.getSheetAt(index);
        row = sheet.getRow(rowNum - 1);
        cell = row.getCell(col_Num);
        return cell.getStringCellValue();
    }

}
