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

import com.example.ElAmigoLeal.Entity.Permiso;

public class ListarPermisoExcel {
	private XSSFWorkbook permiso;
	private XSSFSheet hoja;

	private List<Permiso> listapermiso;

	public ListarPermisoExcel(List<Permiso> listapermiso) {
		this.listapermiso = listapermiso;
		permiso = new XSSFWorkbook();
		hoja = permiso.createSheet("permiso");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = permiso.createCellStyle();
	    XSSFFont fuente = permiso.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idpermiso");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("descripcion");
	    celda.setCellStyle(estilo);  
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = permiso.createCellStyle();
		XSSFFont fuente = permiso.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Permiso permiso: listapermiso) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(permiso.getIdpermiso());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(permiso.getDescripcion());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		permiso.write(outPutSteam);
		
		permiso.close();
		outPutSteam.close();
	}

}
