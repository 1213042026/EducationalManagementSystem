package cn.edu.management.dao;

import java.io.Serializable;
import java.util.List;

import cn.edu.management.vo.VO;

public interface DAO {
	public Boolean insert( VO vo );
	public boolean update( VO vo );
	public boolean delete( VO vo );
	public VO selectById( Class classType , Serializable id );
	public int count( String hql );
	public List selectByPage( String hql , int offset , int size );
}
