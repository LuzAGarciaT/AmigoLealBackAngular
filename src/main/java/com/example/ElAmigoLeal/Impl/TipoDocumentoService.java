package com.example.ElAmigoLeal.Impl;

import java.util.List;

import com.example.ElAmigoLeal.Entity.TipoDocumento;

public interface TipoDocumentoService {
	
	public List<TipoDocumento> findAll();
	public TipoDocumento save(TipoDocumento tipodocumento);
	public TipoDocumento findbyId(Integer iddoc);
	public void delete(Integer iddoc);

}
