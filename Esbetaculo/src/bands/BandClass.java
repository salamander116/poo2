package bands;
/**
 * 
 * @author 50503_50647
 *
 */

public class BandClass extends ArtistClass implements Band{
	
	@SuppressWarnings("unused")
	private String[] elements;
	private int numberOfElements;

	public BandClass(String name, int albumNumbers, String[] albums, int bandENumber, String[] bandElements) {
		super(name, albumNumbers, albums);
		numberOfElements = bandENumber;
		elements = bandElements;
		

	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

}