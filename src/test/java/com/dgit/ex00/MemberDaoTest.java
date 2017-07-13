package com.dgit.ex00;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgit.persistence.MemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MemberDaoTest {

	@Autowired
	private MemberDao dao;

	@Test
	public void testTiime() {
		System.out.println(dao.getTime());
	}

	/*@Test
	public void testInsertMember() {
		MemberVO vo = new MemberVO();
		vo.setUserid("user01");
		vo.setUserpw("user01");
		vo.setUsername("USER01");
		vo.setEmail("user01@aaa.com");
		dao.insertMember(vo);
	}*/
	
	@Test
	public void testReadMember() throws Exception {		
		dao.readMember("user00");	
	}
	
	@Test
	public void testReadWithPw() throws Exception {
		dao.login("user00", "user00");
	}
	
	@Test
	public void testSelectAll() throws Exception {
		dao.selectAll();
	}
}
