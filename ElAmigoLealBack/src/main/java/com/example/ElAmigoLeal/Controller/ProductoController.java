package com.example.ElAmigoLeal.Controller;

import java.io.IOException;

import java.io.FileInputStream;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Descuento;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.ProductoService;
import com.example.ElAmigoLeal.Repository.CategoriaRepository;
import com.example.ElAmigoLeal.Repository.DescuentoRepository;
import com.example.ElAmigoLeal.Utilities.ListarProductoExcel;

@Controller
public class ProductoController {
	@Autowired
	private ProductoService productoservice;
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Autowired
	private DescuentoRepository descuentorepository;
	
	
	@GetMapping("/producto")//YA
	public String listarProducto(Model model) {
		model.addAttribute("producto", productoservice.listarTodosLosProductos());
		return "Productos/Producto";
	}
	
	@GetMapping ("/producto/crear")
	public String crearProducto(Model model) {
		Producto producto = new Producto ();
		List<Categoria> listarTodosLosCategorias = categoriarepository.findAll();
		List<Descuento> listarTodosLosDescuentos = descuentorepository.findAll();
		
		model.addAttribute("producto", producto );
		model.addAttribute("listarTodosLosCategorias", listarTodosLosCategorias);
		model.addAttribute("listarTodosLosDescuentos", listarTodosLosDescuentos);
		return "Productos/crear";
	}
	@PostMapping ("/producto")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		productoservice.guardarProducto(producto);
		return "redirect:producto";
	}
	@GetMapping("/producto/editar/{idproducto}")
		public String editarProducto(@PathVariable Integer idproducto, Model model) {
		model.addAttribute("producto", productoservice.obtenerProductobyId(idproducto));
		List<Categoria> listarTodosLosCategorias = categoriarepository.findAll();
		model.addAttribute("listarTodosLosCategorias", listarTodosLosCategorias );
		
		List<Descuento> listarTodosLosDescuentos = descuentorepository.findAll();
		model.addAttribute("listarTodosLosDescuentos", listarTodosLosDescuentos);
			
		return "Productos/editar";
	}
	
	@PostMapping ("/producto/{idproducto}")
	public String actualizarProducto(@PathVariable Integer idproducto, @ModelAttribute("producto") Producto producto , Model model) {
		Producto productoExistente = productoservice.obtenerProductobyId(idproducto);
		productoExistente.setIdproducto(idproducto);
		productoExistente.setCategoria(producto.getCategoria());
		productoExistente.setDescuento(producto.getDescuento());
		productoExistente.setPrecioproducto(producto.getPrecioproducto());
		productoExistente.setNombreproducto(producto.getNombreproducto());
		productoExistente.setDescripcion(producto.getDescripcion());
		
		productoservice.actualizarProducto(productoExistente);
		return "redirect:/producto";
	}
	
	@GetMapping("/producto/{idproducto}")
	public String eliminarProducto(@PathVariable Integer idproducto) {
		productoservice.eliminarProducto(idproducto);
		return "redirect:/producto";
	}
	@GetMapping("/exportarExcelProducto")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Producto_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Producto> producto = productoservice.listarTodosLosProductos();
		
		ListarProductoExcel exporter = new ListarProductoExcel(producto);
		exporter.Exportar(response);
	}
	
	@GetMapping("/ExportarPdfProducto")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(productoservice.listarTodosLosProductos());
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
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(productoservice.listarTodosLosProductos());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/prueba/Producto.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaProducto.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}