package com.example.ElAmigoLeal.Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.ElAmigoLeal.Entity.Factura;

public class ListarFacturaExcel {
	
	private XSSFWorkbook factura;
	private XSSFSheet hoja;

	private List<Factura> listafactura;

	public ListarFacturaExcel(List<Factura> listafactura) {
		this.listafactura = listafactura;
		factura = new XSSFWorkbook();
		hoja = factura.createSheet("factura");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = factura.createCellStyle();
	    XSSFFont fuente = factura.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idfactura");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("nombre");
	    celda.setCellStyle(estilo); 
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("fecha");
	    celda.setCellStyle(estilo); 
	    
	    celda = fila.createCell(3);
	    celda.setCellValue("precio");
	    celda.setCellStyle(estilo); 
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = factura.createCellStyle();
		XSSFFont fuente = factura.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Factura factura: listafactura) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(factura.getIdfactura());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			
			celda = fila.createCell(2);
			celda.setCellValue(factura.getFecha());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(factura.getPreciofact());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		factura.write(out);
		factura.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
