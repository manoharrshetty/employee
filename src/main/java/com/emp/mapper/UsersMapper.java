package com.emp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.emp.model.Users;
import com.emp.model.UsersQuery;

@Mapper
public interface UsersMapper extends IbatisMapper<Users,UsersQuery,Integer> {


    Users findByName(String name);
	
}
