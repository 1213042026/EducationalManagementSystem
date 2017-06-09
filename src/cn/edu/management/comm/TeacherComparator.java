package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.voImpl.Ems_Teacher_VO;

/**
 *综合比较器
 * 
 */
public class TeacherComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final Ems_Teacher_VO other1 = (Ems_Teacher_VO) o1;
		final Ems_Teacher_VO other2 = (Ems_Teacher_VO) o2;

		int result;
		// 比编号
		result = other1.getIdnum().compareTo(other2.getIdnum());
		return result;
	}

}
