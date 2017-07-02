package findConcertTicket;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bilhiate.Ticket;
import exceptions.AdminNotOn;
import exceptions.AnotherUserLoggedIn;
import exceptions.ArtistsDontExist;
import exceptions.BandAlreadyExists;
import exceptions.NoTickets;
import exceptions.NoUserLoggedIn;
import exceptions.ShowAlreadyExists;
import exceptions.ShowNotExist;
import exceptions.UnknownShow;
import exceptions.UserAlreayExists;
import exceptions.UserLoggedIn;
import exceptions.UserNotExist;
import exceptions.WrongPassword;
import shows.ShowsInterface;
/**
 * 
 * @author 50503_50647
 *
 */
public interface FindConcertTicketInterface {

	/**
	 * 
	 * @return The current logged in user
	 */
	String getCurrentUserName();

	/**
	 * This method calculates the price of a show given a date and a name and
	 * quantity x
	 * 
	 * @param name
	 * @param quantity
	 * @param date
	 * @return The price of the show
	 */
	public int getShowPrice(String name, int quantity, String date);

	/**
	 * This method creates an account for a user in the app, giving the type of
	 * the user (Admin or Client), and an email. This method throws 2
	 * exceptions, in case there is no user logged in in the app, or in case he
	 * already exists
	 * 
	 * @param type
	 * @param mail
	 * @throws UserLoggedIn
	 * @throws UserAlreayExists
	 */
	void registUser(String type, String mail) throws UserLoggedIn, UserAlreayExists;

	/**
	 * This method logs in a user in the app, giving his email and password,
	 * also throws 4 exceptions, in case he doesn't have an account in the app,
	 * the user is already logged in, someone else is logged in, or the password
	 * doesn't match
	 * 
	 * @param mail
	 * @param pw
	 * @throws UserNotExist
	 * @throws UserLoggedIn
	 * @throws AnotherUserLoggedIn
	 * @throws WrongPassword
	 */
	void login(String mail, String pw) throws UserNotExist, UserLoggedIn, AnotherUserLoggedIn, WrongPassword;

	/**
	 * This method unlogs a user in the app, throws an exception in case no user
	 * is logged in
	 * 
	 * @throws NoUserLoggedIn
	 */
	void logOut() throws NoUserLoggedIn;

	/**
	 * This method allows a user of a type admin to regist an artist in the app,
	 * this method also throws and exception in case the admin is not logged in,
	 * or the artist is already registed in the app
	 * 
	 * @param bandName
	 * @param albumN
	 * @param albums
	 * @throws AdminNotOn
	 * @throws BandAlreadyExists
	 */
	void addArtists(String bandName, int albumN, String[] albums) throws AdminNotOn, BandAlreadyExists;

	/**
	 * 
	 * This method allows a user of a type admin to regist a band in the app,
	 * this method also throws and exception in case the admin is not logged in,
	 * or the artist is already registed in the app
	 * 
	 * @param bandName
	 * @param albumN
	 * @param albums
	 * @param bandENumber
	 * @param bandElements
	 * @throws AdminNotOn
	 * @throws BandAlreadyExists
	 */
	void addBand(String bandName, int albumN, String[] albums, int bandENumber, String[] bandElements)
			throws AdminNotOn, BandAlreadyExists;

	/**
	 * This method allows a user of a type admin to regist a concert in the app,
	 * give the concert name, description, capacity, artist name, startdate, the
	 * price of the show, this method also throws an exception in case the admin
	 * is not logged in, a show already is registed in the app or some artists
	 * are not registed in the app, also throws an exception in case the admin
	 * is not logged in, a show already is registed in the app or the artist is
	 * not registed in the app
	 * 
	 * @param name
	 * @param des
	 * @param cap
	 * @param artist
	 * @param date
	 * @param price
	 * @throws AdminNotOn
	 * @throws ShowAlreadyExists
	 * @throws ArtistsDontExist
	 */
	void addConcert(String name, String des, int cap, String artist, String date, int price)
			throws AdminNotOn, ShowAlreadyExists, ArtistsDontExist;

	/**
	 * 
	 * This method allows a user of a type admin to regist a festival in the
	 * app, given the festival name, description, capacity, startDate, the line
	 * up for each day, the number of days of the festival and the price for
	 * each day this method also throws an exception in case the admin is not
	 * logged in, a show already is registed in the app or some artists are not
	 * registed in the app
	 * 
	 * @param name
	 * @param desc
	 * @param cap
	 * @param date
	 * @param daysOfShow
	 * @param numberOfDays
	 * @param price
	 * @throws AdminNotOn
	 * @throws ShowAlreadyExists
	 * @throws ArtistsDontExist
	 */
	void addFestival(String name, String desc, int cap, String date, Map<Integer, List<String>> daysOfShow,
			int numberOfDays, int[] price) throws AdminNotOn, ShowAlreadyExists, ArtistsDontExist;

	/**
	 * This method allows a registed client user to buy a concert ticket, giving
	 * the concert name, the date and the quantity of tickets, this method also
	 * throws an exception in case an admin is not logged in in the app, a show
	 * given the name and the date doensn't exist in the app, or there are no
	 * tickets left
	 * 
	 * @param name
	 * @param date
	 * @param quantity
	 * @throws AdminNotOn
	 * @throws ShowNotExist
	 * @throws NoTickets
	 */
	void buyTicketeru(String name, String date, int quantity) throws AdminNotOn, ShowNotExist, NoTickets;

	/**
	 * This method allows a registed client user to buy a festival ticket,
	 * giving the concert name, the date the quantity of tickets and the list of
	 * days of the festival, this method also throws an exception in case an
	 * admin is not logged in in the app, a show given the name and the date
	 * doensn't exist in the app, or there are no tickets left
	 * 
	 * @param name
	 * @param showDate
	 * @param date
	 * @param quantity
	 * @throws AdminNotOn
	 * @throws ShowNotExist
	 * @throws NoTickets
	 */
	void buyFestivaruTicketeru(String name, String showDate, List<String> date, int quantity)
			throws AdminNotOn, ShowNotExist, NoTickets;

	/**
	 * 
	 * @param mail
	 * @return The generated password of user registed in the app
	 */
	String getUserPw(String mail);

	/**
	 * 
	 * @return The iterator of shows
	 */
	Iterator<ShowsInterface> getShows();

	/**
	 * This method returns a show given the name and the start date of the show,
	 * this method also throws and exception in case the show doesn't exist in
	 * the app
	 * 
	 * @param name
	 * @param date
	 * @return A show (Festival or Concert)
	 * @throws ShowNotExist
	 */
	ShowsInterface getShow(String name, String date) throws ShowNotExist;

	/**
	 * 
	 * @return The iterator of concert tickets
	 */
	Iterator<Ticket> iterateConcertTickets();

	/**
	 * 
	 * @return The iterator of festival tickets
	 */
	Iterator<Ticket> iterateFestivalTickets();

	/**
	 * 
	 * @return The iterator of the shows ordered by the comparator, and orders
	 *         the list by most sold shows
	 */
	Iterator<ShowsInterface> sortShows();

	/**
	 * 
	 * @param type
	 * @return The iterator of shows ordered by the comparator, and orders the
	 *         list by dates
	 * @throws UnknownShow
	 */
	Iterator<ShowsInterface> getShowByType(String type) throws UnknownShow;

	/**
	 * 
	 * @param name
	 * @return The iterator of the shows where an artist will play
	 */
	Iterator<ShowsInterface> getArtistShow(String name);

	/**
	 * 
	 * @param name
	 * @return True in case the artist exists in the app, false in case he
	 *         doesn't
	 */
	boolean existsArtist(String name);

}