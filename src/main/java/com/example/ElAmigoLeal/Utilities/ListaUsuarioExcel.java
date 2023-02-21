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

import com.example.ElAmigoLeal.Entity.Usuario;

public class ListaUsuarioExcel {
	private XSSFWorkbook usuario;
	private XSSFSheet hoja;

	private List<Usuario> listausuario;

	public ListaUsuarioExcel(List<Usuario> listausuario) {
		this.listausuario = listausuario;
		usuario = new XSSFWorkbook();
		hoja = usuario.createSheet("usuario");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = usuario.createCellStyle();
	    XSSFFont fuente = usuario.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idusuario");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("pnombre");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("snombre");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(3);
	    celda.setCellValue("papellido");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(4);
	    celda.setCellValue("sapellido");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(5);
	    celda.setCellValue("correo");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(6);
	    celda.setCellValue("password");
	    celda.setCellStyle(estilo);
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = usuario.createCellStyle();
		XSSFFont fuente = usuario.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Usuario usuario: listausuario) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(usuario.getIdusuario());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(usuario.getPnombre());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(usuario.getPapellido());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(usuario.getSnombre());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(4);
			celda.setCellValue(usuario.getSapellido());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(5);
			celda.setCellValue(usuario.getCorreo());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(6);
			celda.setCellValue(usuario.getPassword());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	
	public ByteArrayInputStream export() throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		
		usuario.write(out);
		usuario.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
