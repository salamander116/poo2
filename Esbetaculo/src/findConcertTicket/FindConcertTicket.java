	package findConcertTicket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import bands.*;
import bilhiate.Ticket;
import comparator.ComparatorByDate;
import comparator.ComparatorByTickets;
import exceptions.*;
import shows.*;
import users.*;

/**
 * 
 * @author 50503_50647
 *
 */
public class FindConcertTicket implements FindConcertTicketInterface {

	private static final String CLIENT = "CLIENT";
	private static final String ADMIN = "ADMIN";
	private Map<String, UsersInterface> users;
	private Map<String, Artist> artists;
	private Map<LocalDate, Map<String, ShowsInterface>> shows;
	private boolean adminOn;
	private boolean login;
	private String currentUserName;
	private int userCounter, adminCounter;

	public FindConcertTicket() {
		login = false;
		users = new LinkedHashMap<String, UsersInterface>();
		userCounter = 0;
		adminCounter = 0;
		artists = new LinkedHashMap<String, Artist>();
		shows = new LinkedHashMap<LocalDate, Map<String, ShowsInterface>>();
		adminOn = false;
		currentUserName = null;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void registUser(String type, String mail) throws UserLoggedIn, UserAlreayExists {
		if (login == true) {
			throw new UserLoggedIn();
		} else {
			if (users.containsKey(mail)) {
				throw new UserAlreayExists();
			} else {
				if (type.equalsIgnoreCase(ADMIN)) {
					users.put(mail, new AdminClass(mail, ++userCounter));
				}

				if (type.equalsIgnoreCase(CLIENT)) {
					users.put(mail, new ClientClass(mail, ++adminCounter));
				}
			}
		}
	}

	/**
	 * 
	 * @return True in case someone is logged in the app, false in case there in
	 *         no one logged in
	 */
	private boolean getLoginStatus() {
		return login;
	}

	public void login(String mail, String pw) throws UserNotExist, UserLoggedIn, AnotherUserLoggedIn, WrongPassword {
		if (!users.containsKey(mail)) {
			throw new UserNotExist();
		} else {
			if (users.get(mail).amIlogged()) {
				throw new UserLoggedIn();
			} else {
				if (getLoginStatus()) {
					throw new AnotherUserLoggedIn();
				} else {
					if (!users.get(mail).getPw().equals(pw)) {
						throw new WrongPassword();
					} else {
						users.get(mail).logMeIn();
						login = true;
						currentUserName = mail;
						if (users.get(mail) instanceof AdminClass) {
							adminOn = true;
						} else {
							adminOn = false;
						}
					}
				}
			}
		}
	}

	public void logOut() throws NoUserLoggedIn {

		if (currentUserName == null) {
			throw new NoUserLoggedIn();
		} else {
			users.get(currentUserName).unlog();
			login = false;
			adminOn = false;
			currentUserName = null;
		}

	}

	/**
	 * 
	 * @return True in case an admin is online, false in case he isn't
	 */
	private boolean isAdminOn() {
		return adminOn;
	}

	public void addArtists(String bandName, int albumN, String[] albums) throws AdminNotOn, BandAlreadyExists {
		if (!(isAdminOn())) {
			throw new AdminNotOn();
		} else {
			if (artists.containsKey(bandName)) {
				throw new BandAlreadyExists();
			} else {
				artists.put(bandName, new ArtistClass(bandName, albumN, albums));
			}
		}
	}

	public void addBand(String bandName, int albumN, String[] albums, int bandENumber, String[] bandElements)
			throws AdminNotOn, BandAlreadyExists {

		if (!(isAdminOn())) {
			throw new AdminNotOn();
		} else {
			if (artists.containsKey(bandName)) {
				throw new BandAlreadyExists();
			} else {
				artists.put(bandName, new BandClass(bandName, albumN, albums, bandENumber, bandElements));
			}
		}
	}

	public void addConcert(String name, String des, int cap, String artist, String date, int price)
			throws AdminNotOn, ShowAlreadyExists, ArtistsDontExist {
		LocalDate parsedDate = parseToDateru(date);

		if (!(isAdminOn())) {
			throw new AdminNotOn();
		} else {
			if (shows.containsKey(parsedDate) && shows.get(parsedDate).containsKey(name)) {
				throw new ShowAlreadyExists();
			} else {
				if (!(artists.containsKey(artist))) {
					List<String> tempList = new LinkedList<String>();
					tempList.add(artist);
					throw new ArtistsDontExist(tempList);
				} else {
					if (!shows.containsKey(parsedDate)) {
						Map<String, ShowsInterface> tempMap = new LinkedHashMap<String, ShowsInterface>();
						tempMap.put(name, new ConcertsClass(name, des, cap, artist, parsedDate, price));
						shows.put(parsedDate, tempMap);
					} else {
						shows.get(parsedDate).put(name, new ConcertsClass(name, des, cap, artist, parsedDate, price));
					}
					artists.get(artist).addShow(shows.get(parsedDate).get(name));
				}
			}
		}

	}

	public void addFestival(String name, String desc, int cap, String date, Map<Integer, List<String>> daysOfShow,
			int numberOfDays, int[] price) throws AdminNotOn, ShowAlreadyExists, ArtistsDontExist {

		LocalDate parsedDate = parseToDateru(date);

		if (!(isAdminOn())) {
			throw new AdminNotOn();
		} else {
			if (shows.containsKey(parsedDate) && shows.get(parsedDate).containsKey(name)) {
				throw new ShowAlreadyExists();
			} else {
				List<String> tempList = new LinkedList<String>();
				for (int a = 1; a <= numberOfDays; a++) {
					for (int aa = 0; aa < daysOfShow.get(a).size(); aa++) {
						if ((artists.containsKey(daysOfShow.get(a).get(aa))) == false) {
							tempList.add(daysOfShow.get(a).get(aa));
						}
					}
				}
				if (tempList.size() > 0)
					throw new ArtistsDontExist(tempList);

				if (!shows.containsKey(parsedDate)) {
					Map<String, ShowsInterface> tempMap = new LinkedHashMap<String, ShowsInterface>();
					tempMap.put(name, new FestivalClass(name, desc, cap, parsedDate, daysOfShow, numberOfDays, price));
					shows.put(parsedDate, tempMap);
				} else {
					shows.get(parsedDate).put(name,
							new FestivalClass(name, desc, cap, parsedDate, daysOfShow, numberOfDays, price));
				}
				for (int i = 1; i <= numberOfDays; i++) {
					for (int ii = 0; ii < daysOfShow.get(i).size(); ii++) {
						artists.get(daysOfShow.get(i).get(ii)).addShow(shows.get(parsedDate).get(name));
					}
				}
			}
		}
	}

	public void buyTicketeru(String name, String date, int quantity) throws AdminNotOn, ShowNotExist, NoTickets {
		LocalDate parsedDate = parseToDateru(date);
		if (!(getLoginStatus()) || isAdminOn()) {
			throw new AdminNotOn();
		} else {
			if (!(shows.containsKey(parsedDate) && shows.get(parsedDate).containsKey(name))) {
				throw new ShowNotExist();
			} else {
				if (!(((ConcertInterface) (shows).get(parsedDate).get(name)).canBuyTicket(quantity))) {
					throw new NoTickets();
				} else {
					int price = shows.get(parsedDate).get(name).getPrice(quantity);
					ClientClass client = (ClientClass) users.get(currentUserName);
					client.buyConcertTicketeru(name, quantity, price, parsedDate);
					((ConcertInterface) shows.get(parsedDate).get(name)).buyTicket(quantity);
				}
			}
		}

	}

	public void buyFestivaruTicketeru(String name, String showDate, List<String> date, int quantity)
			throws AdminNotOn, ShowNotExist, NoTickets {

		List<LocalDate> parsedDate = new LinkedList<LocalDate>();
		for (int i = 0; i < date.size(); i++) {
			parsedDate.add(parseToDateru(date.get(i)));
		}
		LocalDate showDateParsed = parseToDateru(showDate);

		if (!(getLoginStatus()) || isAdminOn()) {
			throw new AdminNotOn();
		} else {
			if (!(shows.containsKey(showDateParsed) && shows.get(showDateParsed).containsKey(name))) {
				throw new ShowNotExist();
			} else {
				if (!(((FestivalInterface) (shows).get(showDateParsed).get(name)).canBuyTicketF(parsedDate))) {
					throw new NoTickets();
				} else {
					ClientClass client = (ClientClass) users.get(currentUserName);
					int price = ((FestivalClass) shows.get(showDateParsed).get(name)).getPrice(quantity - 1);
					client.buyFestivalTicketeru(name, quantity, price, parsedDate);
					((FestivalInterface) shows.get(showDateParsed).get(name)).buyTicket(parsedDate);
				}
			}
		}
	}

	public String getUserPw(String mail) {
		return users.get(mail).getPw();

	}

	public int getShowPrice(String name, int quantity, String date) {
		LocalDate parsedDate = parseToDateru(date);
		return shows.get(parsedDate).get(name).getPrice(quantity);
	}

	/**
	 * This method converts a given String date to a LocalDate date and also
	 * converts it to the specified format (""yyyy/MM/dd")
	 * 
	 * @param date
	 * @return
	 */
	private LocalDate parseToDateru(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate parsedDate = LocalDate.parse(date, format);
		return parsedDate;
	}

	public Iterator<ShowsInterface> getShows() {
		List<ShowsInterface> tempList = new LinkedList<ShowsInterface>();
		Iterator<Map<String, ShowsInterface>> it = shows.values().iterator();
		while (it.hasNext()) {
			tempList.addAll(it.next().values());

		}
		return tempList.iterator();
	}

	public ShowsInterface getShow(String name, String date) throws ShowNotExist {
		LocalDate dateru = parseToDateru(date);
		if (!(shows.containsKey(dateru) && shows.get(dateru).containsKey(name))) {
			throw new ShowNotExist();
		} else {
			return shows.get(dateru).get(name);
		}
	}

	public Iterator<ShowsInterface> sortShows() {
		List<ShowsInterface> tempList = new LinkedList<ShowsInterface>();
		Iterator<Map<String, ShowsInterface>> it = shows.values().iterator();
		while (it.hasNext()) {
			tempList.addAll(it.next().values());

		}
		Collections.sort(tempList, new ComparatorByTickets<ShowsInterface>());
		return tempList.iterator();
	}

	public Iterator<Ticket> iterateConcertTickets() {
		return ((ClientInterface) users.get(currentUserName)).getConcertTickets();
	}

	public Iterator<Ticket> iterateFestivalTickets() {
		return ((ClientInterface) users.get(currentUserName)).getFestivalTickets();
	}

	public Iterator<ShowsInterface> getShowByType(String type) throws UnknownShow {
		if ((type.equals("Concert") || type.equals("Festival")) == false) {
			throw new UnknownShow();
		}
		List<ShowsInterface> tempList = new LinkedList<ShowsInterface>();
		Iterator<Map<String, ShowsInterface>> it = shows.values().iterator();
		while (it.hasNext()) {
			tempList.addAll(it.next().values());

		}
		Collections.sort(tempList, new ComparatorByDate<ShowsInterface>());
		return tempList.iterator();

	}

	public Iterator<ShowsInterface> getArtistShow(String name) {
		return artists.get(name).getArtistShows();
	}

	public boolean existsArtist(String name) {
		return (artists.containsKey(name));
	}
}