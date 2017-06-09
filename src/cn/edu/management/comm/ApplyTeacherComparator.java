package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;

/**
 *查看已申请状态,申请时间比较器
 * 
 */
public class ApplyTeacherComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final ApplyModifyLogVO other1 = (ApplyModifyLogVO) o1;
		final ApplyModifyLogVO other2 = (ApplyModifyLogVO) o2;

		int result;
		// 比申请时间
		result = other2.getApplyDate().compareTo(other1.getApplyDate());
		return result;
	}

}
