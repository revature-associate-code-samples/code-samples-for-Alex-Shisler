package com.revature.test;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;

public class TestGameDAO {
	private GameDAO gDAO = new GameDAOImpl();
	@Ignore
	@Test
	public void testGetGameByName() {
		/*Game result = gDAO.getGameByName("The Legend of Zelda: Majora's Mask");
		System.out.println(result);
		assertEquals("The Legend of Zelda: Majora's Mask", result.getTitle());
		assertEquals("Nintendo EAD", result.getCompany());*/
		
	}
	@Ignore
	@Test
	public void testSearchGameByName() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetGamesByCompany() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetGamesByPlatform() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetGameByGenre() {
		fail("Not yet implemented");
	}

}
