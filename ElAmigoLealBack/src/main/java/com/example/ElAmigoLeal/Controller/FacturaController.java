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

import com.example.ElAmigoLeal.Entity.Factura;
import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.FacturaService;
import com.example.ElAmigoLeal.Repository.UsuarioRepository;
import com.example.ElAmigoLeal.Utilities.ListarFacturaExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Controller
public class FacturaController {
	@Autowired
	private FacturaService facturaservice;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@GetMapping("/factura")//YA
	public String listarFactura(Model model) {
		model.addAttribute("factura", facturaservice.listarTodasLasFacturas());
		return "Facturas/Factura";
	}
	
	@GetMapping ("/factura/crear")
	public String crearFactura(Model model) {
		Factura factura = new Factura ();
		List<Usuario> listarTodosLosUsuarios = usuariorepository.findAll();
		model.addAttribute("factura", factura );
		model.addAttribute("listarTodosLosUsuarios",listarTodosLosUsuarios);
		return "Facturas/crear";
	}
	@PostMapping ("/factura")
	public String guardarFactura(@ModelAttribute("factura") Factura factura) {
		facturaservice.guardarFactura(factura);
		return "redirect:factura";
	}
	@GetMapping("/factura/editar/{idfactura}")
		public String editarFactura(@PathVariable Integer idfactura, Model model) {
		model.addAttribute("factura", facturaservice.obtenerFacturabyId(idfactura));
		
		List<Usuario> listarTodosLosUsuarios = usuariorepository.findAll();
		model.addAttribute("listarTodosLosUsuarios",listarTodosLosUsuarios);
		return "Facturas/editar";
	}
	
	@PostMapping ("/factura/{idfactura}")
	public String actualizarFactura(@PathVariable Integer idfactura, @ModelAttribute("factura") Factura factura , Model model) {
		Factura facturaExistente = facturaservice.obtenerFacturabyId(idfactura);
		facturaExistente.setIdfactura(idfactura);
		facturaExistente.setUsuario(factura.getUsuario());
		facturaExistente.setNombre(factura.getNombre());
		facturaExistente.setFecha(factura.getFecha());
		facturaExistente.setPreciofact(factura.getPreciofact());
		facturaservice.actualizarFactura(facturaExistente);
		return "redirect:/factura";
	}
	
	@GetMapping("/factura/{idfactura}")
	public String eliminarFactura(@PathVariable Integer idfactura) {
		facturaservice.eliminarFactura(idfactura);
		return "redirect:/factura";
	}
	@GetMapping("/exportarExcelFactura")
	public void exportarListaDeUsuarioExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Factura_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Factura> factura = facturaservice.listarTodasLasFacturas();
		
		ListarFacturaExcel exporter = new ListarFacturaExcel(factura);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfFactura")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(facturaservice.listarTodasLasFacturas());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteFactura.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteFactura.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
