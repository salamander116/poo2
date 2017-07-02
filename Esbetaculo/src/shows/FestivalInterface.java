package shows;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author 50503_50647
 *
 */
public interface FestivalInterface extends ShowsInterface {
	
	/**
	 * 
	 * @param name
	 * @return True if the band exists in the festival
	 */
	boolean hasBand(String name);
	
	/**
	 * 
	 * @param i
	 * @return The capacity of each day of the festival
	 */
	int getCap(int i);
	
	/**
	 * 
	 * @param i
	 * @return The iterator for each day of the show
	 */
	Iterator<String> iterateDay(int i);
	
	/**
	 * 
	 * @return The total days of the festival
	 */
	int getNumberOfDays();
	
	/**
	 * 
	 * @param listOfDates
	 * @return True in case the festival still have tickets, false in case it doesn't
	 */
	boolean canBuyTicketF(List<LocalDate>listOfDates);
	/**
	 * This method decrements 1 to the capacity of each day received by the list of dates of the user
	 * 
	 * @param listOfDates
	 */
	void buyTicket(List<LocalDate>listOfDates );
	
	
	
	
	
	

}
