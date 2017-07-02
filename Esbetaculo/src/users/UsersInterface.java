package users;
/**
 * 
 * @author 50503_50647
 *
 */
public interface UsersInterface {
	/**
	 * 
	 * @return The user email 
	 */
	String getEmail();

	/**
	 * 
	 * @return The password automatically  generated for a User/Admin
	 */
	String getPw();
	
	/**
	 * This method logs in a user in the app
	 */
	void logMeIn();
	
	/**
	 * This method unlogs a a user of the app
	 */
	void unlog();
	/**
	 * 
	 * @return True in case the user is logged in, false in case he isn't
	 */
	boolean amIlogged();

}
