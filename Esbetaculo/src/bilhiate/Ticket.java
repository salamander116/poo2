package bilhiate;

/**
 * 
 * @author  50503_50647
 *
 */
public interface Ticket {
	
	/**
	 * 
	 * @return The name of the show bought
	 */
	String getName();
	
	/**
	 * 
	 * @return The total number of tickets bought
	 */
	int getTotalTickets();
	
	/**
	 * 
	 * @return The total price payed for the ticket/s
	 */
	int getTotalPrice();

}
