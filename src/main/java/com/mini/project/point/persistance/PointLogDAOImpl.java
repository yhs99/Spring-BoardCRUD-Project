package com.mini.project.point.persistance;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mini.project.member.domain.PointLogDTO;

@Repository
public class PointLogDAOImpl implements PointLogDAO{

	private final static String NS = "com.mini.mappers.pointLogMapper.";
	@Inject
	SqlSession session;
	
	@Override
	public int insertPointLog(PointLogDTO work) throws Exception {
		return session.insert(NS+"insertPointLog", work);
	}

	@Override
	public int updateMemberPoint(PointLogDTO work) throws Exception {
		return session.insert(NS+"updateMemberPoint", work);
	}

}
