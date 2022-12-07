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

import com.example.ElAmigoLeal.Entity.Descuento;

public class ListarDescuentoExcel {

	private XSSFWorkbook descuento;
	private XSSFSheet hoja;

	private List<Descuento> listadescuento;

	public ListarDescuentoExcel(List<Descuento> listadescuento) {
		this.listadescuento = listadescuento;
		descuento = new XSSFWorkbook();
		hoja = descuento.createSheet("descuento");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = descuento.createCellStyle();
	    XSSFFont fuente = descuento.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("iddescuento");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("valordescuento");
	    celda.setCellStyle(estilo);  
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("fechadescuento");
	    celda.setCellStyle(estilo); 
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = descuento.createCellStyle();
		XSSFFont fuente = descuento.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Descuento descuento: listadescuento) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(descuento.getIddescuento());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(descuento.getValordescuento());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(descuento.getFechadescuento());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		descuento.write(outPutSteam);
		
		descuento.close();
		outPutSteam.close();
	}
}
