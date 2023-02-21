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
import org.springframework.web.bind.annotation.RestController;

import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.TipoDocumento;
import com.example.ElAmigoLeal.Impl.TipoDocumentoService;
import com.example.ElAmigoLeal.Utilities.ListarRolExcel;
import com.example.ElAmigoLeal.Utilities.ListarTipoDocumentoExcel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
@RequestMapping(path = "/api")
public class TipoDocumentoController {

	@Autowired
	private TipoDocumentoService tipodocumentoService;

	// listar
	@GetMapping("/tipodocumento")
	public List<TipoDocumento> listar() {
		return tipodocumentoService.findAll();
	}

	// guardar tipo de documento 
	@PostMapping("/tipodocumento/guardar")
	public TipoDocumento guardar(@RequestBody TipoDocumento tipodocumento) {
		return tipodocumentoService.save(tipodocumento);
	}

	@GetMapping("/tipodocumento/{iddoc}")
	public TipoDocumento getTipoDocumento(@PathVariable Integer iddoc) {
		return tipodocumentoService.findbyId(iddoc);
	}

	// editar rol
	@PutMapping("/tipodocumento/{iddoc}")
	public TipoDocumento editar(@RequestBody TipoDocumento tipodocumento, @PathVariable Integer iddoc) {
		TipoDocumento tipodocumentoActual = tipodocumentoService.findbyId(iddoc);
		tipodocumentoActual.setTipodoc(tipodocumento.getTipodoc());

		return tipodocumentoService.save(tipodocumentoActual);
	}

	// eliminar rol
	@DeleteMapping("/tipodocumento/eliminar/{iddoc}")
	public void eliminar(@PathVariable Integer iddoc) {
		tipodocumentoService.delete(iddoc);
	}

	@GetMapping("/tipodocumento/exportarExcelTipoDoc")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
	
		List<TipoDocumento> listatipoDocumento = tipodocumentoService.findAll();
		ListarTipoDocumentoExcel excelExportar = new ListarTipoDocumentoExcel(listatipoDocumento);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listatipodocumento.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
	}

	@GetMapping("/ExportarPdfDoc")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				tipodocumentoService.findAll());
		JasperReport compileReport = JasperCompileManager
				.compileReport(new FileInputStream("src/main/resources/MyReports/TipoDoc.jrxml"));

		HashMap<String, Object> map = new HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);
		byte[] data = JasperExportManager.exportReportToPdf(report);

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteTipoDoc.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
