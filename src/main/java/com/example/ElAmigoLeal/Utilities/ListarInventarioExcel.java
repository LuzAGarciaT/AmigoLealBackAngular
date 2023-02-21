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

import com.example.ElAmigoLeal.Entity.Inventario;


public class ListarInventarioExcel {
	private XSSFWorkbook inventario;
	private XSSFSheet hoja;

	private List<Inventario> listainventario;

	public ListarInventarioExcel(List<Inventario> listainventario) {
		this.listainventario = listainventario;
		inventario = new XSSFWorkbook();
		hoja = inventario.createSheet("inventario");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = inventario.createCellStyle();
	    XSSFFont fuente = inventario.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idinventario");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("nombreproducto");
	    celda.setCellStyle(estilo);  
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("cantidad");
	    celda.setCellStyle(estilo); 
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = inventario.createCellStyle();
		XSSFFont fuente = inventario.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Inventario inventario: listainventario) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(inventario.getIdinventario());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(inventario.getNombreProducto());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(inventario.getCantidad());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		inventario.write(outPutSteam);
		
		inventario.close();
		outPutSteam.close();
	}

}
