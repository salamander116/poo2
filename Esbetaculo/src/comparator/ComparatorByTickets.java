package comparator;

import java.time.LocalDate;
import java.util.Comparator;

import shows.*;
/**
 * 
 * This comparator allows to order the shows by the most sold shows, if the shows have the same number
 * of tickets sold, then the comparator orders the shows by date, then if the both dates are equal,
 * finally orders it alphabetically.
 * 
 * @author 50503_50647
 *
 * @param <ShowsInterface> List
 */
public class ComparatorByTickets<T extends ShowsInterface> implements Comparator<ShowsInterface> {

	public int compare(ShowsInterface o1, ShowsInterface o2) {
		int result = o2.getSoldTickets() - o1.getSoldTickets();

		if (result == 0) {
			LocalDate date1 = o1.getStartDate();
			LocalDate date2 = o2.getStartDate();

			if (date2.isAfter(date1)) {
				result = -1;
			} else if (date1.isAfter(date2)) {
				result = 1;
			}

			else if (date1.isEqual(date2)) {
				String name1 = o1.getName();
				String name2 = o2.getName();
				result = name1.compareTo(name2);
				if (result < 0) {
					result = -1;
				}
				if (result > 0) {
					result = 1;
				}
			}
		}
		return result;
	}

}
