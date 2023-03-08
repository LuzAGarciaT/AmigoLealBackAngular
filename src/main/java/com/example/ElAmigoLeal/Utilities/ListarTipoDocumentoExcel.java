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

import com.example.ElAmigoLeal.Entity.TipoDocumento;

public class ListarTipoDocumentoExcel {
	private XSSFWorkbook tipodocumento;
	private XSSFSheet hoja;

	private List<TipoDocumento> listatipodocumento;

	public ListarTipoDocumentoExcel(List<TipoDocumento> listatipodocumento) {
		this.listatipodocumento = listatipodocumento;
		tipodocumento = new XSSFWorkbook();
		hoja = tipodocumento.createSheet("tipodocumento");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = tipodocumento.createCellStyle();
	    XSSFFont fuente = tipodocumento.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("iddoc");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("tipodoc");
	    celda.setCellStyle(estilo);  
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = tipodocumento.createCellStyle();
		XSSFFont fuente = tipodocumento.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(TipoDocumento tipodocumento: listatipodocumento) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(tipodocumento.getIddoc());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(tipodocumento.getTipodoc());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		tipodocumento.write(out);
		tipodocumento.close();

		return new ByteArrayInputStream(out.toByteArray());

	}

}
