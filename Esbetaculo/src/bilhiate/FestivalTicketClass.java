package bilhiate;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author 50503_50647
 *
 */
public class FestivalTicketClass extends AbstratcTicket implements FestivalTicket {
	
	List<LocalDate> dates;
	
	public FestivalTicketClass(String name, int totalTickets, int totalPrice, List<LocalDate> date){
		super(name, totalTickets, totalPrice);
		dates = date;
	}
	
	public int totalTickets(){
		return dates.size();
	}
	
	public Iterator<LocalDate> returnDates(){
		return dates.iterator();
	}

}
