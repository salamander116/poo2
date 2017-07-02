import java.time.LocalDate;
import java.util.*;

import bilhiate.*;
import exceptions.*;
import findConcertTicket.*;
import shows.*;
/**
 * 
 * @author 50503_50647
 *
 */
public class Main {
	
	
	// constantes de prints
	private static final String FESTIVAL = "Festival";
	private static final String SHOW_ADDED = "Show added.";
	private static final String ARTIST_ADDED = "Artist added.";
	private static final String TICKET_BOUGHT_WITH_COST_D = "Ticket bought with cost %d.\n";
	private static final String CONCERT_OR_FESTIVAL = "CONCERT OR FESTIVAL?";
	private static final String ALL_SHOWS = "All shows:";
	private static final String MOST_SOLD_SHOWS = "Most sold shows:";
	private static final String FESTIVALS = "Festivals:";
	private static final String CONCERTS = "Concerts:";
	private static final String CONCERT = "Concert";
	private static final String FESTIVALS_WHERE_S_WILL_PLAY = "Festivals where %s will play:\n";
	private static final String CONCERTS_OF_S = "Concerts of %s:\n";
	private static final String MY_TICKETS = "My tickets:";
	private static final String GOODBYE = "Goodbye ";
	private static final String WELCOME = "Welcome ";
	private static final String USER_WAS_REGISTERED_S = "User was registered: %s\n";
	
	//constantes de comandos
	private static final String QUIT = "EXIT";
	private static final String REGIST_USER = "REGISTER";
	private static final String LOGIN = "LOGIN";
	private static final String LOGOUT = "LOGOUT";
	private static final String ADD_SHOW = "ADDSHOW";
	private static final String ADD_ARTIST = "ADDARTIST";
	private static final String BUY_TICKET = "BUYTICKET";
	private static final String LIST_SHOWS = "SHOWS";
	private static final String LIST_SHOWS_CLIENTS = "SHOWSBYCLIENTS";
	private static final String LIST_SHOWS_TYPE = "SHOWSBYTYPE";
	private static final String GET_SHOW = "SHOW";
	private static final String GET_ARTIST = "SEARCH";
	private static final String LIST_TICKETS = "MYTICKETS";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		FindConcertTicketInterface fc = new FindConcertTicket();
		String op = getCommand(in);

		while (!op.equals(QUIT)) {
			switch (op) {
			case REGIST_USER:
				registUser(in, fc);
				break;
			case LOGIN:
				userLogin(in, fc);
				break;
			case LOGOUT:
				userLogout(in, fc);
				break;
			case ADD_SHOW:
				addShow(in, fc);
				break;
			case ADD_ARTIST:
				addArtist(in, fc);
				break;
			case BUY_TICKET:
				buyTicket(in, fc);
				break;
			case LIST_SHOWS:
				listAllShows(fc);
				break;
			case LIST_SHOWS_CLIENTS:
				listShowsByClients(fc);
				break;
			case LIST_SHOWS_TYPE:
				listShowsByType(in, fc);
				break;
			case GET_SHOW:
				getShow(in, fc);
				break;
			case GET_ARTIST:
				getArtist(in, fc);
				break;
			case LIST_TICKETS:
				listUserTickers(in, fc);
				break;

			}
			System.out.println();
			op = getCommand(in);
		}
		System.out.println("Exiting.");
		System.out.println();
		in.close();

	}

	private static String getCommand(Scanner in) {
		String s;
		s = in.nextLine().toUpperCase();
		return s;
	}
	
	private static void registUser(Scanner in, FindConcertTicketInterface fc) {
		String type = in.nextLine().toUpperCase();
		String email = in.nextLine();

		try {
			fc.registUser(type, email);
			String pw = fc.getUserPw(email);
			System.out.printf(USER_WAS_REGISTERED_S, pw);
		} catch (UserLoggedIn e) {
			System.out.println(e.getMessage());

		} catch (UserAlreayExists e) {
			System.out.println(e.getMessage());

		}

	}

	private static void userLogin(Scanner in, FindConcertTicketInterface fc) {

		String email = in.nextLine();
		String pw = in.nextLine();

		try {
			fc.login(email, pw);
			System.out.println(WELCOME + email);
		} catch (UserNotExist e) {
			System.out.println(e.getMessage());
		} catch (UserLoggedIn e) {
			System.out.println(e.getMessage());
		} catch (AnotherUserLoggedIn e) {
			System.out.println(e.getMessage());
		} catch (WrongPassword e) {
			System.out.println(e.getMessage());
		}

	}

	private static void userLogout(Scanner in, FindConcertTicketInterface fc) {

		try {
			String currentUser = fc.getCurrentUserName();
			fc.logOut();
			System.out.println(GOODBYE + currentUser);
		} catch (NoUserLoggedIn e) {
			System.out.println(e.getMessage());
		}

	}

	private static void listUserTickers(Scanner in, FindConcertTicketInterface fc) {

		System.out.println(MY_TICKETS);
		Iterator<Ticket> it = fc.iterateConcertTickets();
		while (it.hasNext()) {
			Ticket ticketC = it.next();
			System.out.println(((ConcertTicket) ticketC).getName());
			System.out.println(((ConcertTicket) ticketC).getDate());
			System.out.println(((ConcertTicket) ticketC).getTotalTickets());
			System.out.println(((ConcertTicket) ticketC).getTotalPrice());
		}

		Iterator<Ticket> its = fc.iterateFestivalTickets();
		while (its.hasNext()) {
			Ticket ticketF = its.next();
			System.out.println(ticketF.getName());
			Iterator<LocalDate> itd = ((FestivalTicket) ticketF).returnDates();
			while (itd.hasNext()) {
				System.out.println(itd.next());
			}
			System.out.println(ticketF.getTotalPrice());
		}
	}

	private static void getArtist(Scanner in, FindConcertTicketInterface fc) {

		String name = in.nextLine();
		if (fc.existsArtist(name) == false) {
			System.out.printf(CONCERTS_OF_S, name);
			System.out.printf(FESTIVALS_WHERE_S_WILL_PLAY, name);
		} else {
			Iterator<ShowsInterface> itC = fc.getArtistShow(name);
			System.out.printf(CONCERTS_OF_S, name);
			while (itC.hasNext()) {
				ShowsInterface show = itC.next();
				if (show instanceof ConcertInterface) {
					System.out.println(((ConcertInterface) show).getName());
					System.out.println(((ConcertInterface) show).getArtist());
					System.out.println(((ConcertInterface) show).getStartDate());
					System.out.println(((ConcertInterface) show).getShowPrice());
					System.out.println(((ConcertInterface) show).getCap());
				}
			}
			Iterator<ShowsInterface> itF = fc.getArtistShow(name);
			System.out.printf(FESTIVALS_WHERE_S_WILL_PLAY, name);
			while (itF.hasNext()) {
				ShowsInterface showF = itF.next();
				if (showF instanceof FestivalInterface) {
					System.out.println(((FestivalInterface) showF).getName());
					for (int i = 1; i <= ((FestivalInterface) showF).getNumberOfDays(); i++) {
						Iterator<String> its = ((FestivalInterface) showF).iterateDay(i);
						System.out.println(showF.getStartDate().plusDays(i - 1));
						while (its.hasNext()) {
							System.out.println(its.next());
						}
					}

					System.out.println(showF.getStartDate());
					System.out
							.println(showF.getStartDate().plusDays(((FestivalInterface) showF).getNumberOfDays() - 1));
					for (int i = 0; i < ((FestivalInterface) showF).getNumberOfDays(); i++) {
						System.out.println((i + 1) + " " + ((FestivalInterface) showF).getPrice(i));
					}
					for (int i = 0; i < ((FestivalInterface) showF).getNumberOfDays(); i++) {
						System.out.println(
								showF.getStartDate().plusDays(i) + " " + ((FestivalInterface) showF).getCap(i));
					}
				}
			}
		}

	}

	private static void getShow(Scanner in, FindConcertTicketInterface fc) {

		try {
			String nemu = in.nextLine();
			String date = in.nextLine();
			ShowsInterface show = fc.getShow(nemu, date);
			System.out.println(nemu + " on " + date + ":");
			if (show instanceof ConcertInterface) {
				System.out.println(((ConcertInterface) show).getName());
				System.out.println(((ConcertInterface) show).getArtist());
				System.out.println(((ConcertInterface) show).getStartDate());
				System.out.println(((ConcertInterface) show).getShowPrice());
				System.out.println(((ConcertInterface) show).getCap());
			} else {
				System.out.println(((FestivalInterface) show).getName());
				for (int i = 1; i <= ((FestivalInterface) show).getNumberOfDays(); i++) {
					Iterator<String> its = ((FestivalInterface) show).iterateDay(i);
					System.out.println(show.getStartDate().plusDays(i - 1));
					while (its.hasNext()) {
						System.out.println(its.next());
					}
				}
				System.out.println(show.getStartDate());
				System.out.println(show.getStartDate().plusDays(((FestivalInterface) show).getNumberOfDays() - 1));
				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println((i + 1) + " " + ((FestivalInterface) show).getPrice(i));
				}
				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println(show.getStartDate().plusDays(i) + " " + ((FestivalInterface) show).getCap(i));
				}

			}
		} catch (ShowNotExist e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listShowsByType(Scanner in, FindConcertTicketInterface fc) {

		try {
			String type = in.nextLine();
			Iterator<ShowsInterface> it = fc.getShowByType(type);
			if (type.equals(CONCERT)) {
				System.out.println(CONCERTS);
				while (it.hasNext()) {
					ShowsInterface show = it.next();
					if (show instanceof ConcertInterface) {
						System.out.println(((ConcertInterface) show).getName());
						System.out.println(((ConcertInterface) show).getArtist());
						System.out.println(((ConcertInterface) show).getStartDate());
						System.out.println(((ConcertInterface) show).getShowPrice());
						System.out.println(((ConcertInterface) show).getCap());
					}

				}
			} else {
				System.out.println(FESTIVALS);
				while (it.hasNext()) {
					ShowsInterface showF = it.next();
					if (showF instanceof FestivalInterface) {
						System.out.println(showF.getName());
						for (int i = 1; i <= ((FestivalInterface) showF).getNumberOfDays(); i++) {
							Iterator<String> its = ((FestivalInterface) showF).iterateDay(i);
							System.out.println(showF.getStartDate().plusDays(i - 1));
							while (its.hasNext()) {
								System.out.println(its.next());
							}
						}
						System.out.println(showF.getStartDate());
						System.out.println(
								showF.getStartDate().plusDays(((FestivalInterface) showF).getNumberOfDays() - 1));

						for (int i = 0; i < ((FestivalInterface) showF).getNumberOfDays(); i++) {
							System.out.println((i + 1) + " " + ((FestivalInterface) showF).getPrice(i));
						}
						for (int i = 0; i < ((FestivalInterface) showF).getNumberOfDays(); i++) {
							System.out.println(
									showF.getStartDate().plusDays(i) + " " + ((FestivalInterface) showF).getCap(i));
						}

					}
				}
			}
		} catch (UnknownShow e) {
			System.out.println(e.getMessage());

		}

	}

	private static void listShowsByClients(FindConcertTicketInterface fc) {

		Iterator<ShowsInterface> it = fc.sortShows();
		System.out.println(MOST_SOLD_SHOWS);
		while (it.hasNext()) {
			ShowsInterface show = it.next();
			if (show instanceof ConcertInterface) {
				System.out.println(show.getName());
				System.out.println(((ConcertInterface) show).getArtist());
				System.out.println(show.getStartDate());
				System.out.println(((ConcertInterface) show).getShowPrice());
				System.out.println(((ConcertInterface) show).getCap());
			} else {
				System.out.println(show.getName());
				for (int i = 1; i <= ((FestivalInterface) show).getNumberOfDays(); i++) {
					Iterator<String> its = ((FestivalInterface) show).iterateDay(i);
					System.out.println(show.getStartDate().plusDays(i-1));
					while (its.hasNext()) {
						System.out.println(its.next());
					}
				}
				System.out.println(show.getStartDate());
				System.out.println(show.getStartDate().plusDays(((FestivalInterface) show).getNumberOfDays() - 1));

				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println((i + 1) + " " + ((FestivalInterface) show).getPrice(i));
				}
				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println(show.getStartDate().plusDays(i) + " " + ((FestivalInterface) show).getCap(i));
				}

			}
		}

	}

	private static void listAllShows(FindConcertTicketInterface fc) {
		Iterator<ShowsInterface> it = fc.getShows();
		System.out.println(ALL_SHOWS);
		while (it.hasNext()) {
			ShowsInterface show = it.next();
			if (show instanceof ConcertInterface) {
				System.out.println(show.getName());
				System.out.println(((ConcertInterface) show).getArtist());
				System.out.println(show.getStartDate());
				System.out.println(((ConcertInterface) show).getShowPrice());
				System.out.println(((ConcertInterface) show).getCap());
			} else {

				System.out.println(show.getName());
				for (int i = 1; i <= ((FestivalInterface) show).getNumberOfDays(); i++) {
					Iterator<String> its = ((FestivalInterface) show).iterateDay(i);
					System.out.println(show.getStartDate().plusDays(i - 1));
					while (its.hasNext()) {
						System.out.println(its.next());
					}
				}

				System.out.println(show.getStartDate());
				System.out.println(show.getStartDate().plusDays(((FestivalInterface) show).getNumberOfDays() - 1));

				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println((i + 1) + " " + ((FestivalInterface) show).getPrice(i));
				}
				for (int i = 0; i < ((FestivalInterface) show).getNumberOfDays(); i++) {
					System.out.println(show.getStartDate().plusDays(i) + " " + ((FestivalInterface) show).getCap(i));
				}
			}
		}
	}

	private static void buyTicket(Scanner in, FindConcertTicketInterface fc) {

		try {
			String name = in.nextLine();
			String date = in.nextLine();
			System.out.println(CONCERT_OR_FESTIVAL);
			String showType = in.nextLine();
			if (showType.equals(CONCERT)) {
				int quantity = in.nextInt();
				in.nextLine();
				fc.buyTicketeru(name, date, quantity);
				int totalPrice = fc.getShowPrice(name, quantity, date);
				System.out.printf(TICKET_BOUGHT_WITH_COST_D, totalPrice);

			} else {
				if (showType.equals(FESTIVAL)) {
					int quantity = in.nextInt();
					in.nextLine();
					List<String> dates = new LinkedList<String>();
					for (int i = 0; i < quantity; i++) {
						dates.add(in.nextLine());
					}
					fc.buyFestivaruTicketeru(name, date, dates, quantity);
					int festivalPrice = fc.getShowPrice(name, quantity - 1, date);
					System.out.printf(TICKET_BOUGHT_WITH_COST_D, festivalPrice);
				}
			}
		} catch (AdminNotOn e) {
			System.out.println(e.getMessage());

		} catch (ShowNotExist e) {
			System.out.println(e.getMessage());

		} catch (NoTickets e) {
			System.out.println(e.getMessage());

		}
	}

	private static void addArtist(Scanner in, FindConcertTicketInterface fc) {

		try {
			String name = in.nextLine();
			int albumNum = in.nextInt();
			in.nextLine();
			String[] albums = new String[albumNum];
			for (int i = 0; i < albumNum; i++) {
				albums[i] = in.nextLine();
			}

			int bandNumber = in.nextInt();
			in.nextLine();

			if (bandNumber == 1) {
				fc.addArtists(name, albumNum, albums);
				System.out.println(ARTIST_ADDED);

			} else {

				String[] bandElements = new String[bandNumber];
				for (int i = 0; i < bandNumber; i++) {
					bandElements[i] = in.nextLine();
				}

				fc.addBand(name, albumNum, albums, bandNumber, bandElements);
				System.out.println(ARTIST_ADDED);

			}

		} catch (AdminNotOn e) {
			System.out.println(e.getMessage());

		} catch (BandAlreadyExists e) {
			System.out.println(e.getMessage());
		}

	}

	private static void addShow(Scanner in, FindConcertTicketInterface fc) {

		try {
			String name = in.nextLine();
			String desc = in.nextLine();
			int cap = in.nextInt();
			in.nextLine();
			System.out.println(CONCERT_OR_FESTIVAL);
			String show = in.nextLine();
			if (show.equals(CONCERT)) {
				String artist = in.nextLine();
				String date = in.nextLine();
				int price = in.nextInt();
				in.nextLine();
				fc.addConcert(name, desc, cap, artist, date, price);
				System.out.println(SHOW_ADDED);

			} else {
				if (show.equals(FESTIVAL)) {

					int days = in.nextInt();
					in.nextLine();
					String startDate = in.nextLine();

					Map<Integer, List<String>> daysOfShow = new HashMap<Integer, List<String>>();
					for (int i = 1; i <= days; i++) {
						int artistsPerDay = in.nextInt();
						in.nextLine();
						List<String> tempList = new LinkedList<String>();
						for (int ii = 0; ii < artistsPerDay; ii++) {
							tempList.add(in.nextLine());

						}
						daysOfShow.put(i, tempList);
					}

					int[] prices = new int[days];
					for (int a = 0; a < days; a++) {
						in.nextInt();
						int pricePerDay = in.nextInt();
						in.nextLine();
						prices[a] = pricePerDay;
					}

					fc.addFestival(name, desc, cap, startDate, daysOfShow, days, prices);
					System.out.println(SHOW_ADDED);
				}

			}
		} catch (AdminNotOn e) {
			System.out.println(e.getMessage());
		} catch (ShowAlreadyExists e) {
			System.out.println(e.getMessage());
		} catch (ArtistsDontExist e) {
			System.out.println(e.getMessage());
			Iterator<String> itA = e.iterateArtistDontExist();
			while (itA.hasNext()) {
				System.out.println(itA.next());
			}
		}

	}

}
