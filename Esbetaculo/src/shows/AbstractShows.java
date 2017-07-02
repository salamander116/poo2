package shows;

import java.time.LocalDate;
/**
 * 
 * @author 50503_50647
 *
 */
public abstract class AbstractShows implements ShowsInterface {

	protected String name;
	protected String desc;
	protected LocalDate startDate;
	protected int cap;
	protected int ticketSold;

	public AbstractShows(String name, String desc, int cap, LocalDate startDate) {
		this.name = name;
		this.cap = cap;
		this.desc = desc;
		this.startDate = startDate;
		ticketSold = 0;
	}
	

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public LocalDate getStartDate() {
		return startDate;
	}	

	public abstract int getPrice(int i);
	
	
	public int getSoldTickets(){
		return ticketSold;
	}
	

}
