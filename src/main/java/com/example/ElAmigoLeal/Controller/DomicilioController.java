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

import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Impl.DomicilioService;
import com.example.ElAmigoLeal.Repository.DomicilioRepository;
import com.example.ElAmigoLeal.Utilities.ListarDomicilioExcel;
import com.example.ElAmigoLeal.Utilities.ListarRolExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class DomicilioController {
	
	@Autowired
	private DomicilioService domicilioService;
	
	@GetMapping("/domicilio")
	public List<Domicilio> listar() {
		return domicilioService.findAll();
	}

	// guardar domicilio
	@PostMapping("/domicilio/guardar")
	public Domicilio guardar(@RequestBody Domicilio domicilio) {
		return domicilioService.save(domicilio);
	}

	@GetMapping("/domicilio/{iddomicilio}")
	public Domicilio getDomicilio(@PathVariable Integer iddomicilio) {
		return domicilioService.findbyId(iddomicilio);
	}

	// editar domicilio
	@PutMapping("/domicilio/{iddomicilio}")
	public Domicilio editar(@RequestBody Domicilio domicilio, @PathVariable Integer iddomicilio) {
		Domicilio domicilioActual = domicilioService.findbyId(iddomicilio);
		domicilioActual.setDescripcion(domicilio.getDescripcion());
		domicilioActual.setCarrocompra(domicilio.getCarrocompra());
		
		
		return domicilioService.save(domicilioActual);
	}

	// eliminar domicilio
	@DeleteMapping("/domicilio/eliminar/{iddomicilio}")
	public void eliminar(@PathVariable Integer iddomicilio) {
		domicilioService.delete(iddomicilio);
	}
	
	
	@GetMapping("/domicilio/exportarExcelDomicilio")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Domicilio> listadomicilio= domicilioService.findAll();
		ListarDomicilioExcel excelExportar = new ListarDomicilioExcel(listadomicilio);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listadomicilio.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}
	@GetMapping("/ExportarPdfDomicilio")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(domicilioService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteDomicilio.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteDomicilio.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
