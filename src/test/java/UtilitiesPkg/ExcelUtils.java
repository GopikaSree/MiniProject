package UtilitiesPkg;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getTestData(
        String filepath,
        String sheetname
    ) throws IOException {

        try (FileInputStream fis = new FileInputStream(filepath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetname);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows][cols];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= rows; i++) {

                for (int j = 0; j < cols; j++) {

                    data[i - 1][j] = formatter.formatCellValue(
                        sheet.getRow(i).getCell(j)
                    );
                }
            }

            return data;
        }
    }
}
