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
import com.example.ElAmigoLeal.Entity.Domicilio;
import com.example.ElAmigoLeal.Impl.DomicilioService;
import com.example.ElAmigoLeal.Repository.CarroCompraRepository;
import com.example.ElAmigoLeal.Utilities.ListarDomicilioExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class DomicilioController {
	@Autowired
	private DomicilioService domicilioservice;
	
	@Autowired
	private CarroCompraRepository carrocomprarepository;
	
	@GetMapping("/domicilio")//YA
	public String listarDomicilio(Model model) {
		model.addAttribute("domicilio", domicilioservice.listarTodosLosDomicilios());
		return "Domicilios/Domicilio";
	}
	
	@GetMapping ("/domicilio/crear")
	public String crearDomicilio(Model model) {
		Domicilio domicilio = new Domicilio ();
		 List<CarroCompra> listarTodasLosCarroCompra = carrocomprarepository.findAll();
		 
		model.addAttribute("domicilio", domicilio );
		model.addAttribute("listarTodasLosCarroCompra", listarTodasLosCarroCompra);
		return "Domicilios/crear";
	}
	@PostMapping ("/domicilio")
	public String guardarDomicilio(@ModelAttribute("domicilio") Domicilio domicilio) {
		domicilioservice.guardarDomicilio(domicilio);
		return "redirect:domicilio";
	}
	@GetMapping("/domicilio/editar/{iddomicilio}")
		public String editarDomicilio(@PathVariable Integer iddomicilio, Model model) {
		model.addAttribute("domicilio", domicilioservice.obtenerDomiciliobyId(iddomicilio));
		
		 List<CarroCompra> listarTodasLosCarroCompra = carrocomprarepository.findAll();
		 model.addAttribute("listarTodasLosCarroCompra", listarTodasLosCarroCompra);
			
		 return "Domicilios/editar";
	}
	
	@PostMapping ("/domicilio/{iddomicilio}")
	public String actualizarDomicilio(@PathVariable Integer iddomicilio, @ModelAttribute("domicilio") Domicilio domicilio , Model model) {
		Domicilio domicilioExistente = domicilioservice.obtenerDomiciliobyId(iddomicilio);
		domicilioExistente.setIddomicilio(iddomicilio);
		domicilioExistente.setCarrocompra(domicilio.getCarrocompra());
		domicilioExistente.setDescripcion(domicilio.getDescripcion());
		
		domicilioservice.actualizarDomicilio(domicilioExistente);
		return "redirect:/domicilio";
	}
	
	@GetMapping("/domicilio/{iddomicilio}")
	public String eliminarDomicilio(@PathVariable Integer iddomicilio) {
		domicilioservice.eliminarDomicilio(iddomicilio);
		return "redirect:/domicilio";
	}
	@GetMapping("/exportarExcelDomicilio")
	public void exportarListaDeDomicilioExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Domicilio_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Domicilio> domicilio = domicilioservice.listarTodosLosDomicilios();
		
		ListarDomicilioExcel exporter = new ListarDomicilioExcel(domicilio);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfDomicilio")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(domicilioservice.listarTodosLosDomicilios());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteDomicilio.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteDomicilio.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
