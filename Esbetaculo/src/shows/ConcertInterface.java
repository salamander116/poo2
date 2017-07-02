package shows;
/**
 * 
 * @author 50503_50647
 *
 */
public interface ConcertInterface extends ShowsInterface {
	
	/**
	 * 
	 * @return The artist name that will play in the show
	 */
	String getArtist();
	
	/**
	 * 
	 * @return The price of the concert
	 */
	int getShowPrice();
	
	/**
	 * 
	 * @return The full capacity of the concert
	 */
	int getCap();
	
	/**
	 * 
	 * @param i
	 * @return True if there are tickets left, false in case there are no tickets left
	 */
	boolean canBuyTicket(int i);
	
	/**
	 * This method decrements the paramenter i to the capacity of the show
	 * 
	 * @param i
	 */
	void buyTicket(int i);
	


}
