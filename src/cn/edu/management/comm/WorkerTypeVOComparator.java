package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.voImpl.WorkerTypeVO;

/**
 *�ۺϱȽ���
 * 
 */
public class WorkerTypeVOComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final WorkerTypeVO other1 = (WorkerTypeVO) o1;
		final WorkerTypeVO other2 = (WorkerTypeVO) o2;

		int result;
		// �ȱ��
		result = other1.getIdnum().compareTo(other2.getIdnum());
		return result;
	}

}
