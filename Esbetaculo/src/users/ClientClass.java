package users;

import java.time.LocalDate;
import java.util.*;
import bilhiate.*;
/**
 * 
 * @author 50503_50647
 *
 */

public class ClientClass extends UsersAbstract implements ClientInterface{

	private List<Ticket> myBlyatsConcerts;
	private List<Ticket> myBlyatsFestivals;

	public ClientClass(String email, int number) {
		super(email, number);
		pw = "client" + number;
		isAdmin = false;
		myBlyatsConcerts = new LinkedList<Ticket>();
		myBlyatsFestivals = new LinkedList<Ticket>();
	}

	
	public int getNumberOfShows() {
		return myBlyatsConcerts.size() + myBlyatsFestivals.size();
	}
	
	public void buyConcertTicketeru(String name, int totalTickets, int totalPrice, LocalDate date){
		myBlyatsConcerts.add(new ConcertTicketClass(name, totalTickets, totalPrice, date));
	}

	public void buyFestivalTicketeru(String name, int totalTickets, int totalPrice, List<LocalDate> date){
		myBlyatsFestivals.add(new FestivalTicketClass(name, totalTickets, totalPrice, date));
	}
	
	public Iterator<Ticket> getConcertTickets(){
		return myBlyatsConcerts.iterator();
	}
	
	public Iterator<Ticket> getFestivalTickets(){
		return myBlyatsFestivals.iterator();
	}
	
	
}
