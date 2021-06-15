package utility;

import java.util.ArrayList;

public class ExcelDataProvider {

    public Object[][] getDataFromExcel(String path, String sheetName) {
        ExcelReader excelReader = new ExcelReader(path, sheetName);
        int rowCount = excelReader.getRowCount();
        int colCount = excelReader.getColCount();

        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                String cellData = excelReader.getCellDataString(i, j);
                data[i - 1][j] = cellData;
            }
        }
        return data;
    }

    public ArrayList<Object[]> getDataXlsx(String path, String sheetName) {
        ArrayList<Object[]> myData = new ArrayList<Object[]>();
        Xls_Reader reader = new Xls_Reader(path);
        for (int rowNum = 2; rowNum <= reader.getRowCount(sheetName); rowNum++) {
            String username = reader.getCellData(sheetName, "username", rowNum);
            String password = reader.getCellData(sheetName, "password", rowNum);

            Object data[] = {username, password};
            myData.add(data);
        }
        return myData;

    }

    public ArrayList<Object[]> getDataMix(String path, String sheetName) {
        ArrayList<Object[]> myData = new ArrayList<Object[]>();
        ExcelReader excelReader = new ExcelReader(path);
        for (int rowNum = 2; rowNum <= excelReader.getRowCountLastRow(sheetName); rowNum++) {
            String username = excelReader.getCellDataString(sheetName, "username", rowNum);
            String password = excelReader.getCellDataString(sheetName, "password", rowNum);
            Object data[] = {username, password};
            myData.add(data);
        }
        return myData;
    }


}
