package exceptions;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author 50503_50647
 *
 */
public class ArtistsDontExist extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> artistList;
	
	public ArtistsDontExist(List<String> artistList){
		this.artistList = artistList;
		
	}
	
	public String getMessage(){
		return "Artist name(s) do(es) not exist(s):";
	
	}
	
	public Iterator<String> iterateArtistDontExist(){
		return artistList.iterator();
	}

}
