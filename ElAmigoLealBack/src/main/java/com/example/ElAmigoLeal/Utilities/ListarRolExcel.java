package com.example.ElAmigoLeal.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Remote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.ElAmigoLeal.Entity.Rol;

public class ListarRolExcel{

	private XSSFWorkbook rol;
	private XSSFSheet hoja;

	private List<Rol> listarol;

	public ListarRolExcel(List<Rol> listarol) {
		this.listarol = listarol;
		rol = new XSSFWorkbook();
		hoja = rol.createSheet("rol");
	}
	
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = rol.createCellStyle();
	    XSSFFont fuente = rol.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idrol");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("tiporol");
	    celda.setCellStyle(estilo);  
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = rol.createCellStyle();
		XSSFFont fuente = rol.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Rol rol: listarol) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(rol.getIdrol());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(rol.getTiporol());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		rol.write(outPutSteam);
		
		rol.close();
		outPutSteam.close();
	}
}
