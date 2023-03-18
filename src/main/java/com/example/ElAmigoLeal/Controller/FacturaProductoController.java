package com.example.ElAmigoLeal.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.FacturaProducto;
import com.example.ElAmigoLeal.Impl.FacturaProductoService;
import com.example.ElAmigoLeal.Utilities.ListarFacturaExcel;
import com.example.ElAmigoLeal.Utilities.ListarFacturaProductoExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class FacturaProductoController {

	@Autowired
	private FacturaProductoService facturaproductoService;

	@GetMapping("/facturaproducto")
	public List<FacturaProducto> listar() {
		return facturaproductoService.findAll();
	}

	// guardar factura
	@PostMapping("/facturaproducto/guardar")
	public FacturaProducto guardar(@RequestBody FacturaProducto facturaproducto) {
		return facturaproductoService.save(facturaproducto);
	}

	@GetMapping("/facturaproducto/{id}")
	public FacturaProducto getFacturaProducto(@PathVariable Integer id) {
		return facturaproductoService.findbyId(id);
	}

	// editar factura
	@PutMapping("/facturaproducto/{id}")
	public FacturaProducto editar(@RequestBody FacturaProducto facturaproducto, @PathVariable Integer id) {
		FacturaProducto facturaproductoActual = facturaproductoService.findbyId(id);
		facturaproductoActual.setFactura(facturaproducto.getFactura());
		facturaproductoActual.setProducto(facturaproducto.getProducto());
		facturaproductoActual.setCantidad(facturaproducto.getCantidad());
		facturaproductoActual.setSubtotal(facturaproducto.getSubtotal());
		return facturaproductoService.save(facturaproductoActual);
	}

	// eliminar factura
	@DeleteMapping("/facturaproducto/eliminar/{id}")
	public void eliminar(@PathVariable Integer id) {
		facturaproductoService.delete(id);
	}

	@PostMapping("/facturaproducto/guardartodos")
	public List <FacturaProducto> guardartodos(@RequestBody List <FacturaProducto> facturaProducto) {
		return facturaproductoService.saveAll(facturaProducto);
	}

	
	@GetMapping("/facturaproducto/exportarExcelFacturaP")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<FacturaProducto> listafacturaproducto = facturaproductoService.findAll();
		ListarFacturaProductoExcel excelExportar = new ListarFacturaProductoExcel(listafacturaproducto);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listafacturaproducto.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));	
		
	}
	
	@GetMapping("/facturaproducto/ExportarPdfFacturaP")
	public ResponseEntity<byte[]> generatePdfFacturaP() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(facturaproductoService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/factura.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteFacturaProducto.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}