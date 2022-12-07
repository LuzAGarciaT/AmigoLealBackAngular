package com.example.ElAmigoLeal.Utilities;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;

public class ListarDetalleDomicilioExcel {

	private XSSFWorkbook detalledomicilio;
	private XSSFSheet hoja;

	private List<DetalleDomicilio> listadetalledomicilio;

	public ListarDetalleDomicilioExcel(List<DetalleDomicilio> listadetalledomicilio) {
		this.listadetalledomicilio = listadetalledomicilio;
		detalledomicilio = new XSSFWorkbook();
		hoja = detalledomicilio.createSheet("detalledomicilio");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = detalledomicilio.createCellStyle();
	    XSSFFont fuente = detalledomicilio.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("id");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("estado");
	    celda.setCellStyle(estilo);  
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("direccion");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("telefono");
	    celda.setCellStyle(estilo);
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = detalledomicilio.createCellStyle();
		XSSFFont fuente = detalledomicilio.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(DetalleDomicilio detalledomicilio: listadetalledomicilio) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(detalledomicilio.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(detalledomicilio.getEstado());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(detalledomicilio.getDireccion());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(detalledomicilio.getTelefono());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		detalledomicilio.write(outPutSteam);
		
		detalledomicilio.close();
		outPutSteam.close();
	}
}
