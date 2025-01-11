package com.emp.service;

import static com.emp.enums.MessageKey.DELETE_NOT_SUCCESSFUL;
import static com.emp.enums.MessageKey.UPDATE_NOT_SUCCESSFUL;
import static com.emp.enums.MessageKey.KEY_CANNOT_BE_EMPTY;
import static com.emp.util.MessageUtil.MESSAGE_PROPERTIES_INSTANCE;

import java.util.List;

import com.emp.mapper.IbatisMapper;
import com.emp.model.Entity;
/**
 * common functionality for the Employee application
 * @author manoh
 *
 * @param <M>
 * @param <Q>
 * @param <K>
 */
public class AppDomainService<M extends Entity,Q,K> {
	
	IbatisMapper<M,Q,K> mapper;

	public AppDomainService(IbatisMapper<M,Q,K> mapper) {
		this.mapper = mapper;
	}
	
	
	
	
	
	public List<M> findAll() {
		List<M> models  = mapper.findAll();
		return models;
	}
	
	
	
	public M save(M m) {
		mapper.save(m);
		return m;
		
	}
	
	public M update(M m,K k) {
		if (k == null) {
			throw new RuntimeException(String.format(MESSAGE_PROPERTIES_INSTANCE.getMessage(KEY_CANNOT_BE_EMPTY.name())));
		}
		
		int count = mapper.update(m);
		if (count == 0 ) {
			throw new RuntimeException(String.format(MESSAGE_PROPERTIES_INSTANCE.getMessage(UPDATE_NOT_SUCCESSFUL.name()), m.getId()));
		}
		
		return mapper.findById(k);
		
	}
	
	public void delete(K id) {
			
		int count  = mapper.delete(id);
		if (count == 0 ) {
			throw new RuntimeException(String.format(MESSAGE_PROPERTIES_INSTANCE.getMessage(DELETE_NOT_SUCCESSFUL.name()), id));
		}
		

	}
	
	
	public List<M> findByQuery(Q q) {
		List<M> models  = mapper.findByQuery(q);
		return models;
	}

	
	public M findById(K k) {
		M m  = mapper.findById(k);
		return m;
	}

}
