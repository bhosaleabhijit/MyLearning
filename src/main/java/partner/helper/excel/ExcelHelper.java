package partner.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import partner.helper.logger.LoggerHelper;
import partner.helper.resource.ResourceHelper;

public class ExcelHelper {

	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);

	public Object[][] getExcelData(String fileLocation, String sheetName) {
		Object[][] data = null;
		FileInputStream file = null;
		XSSFWorkbook workbook = null;
		int rowCount, colCount, rowNum = 0, colNum = 0;
		Cell cell;
		Row row;

		try {
			file = new FileInputStream(new File(fileLocation));
			workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			rowCount = sheet.getLastRowNum();
			colCount = sheet.getRow(0).getLastCellNum();

			// System.out.println("rowCount & colCount are: " + rowCount + " " +
			// colCount);

			data = new Object[rowCount + 1][colCount];

			Iterator<Row> rowIterator = sheet.rowIterator();
			Iterator<Cell> cellIterator;

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				colNum = 0;

				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					switch (cell.getCellTypeEnum()) {
					case STRING:
						data[rowNum][colNum] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[rowNum][colNum] = cell.getNumericCellValue();
						break;

					case BOOLEAN:
						data[rowNum][colNum] = cell.getBooleanCellValue();
						break;

					case FORMULA:
						data[rowNum][colNum] = cell.getCellFormula();
						break;

					case BLANK:
					case _NONE:
						data[rowNum][colNum] = "";
						break;

					default:
						System.out.println("No matching cell data found in sheet: " + sheetName + " row: " + rowNum
								+ " col: " + colNum);
						log.error("No matching cell data found in sheet: " + sheetName + " row: " + rowNum + " col: "
								+ colNum);
						break;

					}
					++colNum;

				}
				++rowNum;

			}

		} catch (IOException e) {
			log.info("Error while getting Excel file data: " + fileLocation + " " + sheetName + ": " + e.getMessage());
			System.out.println("Exception while getting excel data: " + sheetName);
			e.printStackTrace();
		} finally {
			try {
				if (file != null)
					file.close();

				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				// do nothing
			}
		}

		return data;

	}

	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus) {
		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();

			for (int row = 1; row <= rowCount; row++) {
				XSSFRow curRow = sheet.getRow(row);
				String testCase = curRow.getCell(0).getStringCellValue();

				if (testCase.equals(testCaseName)) {
					curRow.createCell(1).setCellValue(testStatus);
					file.close();

					log.info(testCase + ": result updated with : " + testStatus);

					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					workbook.close();
					break;
				}
			}

		} catch (FileNotFoundException e) {
			log.error("Cannot find file: " + excelLocation);
			System.out.println("Cannot find file: " + excelLocation);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Error while reading/writing a file: " + excelLocation);
			System.out.println(" Error while reading/writing a file: " + excelLocation);
			e.printStackTrace();
		} catch(Exception e)
		{
			System.out.println("Some exception in Update Result: " + e.getMessage());
			log.error("Some exception in Update Result: " + e.getMessage());
		}

	}

	public static void main(String[] args) {
		ExcelHelper helper = new ExcelHelper();
		Object data[][] = helper.getExcelData(ResourceHelper.getResourcePath("/src/main/resources/TestData.xlsx"),
				"Logins");
		
		helper.updateResult(ResourceHelper.getResourcePath("/src/main/resources/TestData.xlsx"), "TestCaseStatus", "Test1", "pass");
		helper.updateResult(ResourceHelper.getResourcePath("/src/main/resources/TestData.xlsx"), "TestCaseStatus", "Test2", "fail");
		helper.updateResult(ResourceHelper.getResourcePath("/src/main/resources/TestData.xlsx"), "TestCaseStatus", "Test3", "skip");

		for (Object[] obj : data) {
			for (Object o : obj) {
				try {
					if (!(o == null))
						System.out.print(o.toString() + " ");
					else
						System.out.print("Empty");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}

			}
			System.out.println("");
		}
	}

}
