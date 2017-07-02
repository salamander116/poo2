package bands;
import java.util.*;

import comparator.ComparatorByDate;
import shows.ShowsInterface;
/**
 * 
 * @author 50503_50647
 *
 */

public class ArtistClass implements Artist {

	@SuppressWarnings("unused")
	private String[] myAlbums;
	private int numberOfAlmbums;
	private String name;
	private List<ShowsInterface> shows;

	public ArtistClass(String name, int albumNumbers, String[] albums) {
		this.name = name;
		numberOfAlmbums = albumNumbers;
		myAlbums = albums;
		shows = new LinkedList<ShowsInterface>();
	}
	
	public void addShow(ShowsInterface show){
		if(!shows.contains(show))
		shows.add(show);
	}

	public String getName() {
		return name;
	}

	public int getNumberOfAlbums() {
		return numberOfAlmbums;
	}

	public Iterator<ShowsInterface> getArtistShows(){
		Collections.sort(shows, new ComparatorByDate<ShowsInterface>());	
		return shows.iterator();
	}
	
	
}
