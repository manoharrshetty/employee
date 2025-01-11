package com.emp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.emp.model.Dept;
import com.emp.model.DeptQuery;
/**MyBatis mapper for Department resource
 * The MyBatis-Spring-Boot-Starter will search, by default, for mappers marked with the @Mapper annotation.
 * @author manoh
 *
 */
@Mapper
public interface DeptMapper extends IbatisMapper<Dept,DeptQuery,Integer>{
	
    
    
    
	
}
