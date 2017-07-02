package bilhiate;
/**
 * 
 * @author 50503_50647
 *
 */
public abstract class AbstratcTicket implements Ticket {
	
	private String name;
	private int totalTickets, totalPrice;
	
	public AbstratcTicket(String name,int totalTickets, int totalPrice){
		this.name = name;
		this.totalTickets = totalTickets;
		this.totalPrice = totalPrice;
		
	}

	public String getName() {
		//just a comment
		return name;
	}

	public int getTotalTickets() {
		return totalTickets;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
		

}
