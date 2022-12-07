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

import com.example.ElAmigoLeal.Entity.Domicilio;

public class ListarDomicilioExcel {
	
	private XSSFWorkbook domicilio;
	private XSSFSheet hoja;

	private List<Domicilio> listadomicilio;

	public ListarDomicilioExcel(List<Domicilio> listadomicilio) {
		this.listadomicilio = listadomicilio;
		domicilio = new XSSFWorkbook();
		hoja = domicilio.createSheet("domicilio");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = domicilio.createCellStyle();
	    XSSFFont fuente = domicilio.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("iddomicilio");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("descripcion");
	    celda.setCellStyle(estilo);  
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = domicilio.createCellStyle();
		XSSFFont fuente = domicilio.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Domicilio domicilio: listadomicilio) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(domicilio.getIddomicilio());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(domicilio.getDescripcion());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		domicilio.write(outPutSteam);
		
		domicilio.close();
		outPutSteam.close();
	}
}
