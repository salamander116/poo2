package shows;

import java.time.LocalDate;
/**
 * 
 * @author 50503_50647
 *
 */
public class ConcertsClass extends AbstractShows implements ConcertInterface {

	private String artist;
	private int price;

	public ConcertsClass(String name, String desc, int cap, String artist, LocalDate startDate, int price) {
		super(name, desc, cap, startDate);
		this.artist = artist;
		this.price = price;
	}

	public int getShowPrice() {
		return price;
	}

	public String getArtist() {
		return artist;
	}

	public int getPrice(int i) {
		return (i * price);
	}

	public void buyTicket(int i) {
		cap -= i;
		ticketSold += i;
	}
	
	public int soldTicks(){
		return ticketSold;
	}

	public boolean canBuyTicket(int i) {
		return (cap - i >=0);
	}

	public int getCap() {
		return cap;
	}
	
}
