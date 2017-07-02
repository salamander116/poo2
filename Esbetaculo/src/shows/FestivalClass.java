package shows;

import java.time.LocalDate;
import java.util.*;
/**
 * 
 * @author 50503_50647
 *
 */
public class FestivalClass extends AbstractShows implements FestivalInterface {

	private int numberOfDays;
	private Map<Integer, List<String>> daysOfShow;
	private Map<LocalDate, Integer> capByDay;
	private int[] prices;

	public FestivalClass(String name, String desc, int cap, LocalDate startDate, Map<Integer, List<String>> daysOfShow,
			int numberOfDays, int[] price) {
		super(name, desc, cap, startDate);
		this.numberOfDays = numberOfDays;
		this.daysOfShow = daysOfShow;
		capByDay = new HashMap<LocalDate, Integer>();
		cap();
		prices = price;

	}

	public boolean hasBand(String name) {
		boolean hasBand = false;
		for (int i = 0; daysOfShow.get(i) != null && !hasBand; i++) {
			if (daysOfShow.get(i).contains(name))
				hasBand = true;
		}
		return hasBand;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}
	/**
	 * This method fulfills each day of the concert with the capacity
	 */
	private void cap() {
		for (int i = 0; i < numberOfDays; i++) {
			capByDay.put(startDate.plusDays(i), cap);
		}
	}

	public int getPrice(int i) {
		return (prices[i]);
	}

	public Iterator<String> iteratorByDay(int i) {
		return daysOfShow.get(i).iterator();
	}

	public void buyTicket(List<LocalDate> listOfDates) {

		for (int i = 0; i < listOfDates.size(); i++) {
			int currentDayTickets = capByDay.get(listOfDates.get(i)) - 1;
			capByDay.put(listOfDates.get(i), currentDayTickets);
			ticketSold++;
		}
	}

	public boolean canBuyTicketF(List<LocalDate> listOfDates) {
		boolean canBuy = true;
		for (int i = 0; i < listOfDates.size() && canBuy == true; i++) {
			if (capByDay.get(listOfDates.get(i)) == 0) {
				canBuy = false;
			}
		}
		return canBuy;
	}

	public int getCap(int i) {
		LocalDate date = startDate.plusDays(i);
		return capByDay.get(date);
	}

	public Iterator<String> iterateDay(int i) {
		return daysOfShow.get(i).iterator();
	}

}