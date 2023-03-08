package com.example.ElAmigoLeal.Controller;

import java.io.IOException;
import java.io.ByteArrayInputStream;
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
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.ProductoService;
import com.example.ElAmigoLeal.Repository.CategoriaRepository;
import com.example.ElAmigoLeal.Repository.DescuentoRepository;
import com.example.ElAmigoLeal.Utilities.ListarProductoExcel;
import com.example.ElAmigoLeal.Utilities.ListarRolExcel;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	@Autowired
	private DataSource dataSource;
	
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
		productoActual.setImagen(producto.getImagen());

		return productoService.save(productoActual);
	}

	// eliminar producto
	@DeleteMapping("/producto/eliminar/{idproducto}")
	public void eliminar(@PathVariable Integer idproducto) {
		productoService.delete(idproducto);
	}
	@GetMapping("/producto/exportarExcelProducto")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Producto> listaproducto = productoService.findAll();
		ListarProductoExcel excelExportar = new ListarProductoExcel(listaproducto);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaproducto.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
	}
	
	@GetMapping("/producto/ExportarPdfProducto")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productoService.findAll());
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteProducto.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);
		byte[] data = JasperExportManager.exportReportToPdf(report);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteProducto.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	
	}
	
	@GetMapping("/producto/ExportarGraficaProducto")
	public ResponseEntity<byte[]> generateGrafica() throws Exception, JRException {
		

	    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/GraficaDeProductos.jrxml"));
	    
	    HashMap<String, Object> map=new HashMap<>();
	    JasperPrint report = JasperFillManager.fillReport(compileReport, null, dataSource.getConnection());		    
	    byte[] data = JasperExportManager.exportReportToPdf(report);
	    
	    HttpHeaders headers=new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=Grafica.pdf");
	    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);}
}