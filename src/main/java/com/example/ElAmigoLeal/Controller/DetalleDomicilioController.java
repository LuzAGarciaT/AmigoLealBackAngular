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

import com.example.ElAmigoLeal.Entity.DetalleDomicilio;
import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Impl.DetalleDomicilioService;
import com.example.ElAmigoLeal.Repository.DomicilioRepository;
import com.example.ElAmigoLeal.Utilities.ListarDetalleDomicilioExcel;
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
public class DetalleDomicilioController {

	@Autowired
	private DetalleDomicilioService detalledomicilioService;
	
	@GetMapping("/detalledomicilio")
	public List<DetalleDomicilio> listar() {
		return detalledomicilioService.findAll();
	}

	// guardar domicilio
	@PostMapping("/detalledomicilio/guardar")
	public DetalleDomicilio guardar(@RequestBody DetalleDomicilio detalledomicilio) {
		return detalledomicilioService.save(detalledomicilio);
	}

	@GetMapping("/detalledomicilio/{id}")
	public DetalleDomicilio getDetalleDomicilio(@PathVariable Integer id) {
		return detalledomicilioService.findbyId(id);
	}

	// editar domicilio
	@PutMapping("/detalledomicilio/{id}")
	public DetalleDomicilio editar(@RequestBody DetalleDomicilio detalledomicilio, @PathVariable Integer id) {
		DetalleDomicilio detalledomicilioActual = detalledomicilioService.findbyId(id);
		detalledomicilioActual.setDomicilio(detalledomicilio.getDomicilio());
		detalledomicilioActual.setEstado(detalledomicilio.getEstado());
		detalledomicilioActual.setDireccion(detalledomicilio.getDireccion());
		detalledomicilioActual.setTelefono(detalledomicilio.getTelefono());
		
		
		return detalledomicilioService.save(detalledomicilioActual);
	}

	// eliminar domicilio
	@DeleteMapping("/detalledomicilio/eliminar/{id}")
	public void eliminar(@PathVariable Integer id) {
		detalledomicilioService.delete(id);
	}
	@GetMapping("/detalledomicilio/exportarExcelDetalle")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<DetalleDomicilio> listadetalledomicilio=detalledomicilioService.findAll();
		ListarDetalleDomicilioExcel excelExportar = new ListarDetalleDomicilioExcel(listadetalledomicilio);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listadetalledomicilio.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}

	
	@GetMapping("/ExportarPdfDetalle")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(detalledomicilioService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteDetalleDomicilio.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteDetalleDomicilio.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
}
