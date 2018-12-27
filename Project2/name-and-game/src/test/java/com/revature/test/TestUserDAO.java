package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.model.GameUser;

public class TestUserDAO {
	private UserDAO uDAO = new UserDAOImpl();
	
	//**************************************createUser()*****************************************************//
		@Ignore
		@Test
		public void testCreateUser() {
			
			GameUser user = new GameUser("CreateMe", "test@testmail.com", "password", "user");
			
			int id = uDAO.createUser(user);
			GameUser result = uDAO.getUserbyId(id);
			assertEquals("CreateMe", result.getUsername());
			assertEquals("test@testmail.com", result.getEmail());
			assertEquals("password", result.getPassword());
			assertEquals("user", result.getRole());
				
		} 
	//**************************************getUser()*****************************************************//
		@Ignore
		@Test
		public void testGetUser() {		
			GameUser result = uDAO.getUser("GMan");
			System.out.println(result);
			assertEquals("GMan", result.getUsername());
			assertEquals("halflife4@gmail.com", result.getEmail());
			assertEquals("Freeman", result.getPassword());
			assertEquals("user", result.getRole());
		}
	//*************************************testUpdateUser()******************************************************//
		
		@Ignore
		@Test
		public void testUpdateUser() {
			GameUser ogResult = uDAO.getUser("Testman");
			ogResult.setPassword("updated");
			uDAO.updateUser(ogResult);
			GameUser uResult = uDAO.getUserbyId(ogResult.getId());
			assertEquals("Testman", uResult.getUsername());
			assertEquals("test1@testmail.com", uResult.getEmail());
			assertEquals("updated", uResult.getPassword());
			assertEquals("user", uResult.getRole());			
		}
	//*************************************testUpdateUser()******************************************************//
		
		@Ignore
		public void testGetAllUsers() {
			fail("Not yet implemented"); 
		}



	//*************************************Initialization******************************************************//
		
		@Ignore
		@After
		public void deleteUser() {
			GameUser user = uDAO.getUser("CreateMe");
			System.out.println(user);
			if(user != null)
				uDAO.deleteUser(user.getId());


		}
		@Ignore
		@After
		public void resetPassword() {
			GameUser ogResult = uDAO.getUser("Testman");
			ogResult.setPassword("password");
		}
		
	//******************************************************************************************************//

}
