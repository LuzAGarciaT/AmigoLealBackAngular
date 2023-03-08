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

import com.example.ElAmigoLeal.Entity.Producto;


public class ListarProductoExcel {
	private XSSFWorkbook producto;
	private XSSFSheet hoja;

	private List<Producto> listaproducto;

	public ListarProductoExcel(List<Producto> listaproducto) {
		this.listaproducto = listaproducto;
		producto = new XSSFWorkbook();
		hoja = producto.createSheet("producto");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = producto.createCellStyle();
	    XSSFFont fuente = producto.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idproducto");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("nombreproducto");
	    celda.setCellStyle(estilo); 
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("precioproducto");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(3);
	    celda.setCellValue("descripcion");
	    celda.setCellStyle(estilo);
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = producto.createCellStyle();
		XSSFFont fuente = producto.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Producto producto: listaproducto) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(producto.getIdproducto());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(producto.getNombreproducto());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(producto.getPrecioproducto());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(producto.getDescripcion());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		producto.write(out);
		
		producto.close();
		return new ByteArrayInputStream(out.toByteArray());
	}

}
