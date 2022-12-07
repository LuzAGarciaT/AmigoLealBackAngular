package com.example.ElAmigoLeal.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Impl.DescuentoService;
import com.example.ElAmigoLeal.Utilities.ListarDescuentoExcel;

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
public class DescuentoController {
	@Autowired
	private DescuentoService descuentoService;


// listar
	@GetMapping("/descuento")
	public List<Descuento> listar() {
		return descuentoService.findAll();
	}

	// guardar descuento
	@PostMapping("/descuento/guardar")
	public Descuento guardar(@RequestBody Descuento descuento) {
		return descuentoService.save(descuento);
	}

	@GetMapping("/descuento/{iddescuento}")
	public Descuento getDescuento(@PathVariable Integer iddescuento) {
		return descuentoService.findbyId(iddescuento);
	}

	// editar descuento
	@PutMapping("/descuento/{iddescuento}")
	public Descuento editar(@RequestBody Descuento descuento, @PathVariable Integer iddescuento) {
		Descuento descuentoActual = descuentoService.findbyId(iddescuento);
		descuentoActual.setValordescuento(descuento.getValordescuento()); 
                descuentoActual.setFechadescuento(descuento.getFechadescuento()); 

		return descuentoService.save(descuentoActual);
	}

	// eliminar descuento
	@DeleteMapping("/descuento/eliminar/{iddescuento}")
	public void eliminar(@PathVariable Integer iddescuento) {
		descuentoService.delete(iddescuento);
	}



	@GetMapping("/exportarExcelDescuento")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Descuento_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Descuento> descuento = descuentoService.findAll();
		
		ListarDescuentoExcel exporter = new ListarDescuentoExcel(descuento);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfDescuento")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(descuentoService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteDescuentos.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteDescuento.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
