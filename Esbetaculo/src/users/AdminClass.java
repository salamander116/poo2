package users;
/**
 * 
 * @author 50503_50647
 *
 */
public class AdminClass extends UsersAbstract {

	public AdminClass(String email, int number) {
		super(email, number);
		pw = "admin" + number;
	}
	
}
