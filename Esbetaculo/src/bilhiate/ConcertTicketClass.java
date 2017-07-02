package bilhiate;

import java.time.LocalDate;
/**
 * 
 * @author 50503_50647
 *
 */
public class ConcertTicketClass extends AbstratcTicket implements ConcertTicket {
	
	LocalDate date;

	public ConcertTicketClass(String name, int totalTickets, int totalPrice, LocalDate date) {
		super(name, totalTickets, totalPrice);
		this.date = date;
		
	}
	
	public LocalDate getDate(){
		return date;
	}
	

}
