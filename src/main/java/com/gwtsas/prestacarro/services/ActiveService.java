package com.gwtsas.prestacarro.services;

import java.util.List;

import com.gwtsas.prestacarro.entities.Active;
import com.gwtsas.prestacarro.schemas.ActiveSchema;

public interface ActiveService {
	
	List<Active> getAllActives();
	
	Active getActiveById(Long id);
	
	Active createActive(ActiveSchema activeSchema);
	
	Active changeDisableStatus(Long id, boolean value);
}
