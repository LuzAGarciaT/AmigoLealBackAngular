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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ElAmigoLeal.Entity.Inventario;
import com.example.ElAmigoLeal.Entity.Producto;
import com.example.ElAmigoLeal.Impl.InventarioService;
import com.example.ElAmigoLeal.Repository.ProductoRepository;
import com.example.ElAmigoLeal.Utilities.ListarInventarioExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Controller
public class InventarioController {

	@Autowired
	private InventarioService inventarioservice;
	
	@Autowired
	private ProductoRepository productorepository;
	
	@GetMapping("/inventario") // YA
	public String listarInventario(Model model) {
		model.addAttribute("inventario", inventarioservice.listarTodosLosInventarios());
		return "Inventarios/Inventario";
	}

	@GetMapping("/inventario/crear")
	public String crearUsuario(Model model) {
		Inventario inventario = new Inventario();
		List<Producto> listarTodosLosProductos = productorepository.findAll();
		
		model.addAttribute("inventario", inventario);
		model.addAttribute("listarTodosLosProductos", listarTodosLosProductos);
		
		return "Inventarios/crear";
	}
	@PostMapping("/inventario")
	public String guardarInventario(@ModelAttribute("inventario") Inventario inventario) {
		inventarioservice.guardarInventario(inventario);
		return "redirect:/inventario";
	}

	@GetMapping("/inventario/editar/{idinventario}")
	public String editarInventario(@PathVariable Integer idinventario, Model model) {
		model.addAttribute("inventario", inventarioservice.obtenerInventariobyId(idinventario));
		List<Producto> listarTodosLosProductos = productorepository.findAll();
		model.addAttribute("listarTodosLosProductos", listarTodosLosProductos );
		return "Inventarios/editar";
	}

	@PostMapping("/inventario/{idinventario}")
	public String actualizarInventario(@PathVariable Integer idinventario, @ModelAttribute("inventario") Inventario inventario, Model model) {
		Inventario inventarioExistente = inventarioservice.obtenerInventariobyId(idinventario);
		inventarioExistente.setIdinventario(idinventario);
		inventarioExistente.setProducto(inventario.getProducto());
		inventarioExistente.setNombreproducto(inventario.getNombreproducto());
		inventarioExistente.setCantidad(inventario.getCantidad());

		
		inventarioservice.actualizarInventario(inventarioExistente);
		return "redirect:/inventario";
	}

	@GetMapping("/inventario/{idinventario}")
	public String eliminarInventario(@PathVariable Integer idinventario) {
		inventarioservice.eliminarInventario(idinventario);
		return "redirect:/inventario";
	}
	
	@GetMapping("/exportarExcelInventario")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Inventario_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Inventario> inventario = inventarioservice.listarTodosLosInventarios();
		
		ListarInventarioExcel exporter = new ListarInventarioExcel(inventario);
		exporter.Exportar(response);
	}
	
	@GetMapping("/ExportarPdfInventario")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(inventarioservice.listarTodosLosInventarios());
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
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(inventarioservice.listarTodosLosInventarios());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/prueba/Inventario.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaInventario.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
