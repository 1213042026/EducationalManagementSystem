package cn.edu.management.service;

import java.io.Serializable;

import javax.annotation.Resource;

import cn.edu.management.dao.SuperDAO;
import cn.edu.management.dao.UserDAO;
import cn.edu.management.vo.VO;

/**
 * 这个要写成abstract  是为了下次更好的进行扩展
 * @author Administrator
 *
 */
public  class SuperService implements Service {
	
	private SuperDAO superDao ;
	
	public int count(String hql) {
		// TODO Auto-generated method stub
		return this.superDao.count(hql);
	}

	public boolean delete(VO vo) {
		// TODO Auto-generated method stub
		return this.superDao.delete(vo);
	}

	public Boolean insert(VO vo) {
		// TODO Auto-generated method stub
		return this.superDao.insert(vo);
	}

	public VO selectById(Class classType, Serializable id) {
		// TODO Auto-generated method stub
		return this.superDao.selectById(classType, id);
	}

	public boolean update(VO vo) {
		// TODO Auto-generated method stub
		return this.superDao.update(vo);
	}

	@Resource
	public void setSuperDao(SuperDAO superDao) {
		this.superDao = superDao;
	}
	
	
	
	
	
}
