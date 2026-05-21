package ui.utils;

import common.config.ConfigReader;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {

    private static final String filePath = ConfigReader.getProperty("excelFilePath");

    private static final String MASTER_SHEET = "MasterData";
    private static final String TESTDATA_SHEET = "TestData";

    public static Map<String, String> getMergedTestData(String testCaseId) {

        Map<String, String> masterData =
                getRowByColumnValue(MASTER_SHEET, "TestCaseID", testCaseId);

        if (masterData == null) {
            return null;
        }

        String testDataKey =
                masterData.getOrDefault("Testdata_Key", "").trim();

        if (testDataKey.isEmpty()) {
            return masterData;
        }

        Map<String, String> testData =
                getRowByColumnValue(TESTDATA_SHEET, "Testdata_Key", testDataKey);

        if (testData != null) {
            masterData.putAll(testData);
        }

        return masterData;
    }

    public static Map<String, String> getRowByColumnValue(
            String sheetName,
            String columnName,
            String expectedValue
    ) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                return null;
            }

            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                return null;
            }

            DataFormatter formatter = new DataFormatter();

            List<String> headers = new ArrayList<>();
            int targetColumnIndex = -1;

            for (int c = 0; c < headerRow.getLastCellNum(); c++) {

                String header =
                        formatter.formatCellValue(headerRow.getCell(c)).trim();

                headers.add(header);

                if (header.equalsIgnoreCase(columnName)) {
                    targetColumnIndex = c;
                }
            }

            if (targetColumnIndex == -1) {
                return null;
            }

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {

                Row row = sheet.getRow(r);

                if (row == null) continue;

                String actualValue =
                        formatter.formatCellValue(
                                row.getCell(targetColumnIndex)
                        ).trim();

                if (actualValue.equalsIgnoreCase(expectedValue)) {

                    Map<String, String> rowData = new HashMap<>();

                    for (int c = 0; c < headers.size(); c++) {

                        String value =
                                formatter.formatCellValue(row.getCell(c)).trim();

                        rowData.put(headers.get(c), value);
                    }

                    return rowData;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}