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

import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Entity.Rol;
import com.example.ElAmigoLeal.Entity.Categoria;
import com.example.ElAmigoLeal.Impl.CategoriaService;
import com.example.ElAmigoLeal.Utilities.ListarCategoriaExcel;
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

public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	// listar
		@GetMapping("/categoria")
		public List<Categoria> listar() {
			return categoriaService.findAll();
		}

		// guardar categoria
		@PostMapping("/categoria/guardar")
		public Categoria guardar(@RequestBody Categoria categoria) {
			return categoriaService.save(categoria);
		}

		@GetMapping("/categoria/{idcategoria}")
		public Categoria getCategoria(@PathVariable Integer idcategoria) {
			return categoriaService.findbyId(idcategoria);
		}

		// editar categoria
		@PutMapping("/categoria/{idcategoria}")
		public Categoria editar(@RequestBody Categoria categoria, @PathVariable Integer idcategoria) {
			Categoria categoriaActual = categoriaService.findbyId(idcategoria);
			categoriaActual.setNombrecategoria(categoria.getNombrecategoria());

			return categoriaService.save(categoriaActual);
		}

		// eliminar categoria
		@DeleteMapping("/categoria/eliminar/{idcategoria}")
		public void eliminar(@PathVariable Integer idcategoria) {
			categoriaService.delete(idcategoria);
		}

	
	@GetMapping("/categoria/exportarExcelCategoria")
	public ResponseEntity<InputStreamResource> exportar() throws IOException {
		
		List<Categoria> listacategoria = categoriaService.findAll();
		ListarCategoriaExcel excelExportar = new ListarCategoriaExcel(listacategoria);
		ByteArrayInputStream bais = excelExportar.export();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listacategoria.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bais));
		
		
		
	}
	@GetMapping("/categoria/ExportarPdfCategoria")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {
		
		    JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(categoriaService.findAll());
		    JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/MyReports/ReporteCategoria.jrxml"));
		    
		    HashMap<String, Object> map=new HashMap<>();
		    JasperPrint report = JasperFillManager.fillReport(compileReport, null, beanCollectionDataSource);		    
		    byte[] data = JasperExportManager.exportReportToPdf(report);
		    
		    HttpHeaders headers=new HttpHeaders();
		    headers.set(HttpHeaders.CONTENT_DISPOSITION, "incline;filename=ReporteCategoria.pdf");
		    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}
}


