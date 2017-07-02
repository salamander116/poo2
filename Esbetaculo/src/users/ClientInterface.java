package users;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import bilhiate.Ticket;
/**
 * 
 * @author 50503_50647
 *
 */
public interface ClientInterface extends UsersInterface {

	/**
	 * 
	 * @return The iterator of the concert tickets bought by a user
	 */
	Iterator<Ticket> getConcertTickets();
	
	/**
	 * 
	 * @return The number of Tickets bought
	 */
	int getNumberOfShows();
	
	/**
	 * This method adds a bought concert ticket to the list of concert tickets bought by a user
	 * 
	 * @param name
	 * @param totalTickets
	 * @param totalPrice
	 * @param date
	 */
	void buyConcertTicketeru(String name, int totalTickets, int totalPrice, LocalDate date);
	
	/**
	 * This method adds a bought festival ticket to the list of festival tickets bought by a user
	 * 
	 * @param name
	 * @param totalTickets
	 * @param totalPrice
	 * @param date
	 */
	void buyFestivalTicketeru(String name, int totalTickets, int totalPrice,  List<LocalDate> date);
	
	/**
	 * 
	 * @return The iterator of the festival tickets bought by a user
	 */
	Iterator<Ticket> getFestivalTickets();
}
