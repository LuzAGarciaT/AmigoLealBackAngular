package com.example.ElAmigoLeal.Controller;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Usuario;
import com.example.ElAmigoLeal.Impl.CorreoService;
import com.example.ElAmigoLeal.Impl.UsuarioService;
import com.example.ElAmigoLeal.Utilities.ListaUsuarioExcel;

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
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CorreoService correoService;

	@GetMapping("/usuario")
	public List<Usuario> listar() {
		return usuarioService.findAll();
	}

	// guardar usuario
	@PostMapping("/usuario/guardar")
	public Usuario guardar(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}

	@GetMapping("/usuario/{idusuario}")
	public Usuario getUsuario(@PathVariable Integer idusuario) {
		return usuarioService.findbyId(idusuario);
	}

	// editar usuario
	@PutMapping("/usuario/{idusuario}")
	public Usuario editar(@RequestBody Usuario usuario, @PathVariable Integer idusuario) {
		Usuario usuarioActual = usuarioService.findbyId(idusuario);
		usuarioActual.setPnombre(usuario.getPnombre());
		usuarioActual.setSnombre(usuario.getSnombre());
		usuarioActual.setPapellido(usuario.getPapellido());
		usuarioActual.setSapellido(usuario.getSapellido());
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setPassword(usuario.getPassword());
		usuarioActual.setRol(usuario.getRol());
		usuarioActual.setTipodocumento(usuario.getTipodocumento());
		return usuarioService.save(usuarioActual);
	}

	// eliminar usuario
	@DeleteMapping("/usuario/eliminar/{idusuario}")
	public void eliminar(@PathVariable Integer idusuario) {
		usuarioService.delete(idusuario);
	}

	@GetMapping("/usuario/exportarExcelUsuario")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
	
		List<Usuario> listausuario= usuarioService.findAll();
		ListaUsuarioExcel excelExportar = new ListaUsuarioExcel(listausuario);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listausuario.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
	}
	
	@GetMapping("/usuario/ExportarPdfUsuario")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(usuarioService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteUsuario.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteUsuario.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
	
	@GetMapping("/usuario/enviarcorreo")
	public String EnviarCorreos(@RequestParam Map<String, Object> Params) {
		correoService.enviarcorreo(Params.get("asunto").toString(), Params.get("mensaje").toString());
		return "ok:D";
	}

}
