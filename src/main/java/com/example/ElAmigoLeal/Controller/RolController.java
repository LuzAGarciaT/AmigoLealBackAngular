package com.example.ElAmigoLeal.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Impl.RolService;
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
public class RolController {

	@Autowired
	private RolService rolService;

	// listar
	@GetMapping("/rol")
	public List<Rol> listar() {
		return rolService.findAll();
	}

	// guardar rol
	@PostMapping("/rol/guardar")
	public Rol guardar(@RequestBody Rol rol) {
		return rolService.save(rol);
	}

	@GetMapping("/rol/{idrol}")
	public Rol getRol(@PathVariable Integer idrol) {
		return rolService.findbyId(idrol);
	}

	// editar rol
	@PutMapping("/rol/{idrol}")
	public Rol editar(@RequestBody Rol rol, @PathVariable Integer idrol) {
		Rol rolActual = rolService.findbyId(idrol);
		rolActual.setTiporol(rol.getTiporol());

		return rolService.save(rolActual);
	}

	// eliminar rol
	@DeleteMapping("/rol/eliminar/{idrol}")
	public void eliminar(@PathVariable Integer idrol) {
		rolService.delete(idrol);
	}

	@GetMapping("/rol/exportarExcelRol")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
	
		List<Rol> listarol = rolService.findAll();
		ListarRolExcel excelExportar = new ListarRolExcel(listarol);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listarol.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}

	@GetMapping("/rol/ExportarPdfRol")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(rolService.findAll());
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/MyReports/roles.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);
		byte[] data = JasperExportManager.exportReportToPdf(report);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteRol.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
