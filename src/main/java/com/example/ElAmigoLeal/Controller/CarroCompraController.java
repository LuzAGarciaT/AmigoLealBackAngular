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

import com.example.ElAmigoLeal.Entity.CarroCompra;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.CarroCompraService;
import com.example.ElAmigoLeal.Repository.UsuarioRepository;
import com.example.ElAmigoLeal.Utilities.ListarCarroCompraExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class CarroCompraController {

	@Autowired
	private CarroCompraService carrocompraservice;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@GetMapping("/carrocompra")//YA
	public String listarCarroCompra(Model model) {
		model.addAttribute("carrocompra", carrocompraservice.listarTodasLosCarroCompra());
		return "CarroCompras/CarroCompra";
	}
	
	@GetMapping ("/carrocompra/crear")
	public String crearCarroCompra(Model model) {
		CarroCompra carrocompra = new CarroCompra ();
		List<Usuario> listarTodosLosUsuarios = usuariorepository.findAll();
		model.addAttribute("carrocompra", carrocompra );
		model.addAttribute("listarTodosLosUsuarios",listarTodosLosUsuarios);
		return "CarroCompras/crear";
	}
	@PostMapping ("/carrocompra")
	public String guardarCarroCompra(@ModelAttribute("carrocompra") CarroCompra carrocompra) {
		carrocompraservice.guardarCarroCompra(carrocompra);
		return "redirect:/carrocompra";
	}
	@GetMapping("/carrocompra/editar/{idcarro}")
		public String editarCarroCompra(@PathVariable Integer idcarro, Model model) {
		model.addAttribute("carrocompra", carrocompraservice.obtenerCarroComprabyId(idcarro));
		List<Usuario> listarTodosLosUsuarios = usuariorepository.findAll();
		model.addAttribute("listarTodosLosUsuarios",listarTodosLosUsuarios);
		return "CarroCompras/editar";
	}
	
	@PostMapping ("/carrocompra/{idcarro}")
	public String actualizarCarroCompra(@PathVariable Integer idcarro, @ModelAttribute("carrocompra") CarroCompra carrocompra , Model model) {
		CarroCompra carrocompraExistente = carrocompraservice.obtenerCarroComprabyId(idcarro);
		carrocompraExistente.setIdcarro(idcarro);
		carrocompraExistente.setUsuario(carrocompra.getUsuario());
		carrocompraExistente.setPrecio(carrocompra.getPrecio());
		carrocompraExistente.setCantidad(carrocompra.getCantidad());
		carrocompraExistente.setCantidadpagar(carrocompra.getCantidadpagar());
		carrocompraExistente.setEstado(carrocompra.getEstado());
		
		carrocompraservice.actualizarCarroCompra(carrocompraExistente);
		return "redirect:/carrocompra";
	}
	@GetMapping("/carrocompra/{idcarro}")
	public String eliminarCarroCompra(@PathVariable Integer idcarro) {
		carrocompraservice.eliminarCarroCompra(idcarro);
		return "redirect:/carrocompra";
	}
	@GetMapping("/exportarExcelCarro")
	public void exportarListaDeRolExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=CarroCompra_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<CarroCompra> carrocompra = carrocompraservice.listarTodasLosCarroCompra();
		
		ListarCarroCompraExcel exporter = new ListarCarroCompraExcel(carrocompra);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfCarroCompra")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(carrocompraservice.listarTodasLosCarroCompra());
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
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(carrocompraservice.listarTodasLosCarroCompra());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/Grafica/GraficaCarrito.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=GraficaCarroCompra.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}