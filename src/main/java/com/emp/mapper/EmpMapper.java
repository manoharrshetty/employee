package com.emp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.emp.model.Emp;
import com.emp.model.EmpQuery;
/**MyBatis mapper for Emp resource
 * The MyBatis-Spring-Boot-Starter will search, by default, for mappers marked with the @Mapper annotation.
 * @author manoh
 *
 */
@Mapper
public interface EmpMapper extends IbatisMapper<Emp,EmpQuery,Integer>{
	
    
    
	
	
	
	
    
	
}
