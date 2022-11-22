package com.gwtsas.prestacarro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Active;
import com.gwtsas.prestacarro.repositories.ActiveRepository;
import com.gwtsas.prestacarro.schemas.ActiveSchema;
import com.gwtsas.prestacarro.services.ActiveService;

@Service
public class ActiveServiceImpl implements ActiveService{

	public  ActiveRepository activeRepository;

	public ActiveServiceImpl(ActiveRepository activeRepository) {
		this.activeRepository = activeRepository;
	}

	@Override
	public List<Active> getAllActives() {
		return activeRepository.findAll();
	}

	@Override
	public Active getActiveById(Long id) {
		return activeRepository.findById(id).get();
	}

	@Override
	public Active createActive(ActiveSchema activeSchema) {
		Active active = Active.builder()
				.internalCode(activeSchema.getInternalCode())
				.serial(activeSchema.getSerial()).build();
		return activeRepository.save(active);
	}

	@Override
	public Active changeDisableStatus(Long id, boolean value) {
		Active active = getActiveById(id);
		active.setDisabled(value);
		return activeRepository.save(active);
	}

	@Override
	public Active getTheLeastUsedActive(List<String> actives) {
		// TODO Auto-generated method stub
		return activeRepository.getLeastUsedActive(actives).get();
	}

}
