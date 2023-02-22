package com.example.ElAmigoLeal.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Impl.FacturaService;
import com.example.ElAmigoLeal.Repository.FacturaRepository;
import com.example.ElAmigoLeal.Utilities.ListarFacturaExcel;
import com.example.ElAmigoLeal.Utilities.ListarRolExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping(path = "/api")
public class FacturaController {
	@Autowired
	private FacturaService facturaService;
	
	@GetMapping("/factura")
	public List<Factura> listar() {
		return facturaService.findAll();
	}

	// guardar factura
	@PostMapping("/factura/guardar")
	public Factura guardar(@RequestBody Factura factura) {
		return facturaService.save(factura);
	}

	@GetMapping("/factura/{idfactura}")
	public Factura getFactura(@PathVariable Integer idfactura) {
		return facturaService.findbyId(idfactura);
	}

	// editar factura
	@PutMapping("/factura/{idfactura}")
	public Factura editar(@RequestBody Factura factura, @PathVariable Integer idfactura) {
		Factura facturaActual = facturaService.findbyId(idfactura);
		facturaActual.setUsuario(factura.getUsuario());
		facturaActual.setFecha(factura.getFecha());
		facturaActual.setPreciofact(factura.getPreciofact());
		return facturaService.save(facturaActual);
	}
	
	// eliminar factura
		@DeleteMapping("/factura/eliminar/{idfactura}")
		public void eliminar(@PathVariable Integer idfactura) {
			facturaService.delete(idfactura);
		}

	@GetMapping("/factura/exportarExcelFactura")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Factura> listafactura = facturaService.findAll();
		ListarFacturaExcel excelExportar = new ListarFacturaExcel(listafactura);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listafactura.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));	
		
	}
	
	@GetMapping("/ExportarPdfFactura")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(facturaService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteFactura.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteFactura.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
