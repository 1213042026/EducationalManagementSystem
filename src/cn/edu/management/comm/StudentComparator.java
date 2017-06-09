package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.showVO.StudentVO;

/**
 *综合比较器
 * 
 */
public class StudentComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final StudentVO other1 = (StudentVO) o1;
		final StudentVO other2 = (StudentVO) o2;

		int result;
		// 比编号
		result = other1.getIdnum().compareTo(other2.getIdnum());
		return result;
	}

}
