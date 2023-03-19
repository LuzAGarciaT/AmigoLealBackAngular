package com.example.ElAmigoLeal.Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.FacturaProducto;

public class ListarFacturaProductoExcel {
	private XSSFWorkbook facturaproducto;
	private XSSFSheet hoja;

	private List<FacturaProducto> listafacturaproducto;

	public ListarFacturaProductoExcel(List<FacturaProducto> listafacturaproducto) {
		this.listafacturaproducto = listafacturaproducto;
		facturaproducto = new XSSFWorkbook();
		hoja = facturaproducto.createSheet("facturaproducto");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = facturaproducto.createCellStyle();
	    XSSFFont fuente = facturaproducto.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("id");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("cantidad");
	    celda.setCellStyle(estilo); 
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("subtotal");
	    celda.setCellStyle(estilo); 
	    
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = facturaproducto.createCellStyle();
		XSSFFont fuente = facturaproducto.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(FacturaProducto facturaproducto: listafacturaproducto) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(facturaproducto.getID());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			
			celda = fila.createCell(1);
			celda.setCellValue(facturaproducto.getCantidad());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(facturaproducto.getSubtotal());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		facturaproducto.write(out);
		facturaproducto.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}



