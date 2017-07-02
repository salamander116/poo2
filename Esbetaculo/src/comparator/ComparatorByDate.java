package comparator;

import java.time.LocalDate;
import java.util.Comparator;

import shows.ShowsInterface;
/**
 * 
 * This comparator allows to order the shows by date, in case the dates are equal to each other, 
 * the comparator then compares the names of each show and orders it alphabetically.
 *
 * @author 50503_50647
 *
 * @param <ShowsInterface> List
 */
public class ComparatorByDate<T extends ShowsInterface> implements Comparator<ShowsInterface> {

	public int compare(ShowsInterface show1, ShowsInterface show2) {
		int result = 0;
		LocalDate date1 = show1.getStartDate();
		LocalDate date2 = show2.getStartDate();
		if (date2.isAfter(date1)) {
			result = -1;
		} else if (date1.isAfter(date2)) {
			result = 1;
		}

		else if (date1.isEqual(date2)) {
			String name1 = show1.getName();
			String name2 = show2.getName();
			result = name1.compareTo(name2);
			if (result < 0) {
				result = -1;
			}
			if (result > 0) {
				result = 1;
			}
		}

		return result;
	}

}
