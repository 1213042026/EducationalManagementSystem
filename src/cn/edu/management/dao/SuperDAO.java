package cn.edu.management.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.management.vo.VO;

public abstract class SuperDAO extends HibernateDaoSupport implements DAO {
	
	
	
	public int count(String hql) {
		
		return 0;
	}

	public boolean delete(VO vo) {
		
		return true;
	}

	public Boolean insert(VO vo) {
		
		return true;
	}

	public VO selectById(Class classType, Serializable id) {
		
		return null;
	}

	public List selectByPage(String hql, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(VO vo) {
		
		return true;
	}
	
	
	/**
	 * 这个一定要加上去的，如果不加则会报错，原因有待查明
	 * @param sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	

}
