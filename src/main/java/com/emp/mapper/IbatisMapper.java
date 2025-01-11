package com.emp.mapper;

import java.util.List;

public interface IbatisMapper<M, Q, K> {
	M findById(K key);

	List<M> findByQuery(Q query);

	List<M> findAll();

	Integer findNextSeq();

	Integer save(M model);

	Integer update(M model);

	Integer delete(K key);

}
