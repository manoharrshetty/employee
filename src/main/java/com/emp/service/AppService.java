package com.emp.service;

import java.util.List;

public interface AppService<Model,Query> {
	
	
	List<Model> findAll();
	List<Model> findByQuery(Query query);
	
	Model save(Model model);
	
	Model update(Model model);
	
	void delete(Integer id);
	
	
	

}
