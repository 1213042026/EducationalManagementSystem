package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.voImpl.ProfessionVO;

/**
 *综合比较器
 * 
 */
public class ProfessionVOComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final ProfessionVO other1 = (ProfessionVO) o1;
		final ProfessionVO other2 = (ProfessionVO) o2;

		int result;
		// 比编号
		result = other1.getIdnum().compareTo(other2.getIdnum());
		return result;
	}

}
