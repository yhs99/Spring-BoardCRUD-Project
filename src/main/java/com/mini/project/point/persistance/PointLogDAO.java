package com.mini.project.point.persistance;

import com.mini.project.member.domain.PointLogDTO;

public interface PointLogDAO {
	int insertPointLog(PointLogDTO work) throws Exception;
	int updateMemberPoint(PointLogDTO work) throws Exception;
}
