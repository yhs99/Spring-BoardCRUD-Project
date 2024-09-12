package com.mini.project;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mini.project.board.domain.BoardDTO;
import com.mini.project.board.persistance.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class DummyInsert {
	
	@Inject
	private BoardDAO dao;
	
	@Test
	public void loginMemberTest() {
		try {
			for(int i=1; i<500; i++) {
				BoardDTO dto = BoardDTO.builder().writer("imfirst").title("더미" + i + "번째 데이터").content(i + " 내용").build();
				dao.insertBoard(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
