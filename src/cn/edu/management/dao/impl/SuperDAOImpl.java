package cn.edu.management.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.edu.management.dao.SuperDAO;
import cn.edu.management.vo.VO;

@Component("superDao")
public class SuperDAOImpl extends SuperDAO {
	
	public int count(String hql) {
		// TODO Auto-generated method stub
		Integer count = -1;
		try{
			Query query = this.getSession().createQuery(hql);
			count = (Integer) query.uniqueResult();
			
		}catch( Exception e ){
			e.printStackTrace();
		}
		
		return count;
	}

	public boolean delete(VO vo) {
		// TODO Auto-generated method stub
		try{
			this.getHibernateTemplate().delete(vo);
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean insert(VO vo) {
		// TODO Auto-generated method stub
		try{
			this.getHibernateTemplate().save( vo );
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public VO selectById(Class classType, Serializable id) {
		// TODO Auto-generated method stub
		VO vo = null;
		try{
			vo = (VO) this.getHibernateTemplate().load(classType, id);
		}catch( Exception e ){
			e.printStackTrace();
		}
		return vo;
	}

	public List selectByPage(String hql, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(VO vo) {
		// TODO Auto-generated method stub
		try{
			this.getHibernateTemplate().update( vo );
		}catch( Exception e ){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
