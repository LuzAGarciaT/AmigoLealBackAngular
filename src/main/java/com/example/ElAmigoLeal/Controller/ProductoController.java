package com.example.ElAmigoLeal.Controller;

import java.io.IOException;

import java.io.FileInputStream;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.ProductoService;
import com.example.ElAmigoLeal.Repository.CategoriaRepository;
import com.example.ElAmigoLeal.Repository.DescuentoRepository;
import com.example.ElAmigoLeal.Utilities.ListarProductoExcel;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/producto")
	public List<Producto> listar() {
		return productoService.findAll();
	}

	// guardar producto
	@PostMapping("/producto/guardar")
	public Producto guardar(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@GetMapping("/producto/{idproducto}")
	public Producto getProducto(@PathVariable Integer idproducto) {
		return productoService.finallById(idproducto);
	}

	// editar producto
	@PutMapping("/producto/{idproducto}")
	public Producto editar(@RequestBody Producto producto, @PathVariable Integer idproducto) {
		Producto productoActual = productoService.finallById(idproducto);
		productoActual.setNombreproducto(producto.getNombreproducto());
		productoActual.setCategoria(producto.getCategoria());
		productoActual.setDescuento(producto.getDescuento());
		productoActual.setPrecioproducto(producto.getPrecioproducto());
		productoActual.setDescripcion(producto.getDescripcion());

		return productoService.save(productoActual);
	}

	// eliminar producto
	@DeleteMapping("/producto/eliminar/{idproducto}")
	public void eliminar(@PathVariable Integer idproducto) {
		productoService.delete(idproducto);
	}
	@GetMapping("/exportarExcelProducto")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Producto_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Producto> producto = productoService.findAll();
		
		ListarProductoExcel exporter = new ListarProductoExcel(producto);
		exporter.Exportar(response);
	}
	
	@GetMapping("/ExportarPdfProducto")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(productoService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteProducto.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteProducto.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/ExportarGraficaProducto")
	public ResponseEntity<byte[]> generateGrafica() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(productoService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/prueba/Producto.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaProducto.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}