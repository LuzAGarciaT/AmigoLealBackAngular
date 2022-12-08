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

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Impl.CarroCompraService;
import com.example.ElAmigoLeal.Repository.CarroCompraRepository;
import com.example.ElAmigoLeal.Utilities.ListarCarroCompraExcel;

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
public class CarroCompraController {

	@Autowired
	private CarroCompraService carrocompraService;
	
	@GetMapping("/carrocompra")
	public List<CarroCompra> listar() {
		return carrocompraService.findAll();
	}

	// guardar carrocompra
	@PostMapping("/carrocompra/guardar")
	public CarroCompra guardar(@RequestBody CarroCompra carrocompra) {
		return carrocompraService.save(carrocompra);
	}

	@GetMapping("/carrocompra/{idcarro}")
	public CarroCompra getCarroCompra(@PathVariable Integer idcarro) {
		return carrocompraService.findbyId(idcarro);
	}

	// editar carrocompra
	@PutMapping("/carrocompra/{idcarro}")
	public CarroCompra editar(@RequestBody CarroCompra carrocompra, @PathVariable Integer idcarro) {
		CarroCompra carrocompraActual = carrocompraService.findbyId(idcarro);
		carrocompraActual.setUsuario(carrocompra.getUsuario());
		carrocompraActual.setPrecio(carrocompra.getPrecio());
		carrocompraActual.setCantidad(carrocompra.getCantidad());
		carrocompraActual.setCantidadpagar(carrocompra.getCantidadpagar());
		carrocompraActual.setEstado(carrocompra.getEstado());
		
		return carrocompraService.save(carrocompraActual);
	}

	// eliminar carrocompra
	@DeleteMapping("/carrocompra/eliminar/{idcarro}")
	public void eliminar(@PathVariable Integer idcarro) {
		carrocompraService.delete(idcarro);
	}
	
	@GetMapping("/exportarExcelCarro")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=CarroCompra_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<CarroCompra> carrocompra = carrocompraService.findAll();
		
		ListarCarroCompraExcel exporter = new ListarCarroCompraExcel(carrocompra);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfCarroCompra")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(carrocompraService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteCarroCompra.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteCarroCompra.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	@GetMapping("/ExportarGraficaCarroCompra")
	public ResponseEntity<byte[]> generateGrafica() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(carrocompraService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/Grafica/GraficaCarrito.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaCarroCompra.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}