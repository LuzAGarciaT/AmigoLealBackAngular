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

import com.example.ElAmigoLeal.Entity.CarroCompra;

public class ListarCarroCompraExcel {

	private XSSFWorkbook carrocompra;
	private XSSFSheet hoja;

	private List<CarroCompra> listacarrocompra;

	public ListarCarroCompraExcel(List<CarroCompra> listacarrocompra) {
		this.listacarrocompra = listacarrocompra;
		carrocompra = new XSSFWorkbook();
		hoja = carrocompra.createSheet("carrocompra");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = carrocompra.createCellStyle();
	    XSSFFont fuente = carrocompra.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idcarro");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("precio");
	    celda.setCellStyle(estilo); 
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("cantidad");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(3);
	    celda.setCellValue("cantidadpagar");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(4);
	    celda.setCellValue("estado");
	    celda.setCellStyle(estilo);
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = carrocompra.createCellStyle();
		XSSFFont fuente = carrocompra.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(CarroCompra carrocompra: listacarrocompra) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(carrocompra.getIdcarro());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(carrocompra.getPrecio());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(carrocompra.getCantidad());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(carrocompra.getCantidadpagar());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(carrocompra.getEstado());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		carrocompra.write(out);
		carrocompra.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
