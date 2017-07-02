package bands;
import java.util.Iterator;

import shows.ShowsInterface;

/**
 * 
 * @author 50503_50647
 *
 */

public interface Artist {
	
	/**
	 * 
	 * @return The band name if its a band, or the artist name if its an artist
	 */
	public String getName();

	/**
	 * 
	 * @return the total number of albums
	 */
	int getNumberOfAlbums();
	
	/**
	 * This method adds a show to a list of shows of each artist
	 * @param show
	 */
	void addShow(ShowsInterface show);
	/**
	 * 
	 * @return The iterator of each shows of an artist/band
	 */
	Iterator<ShowsInterface> getArtistShows();

}
