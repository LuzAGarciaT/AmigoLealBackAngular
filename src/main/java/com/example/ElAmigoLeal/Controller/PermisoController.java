package com.example.ElAmigoLeal.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
import com.example.ElAmigoLeal.Entity.Permiso;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Permiso;
import com.example.ElAmigoLeal.Impl.PermisoService;
import com.example.ElAmigoLeal.Utilities.ListarPermisoExcel;
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
public class PermisoController {
	@Autowired
	private PermisoService permisoService;
	
	// listar
	@GetMapping("/permiso")
	public List<Permiso> listar() {
		return permisoService.findAll();
	}

	// guardar permiso
	@PostMapping("/permiso/guardar")
	public Permiso guardar(@RequestBody Permiso permiso) {
		return permisoService.save(permiso);
	}

	@GetMapping("/permiso/{idpermiso}")
	public Permiso getPermiso(@PathVariable Integer idpermiso) {
		return permisoService.findbyId(idpermiso);
	}

	// editar permiso
	@PutMapping("/permiso/{idpermiso}")
	public Permiso editar(@RequestBody Permiso permiso, @PathVariable Integer idpermiso) {
		Permiso permisoActual = permisoService.findbyId(idpermiso);
		permisoActual.setDescripcion(permiso.getDescripcion());

		return permisoService.save(permisoActual);
	}

	// eliminar permiso
	@DeleteMapping("/permiso/eliminar/{idpermiso}")
	public void eliminar(@PathVariable Integer idpermiso) {
		permisoService.delete(idpermiso);
	}

	@GetMapping("/permiso/exportarExcelPermiso")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Permiso> listapermiso = permisoService.findAll();
		ListarPermisoExcel excelExportar = new ListarPermisoExcel(listapermiso);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listapermiso.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}
	
	@GetMapping("/permiso/ExportarPdfPermiso")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(permisoService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/permisos.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReportePermiso.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}

	











