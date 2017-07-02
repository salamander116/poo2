package users;
/**
 * 
 * @author 50503_50647
 *
 */
public abstract class UsersAbstract implements UsersInterface {

	private String email;
	protected String pw;
	protected boolean isAdmin, loggedIn;

	public UsersAbstract(String email, int number) {
		this.email = email;
		loggedIn = false;
	}

	public void logMeIn(){
		loggedIn = true;
	}
	public void unlog(){
		loggedIn = false;
	}
	public boolean amIlogged(){
		return loggedIn;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}
}
