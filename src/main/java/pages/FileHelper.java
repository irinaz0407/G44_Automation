package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.getProperty;

public class FileHelper {

    private final static Logger log = LogManager.getLogger("File helper");

    @Step("Read test data from file")
    public static Collection<String[]> readTestDataFromFile() {
        String filePath = getProperty("user.dir") + "\\src\\test\\java\\data\\";
        Collection<String[]> result = new ArrayList<String[]>();
        try {
            if (!getProperty("useexcel").equals("true")) {
                log.debug("Test data source is file");

                for (String s : Files.readAllLines(Paths.get(filePath + "testdata.txt"))) {
                    result.add(s.split(";"));
                    log.debug("Line from file");
                    log.debug(Arrays.toString(s.split(";")));
                }
                return result;

                //, StandardCharsets.ISO_8859_1);
            } else {
                log.debug("useexcel = " + getProperty("useexcel"));
                log.debug("Test data source is excel sheet");
                XSSFWorkbook excelWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath + "testdata.xlsx")));
                XSSFSheet excelSheet1 = excelWorkbook.getSheet("Sheet1");
                String[] tmpStringList = new String[3];
                for (int n = 0; n < excelSheet1.getLastRowNum(); n++) {
                    log.debug("Excel read row " + n);
                    tmpStringList[0] = excelSheet1.getRow(n).getCell(0).getStringCellValue();
                    tmpStringList[1] = excelSheet1.getRow(n).getCell(1).getStringCellValue();
                    tmpStringList[2] = excelSheet1.getRow(n).getCell(2).getStringCellValue();
                    result.add(tmpStringList);
                    log.debug(Arrays.toString(tmpStringList));
                }
                return result;

            }
        } catch (IOException e) {
            log.error(e);
            return null;
        }

    }

    private List<String[]> excelLog = new ArrayList<String[]>();

    @Step("Add item to test log")
    public void addTestResult(String dateTime, String testName, String testResult) {
        String[] logRow = new String[3];
        logRow[0] = dateTime;
        logRow[1] = testName;
        logRow[2] = testResult;
        excelLog.add(logRow);
    }

    @Step("Adding test log to excel file")
    public void addRowsToExcel() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        try {
            String filePath = getProperty("user.dir") + "\\src\\test\\java\\data\\";
            XSSFWorkbook excelWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath + "testresult.xlsx")));
            XSSFSheet excelSheet1 = excelWorkbook.getSheet("Result");
            int rowNum = excelSheet1.getLastRowNum() + 1;
            for (String[] sa : excelLog) {
                Row excelRow = excelSheet1.createRow(rowNum++);
                int cellNum = 0;
                for (String s : sa) {
                    Cell excelCell = excelRow.createCell(cellNum++);
                    excelCell.setCellValue(s);
                }
            }
            FileOutputStream fileOut = new FileOutputStream(filePath + "testresult" + dateFormat.format(Calendar.getInstance().getTime()) + ".xlsx");
            excelWorkbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (IOException e) {
            log.error(e);
        }
    }
}

