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

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Impl.InventarioService;
import com.example.ElAmigoLeal.Repository.ProductoRepository;
import com.example.ElAmigoLeal.Utilities.ListarInventarioExcel;
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
public class InventarioController {

	@Autowired
	private InventarioService inventarioService;

	@GetMapping("/inventario")
	public List<Inventario> listar() {
		return inventarioService.findAll();
	}

	// guardar inventario
	@PostMapping("/inventario/guardar")
	public Inventario guardar(@RequestBody Inventario inventario) {
		return inventarioService.save(inventario);
	}

	@GetMapping("/inventario/{idinventario}")
	public Inventario getInventario(@PathVariable Integer idinventario) {
		return inventarioService.findbyId(idinventario);
	}

	// editar inventario
	@PutMapping("/inventario/{idinventario}")
	public Inventario editar(@RequestBody Inventario inventario, @PathVariable Integer idinventario) {
		Inventario inventarioActual = inventarioService.findbyId(idinventario);
		inventarioActual.setProducto(inventario.getProducto());
		inventarioActual.setCantidad(inventario.getCantidad());
		return inventarioService.save(inventarioActual);
	}

	// eliminar inventario
	@DeleteMapping("/inventario/eliminar/{idinventario}")
	public void eliminar(@PathVariable Integer idinventario) {
		inventarioService.delete(idinventario);
	}
	
	@GetMapping("/inventario/exportarExcelInventario")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Inventario> listainventario = inventarioService.findAll();
		ListarInventarioExcel excelExportar = new ListarInventarioExcel(listainventario);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listarol.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}

	
	@GetMapping("/ExportarPdfInventario")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(inventarioService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteInvetario.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteInventario.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/ExportarGraficaInventario")
	public ResponseEntity<byte[]> generateGrafica() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(inventarioService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/prueba/Inventario.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaInventario.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
