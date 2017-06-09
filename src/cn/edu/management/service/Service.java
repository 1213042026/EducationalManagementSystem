package cn.edu.management.service;

import java.io.Serializable;

import cn.edu.management.vo.VO;

public interface Service {
	
	public Boolean insert( VO vo );
	public boolean update( VO vo );
	public boolean delete( VO vo );
	public VO selectById( Class classType , Serializable id );
	public int count( String hql );
	
}
