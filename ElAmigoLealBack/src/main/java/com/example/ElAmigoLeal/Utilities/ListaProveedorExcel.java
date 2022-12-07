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

import com.example.ElAmigoLeal.Entity.Proveedor;

public class ListaProveedorExcel {
	private XSSFWorkbook proveedor;
	private XSSFSheet hoja;

	private List<Proveedor> listaproveedor;

	public ListaProveedorExcel(List<Proveedor> listaproveedor) {
		this.listaproveedor = listaproveedor;
		proveedor = new XSSFWorkbook();
		hoja = proveedor.createSheet("rol");
	}
    
	private void escribircabzeradetabla() {
	    Row fila = hoja.createRow(0);
	    
	    CellStyle estilo = proveedor.createCellStyle();
	    XSSFFont fuente = proveedor.createFont();
	    fuente.setBold(true);
	    fuente.setFontHeight(16);
	    estilo.setFont(fuente);
	    
	    Cell celda = fila.createCell(0);
	    celda.setCellValue("idproveedor");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(1);
	    celda.setCellValue("nombre");
	    celda.setCellStyle(estilo);  
	    
	    celda = fila.createCell(2);
	    celda.setCellValue("telefono");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(3);
	    celda.setCellValue("correo");
	    celda.setCellStyle(estilo);
	    
	    celda = fila.createCell(4);
	    celda.setCellValue("direccion");
	    celda.setCellStyle(estilo);
	}
	
	private void escribirDatoDeTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = proveedor.createCellStyle();
		XSSFFont fuente = proveedor.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Proveedor proveedor: listaproveedor) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(proveedor.getIdproveedor());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(proveedor.getNombre());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(proveedor.getTelefono());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(3);
			celda.setCellValue(proveedor.getCorreo());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(4);
			celda.setCellValue(proveedor.getDireccion());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
		}
	}
	public void Exportar(HttpServletResponse response) throws IOException {
		escribircabzeradetabla();
		escribirDatoDeTabla();
		
		ServletOutputStream outPutSteam = response.getOutputStream();
		proveedor.write(outPutSteam);
		
		proveedor.close();
		outPutSteam.close();
	}
}
