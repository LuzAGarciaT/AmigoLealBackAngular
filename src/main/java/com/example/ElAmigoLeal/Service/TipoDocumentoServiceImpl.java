package com.example.ElAmigoLeal.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElAmigoLeal.Entity.TipoDocumento;
import com.example.ElAmigoLeal.Impl.TipoDocumentoService;
import com.example.ElAmigoLeal.Repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
	@Autowired
	private TipoDocumentoRepository tipodocumentoRepository;

	@Override
	public List<TipoDocumento> findAll() {
		return (List<TipoDocumento>) tipodocumentoRepository.findAll();
	}

	@Override
	public TipoDocumento save(TipoDocumento tipodocumento) {
		return tipodocumentoRepository.save(tipodocumento);
	}

	@Override
	public TipoDocumento findbyId(Integer iddoc) {
		return tipodocumentoRepository.findById(iddoc).orElse(null);
	}

	@Override
	public void delete(Integer iddoc) {
		tipodocumentoRepository.deleteById(iddoc);
	}

	
}
