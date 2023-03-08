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

import com.example.ElAmigoLeal.Entity.Categoria;

public class ListarCategoriaExcel {

	private XSSFWorkbook categoria;
	private XSSFSheet hoja;

	private List<Categoria> listacategoria;

	public ListarCategoriaExcel(List<Categoria> listacategoria) {
		this.listacategoria = listacategoria;
		categoria = new XSSFWorkbook();
		hoja = categoria.createSheet("categoria");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = categoria.createCellStyle();
	    XSSFFont fuente = categoria.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idcategoria");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("nombrecategoria");
	    celda.setCellStyle(estilo);  
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = categoria.createCellStyle();
		XSSFFont fuente = categoria.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Categoria categoria: listacategoria) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(categoria.getIdcategoria());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(categoria.getNombrecategoria());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		categoria.write(out);
		categoria.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
}
