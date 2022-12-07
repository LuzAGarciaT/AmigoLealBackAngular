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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Proveedor;
import com.example.ElAmigoLeal.Impl.ProveedorService;
import com.example.ElAmigoLeal.Utilities.ListaProveedorExcel;

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
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping("/proveedor") // YA
	public String listarProveedor(Model model) {
		model.addAttribute("proveedor", proveedorservice.listarTodosLosProveedores());
		return "Proveedores/Proveedor";
	}

	@GetMapping("/proveedor/crear")
	public String crearProveedor(Model model) {
		Proveedor proveedor = new Proveedor();
		model.addAttribute("proveedor", proveedor);
		return "Proveedores/crear";
	}

	@PostMapping("/proveedor")
	public String guardarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
		proveedorservice.guardarProveedor(proveedor);
		return "redirect:/proveedor";
	}

	@GetMapping("/proveedor/editar/{idproveedor}")
	public String editarProveedor(@PathVariable Integer idproveedor, Model model) {
		model.addAttribute("proveedor", proveedorservice.obtenerProveedorbyId(idproveedor));
		return "Proveedores/editar";
	}

	@PostMapping("/proveedor/{idproveedor}")
	public String actualizarProveedor(@PathVariable Integer idproveedor, @ModelAttribute("proveedor") Proveedor proveedor,
			Model model) {
		Proveedor proveedorExistente = proveedorservice.obtenerProveedorbyId(idproveedor);
		proveedorExistente.setIdproveedor(idproveedor);
		proveedorExistente.setNombre(proveedor.getNombre());
		proveedorExistente.setTelefono(proveedor.getTelefono());
		proveedorExistente.setCorreo(proveedor.getCorreo());
		proveedorExistente.setDireccion(proveedor.getDireccion());

		proveedorservice.actualizarProveedor(proveedorExistente);
		return "redirect:/proveedor";
	}

	@GetMapping("/proveedor/{idproveedor}")
	public String eliminarProveedor(@PathVariable Integer idproveedor) {
		proveedorservice.eliminarProveedor(idproveedor);
		return "redirect:/proveedor";
	}
	@GetMapping("/exportarExcelProveedor")
	public void exportarListaDeProveedorExcel(HttpServletResponse response)throws IOException {
		response.setContentType("aplication/octec-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Proveedor_" + fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		
		List<Proveedor> proveedor = proveedorService.findAll();
		
		ListaProveedorExcel exporter = new ListaProveedorExcel(proveedor);
		exporter.Exportar(response);
	}
	@GetMapping("/ExportarPdfProveedor")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(proveedorService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteProveedor.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteProveedor.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}
