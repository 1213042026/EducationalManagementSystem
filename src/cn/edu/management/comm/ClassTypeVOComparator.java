package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.voImpl.ClassTypeVO;

/**
 *�ۺϱȽ���
 * 
 */
public class ClassTypeVOComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final ClassTypeVO other1 = (ClassTypeVO) o1;
		final ClassTypeVO other2 = (ClassTypeVO) o2;

		int result;
		// �ȱ��
		result = other1.getIdnum().compareTo(other2.getIdnum());
		return result;
	}

}
