package cn.edu.management.comm;

import java.util.Comparator;

import cn.edu.management.vo.showVO.disAuditGraduationResult;

/**
 *查看已申请状态,申请时间比较器
 * 
 */
public class disAuditGraduationComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		final disAuditGraduationResult other1 = (disAuditGraduationResult) o1;
		final disAuditGraduationResult other2 = (disAuditGraduationResult) o2;

		int result;
		// 比申请时间
		result = other2.getApplyDate().compareTo(other1.getApplyDate());
		return result;
	}

}
