package com.gwtsas.prestacarro.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gwtsas.prestacarro.entities.Loan;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	static String[] HEADERS = { "Id", // LOAN
			// Person
			"Nombre", "Numero documento", "Fecha Nacimiento", "Sexo",
			// Loan
			"Fecha Prestamo", "Vehiculo",
			// Return
			"Fecha de devolución" };

	static String SHEET = "Prestamos";

	public static ByteArrayInputStream loansToExcel(List<Loan> loans) {

		// Resource resource = new ClassPathResource("classpath:template.xlsx");
		// System.out.println(resource.toString());

		// InputStream inputStream = resource.getInputStream();
		// FileInputStream excelFile = new FileInputStream(new File(FILE_PATH));
		// Workbook workbook = new XSSFWorkbook(inputStream);

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERS.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERS[col]);
			}
			int rowIdx = 1;
			for (Loan loan : loans) {

				Row row = sheet.createRow(rowIdx++);
				// LOAN
				row.createCell(0).setCellValue(loan.getId());
				row.createCell(5).setCellValue(loan.getRegistrationDate().toString());

				// PERSON
				//row.createCell(1).setCellValue(loan.getRegistrationDate().toString());
				row.createCell(1).setCellValue(loan.getPerson().getFullName());
				row.createCell(2).setCellValue(loan.getPerson().getDocumentNumber());
				row.createCell(3).setCellValue(loan.getPerson().getBirthDate());
				row.createCell(4).setCellValue(loan.getPerson().getSex());

				// ACTIVE
				row.createCell(6).setCellValue(loan.getActive().getInternalCode());

				// RETURN
				try {
					row.createCell(7).setCellValue(loan.getReturnObject().getRegistrationDate().toString());
				}
				catch(NullPointerException exception) {
					row.createCell(7).setCellValue("N/D");
				}
				
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Cannot export data to Excel file: " + e.getMessage());
		}
	}
}