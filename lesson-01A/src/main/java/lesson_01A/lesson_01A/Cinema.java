package lesson_01A.lesson_01A;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

public class Cinema{

	
	private String name;
	private Time open;
	private Time close;
	private TimeOfDay cinemaTimeTable;
	private TreeSet<Hall> cinemaHalls;

	public Cinema(String name, Time open, Time close) {
		this.name = name;
		this.open = open;
		this.close = close;
		this.cinemaTimeTable = new TimeOfDay();
		this.cinemaHalls = new TreeSet<Hall>();
	}

	public String getName() {
		return name;
	}

	public static Cinema inputCinema()throws TimeException  {
	
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter name of cinema : ");
		String name = scanner.nextLine();
		if (name.equals("")) {
			System.out.println("Wrong entered cinema ");
			name = "Cinema";
		}
		Time open = Time.inputTime();
		Time close = Time.inputTime();
		System.out.println("Cinema \"" + name.toString() + "\"is created \n"+ " Opening "+ open + "\" Closing" + close+"\n");
		return new Cinema(name,open,close);
	}

	public void addHallToCinema() {
		Hall cinemaHall = Hall.inputHall();
		cinemaHalls.add(cinemaHall);
		System.out.println("Hall \"" + cinemaHall.getName() + "\" edded \"" + name + "\"!\n");
	}

	public Optional<Hall> getHallFromSet(Hall cinemaHall) {
		Optional<Hall> hallFound = cinemaHalls.stream().filter(entry -> entry.getName().equals(cinemaHall.getName()))
				.findFirst();

		return hallFound;
	}

	public boolean removeHallFromCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			cinemaHalls.remove(hallFound.get());
			System.out.println(
					"Hall \"" + hallFound.get().getName() + "\" deleted \"" + name + "\"!\n");
			return true;
		} else {
			System.out.println("Hall \"" + cinemaHall.getName() + "\" unfounded \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addTimeTableToHallInCinema()throws TimeException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addTimeTableToHall();

			if (isDone) {
				System.out.println("Schedule \"" + hallFound.get() + "\" edded \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Changes in Schedule \"" + name + "\" don't edded\n");
				return false;
			}
		} else {
			System.err.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeTimeTableFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeTimeTableFromHall();

			if (isDone) {
				System.out.println("Schedule \"" + hallFound.get() + "\" deleted \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Changes in Schedule \"" + name + "\" don't edded\n");
				return false;
			}
		} else {
			System.err.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addScheduleToHallInCinema()throws TimeException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().addScheduleToHall();

			if (isDone) {
				System.out.println("Schedule \"" + hallFound.get() + "\" edded \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Changes in Schedule\"" + hallFound.get() + "\" in cinema \""
						+ name + "\" don't changed!\n");
				return false;
			}
		} else {
			System.out.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeScheduleFromHallInCinema() {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			boolean isDone = hallFound.get().removeScheduleFromHall();

			if (isDone) {
				System.out.println("Schedule \"" + hallFound.get() + "\" deleted \""
						+ name + "\"!\n");
				return true;
			} else {
				System.out.println("Schedule for \"" + hallFound.get() + "\" in cinema \""
						+ name + "\" don't changed\n");
				return false;
			}
		} else {
			System.err.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean addSeanceToScheduleInHallInCinema()throws TimeException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				boolean isDone = hallScheduleEntryFound.get().getValue().addSeance(movie);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.out.println(day.toLiteral(true) + " don't have in \"" + hallFound.get()
						+ "\" cinema \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.out.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public boolean removeSeanceFromScheduleInHallInCinema()throws TimeException {
		Hall cinemaHall = Hall.inputHall();

		Optional<Hall> hallFound = getHallFromSet(cinemaHall);

		if (hallFound.isPresent()) {
			Days day = Days.inputDay();
			if (day == null)
				return false;

			Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
					.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

			if (hallScheduleEntryFound.isPresent()) {
				Movie movie = Movie.inputMovie();
				Seance removingSeance = Seance.inputSeance(movie);
				boolean isDone = hallScheduleEntryFound.get().getValue().removeSeance(removingSeance);

				if (isDone)
					return true;
				else
					return false;
			} else {
				System.err.println(day.toLiteral(true) + " don't have in schedule from  \"" + hallFound.get()
						+ "\" cinema \"" + name + "\"!\n");
				return false;
			}
		} else {
			System.err.println("Hall \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			return false;
		}
	}

	public void addMovieToSeanceInScheduleInHallInCinema()throws TimeException {
		Movie movie = Movie.inputMovie();

		boolean addOneMoreSeance;

		do {
			Hall cinemaHall = Hall.inputHall();

			Optional<Hall> hallFound = getHallFromSet(cinemaHall);

			if (hallFound.isPresent()) {
				Days day = Days.inputDay();
				if (day == null)
					return;

				Optional<Entry<Days, Schedule>> hallScheduleEntryFound = hallFound.get().getHallSchedule().entrySet()
						.stream().filter(entry -> entry.getKey().equals(day)).findFirst();

				if (hallScheduleEntryFound.isPresent()) {
					hallScheduleEntryFound.get().getValue().addSeance(movie);
				} else {
					System.out.println(day.toLiteral(true) + "don't have in schedule from \""
							+ hallFound.get() + "\" cinema \"" + name + "\"!\n");
				}
			} else {
				System.out.println("��� \"" + cinemaHall.getName() + "\" unknown \"" + name + "\"!\n");
			}

			
			Scanner scanner = new Scanner(System.in);

			System.out.print("Add new seanse? (true/false) ");
			addOneMoreSeance = scanner.nextBoolean();

			
		} while (addOneMoreSeance);
	}

	public void removeMovieFromSeanceInScheduleInAllHallsInCinema()throws TimeException {
		Movie movie = Movie.inputMovie();
		boolean isDone = false;

		for (Hall hall : cinemaHalls) {
			for (Days day : Days.values()) {
				Optional<Seance> seance = hall.getHallSchedule().entrySet().stream()
						.filter(entry -> entry.getKey().equals(day)).findFirst().get().getValue()
						.getMovieSeanceFromSet(movie);

				if (seance.isPresent()) {
					hall.getHallSchedule().entrySet().stream().filter(entry -> entry.getKey().equals(day)).findFirst()
							.get().getValue().removeSeance(seance.get());
					isDone = true;
				} else
					break;
			}
		}

		if (isDone) {
			System.out.println(movie.toString() + " deleted\n");
		} else {
			System.err.println("Deleted " + movie.toString()
					+ " is didn't done\n");
		}
	}

	public void viewCinemaHalls() {
		System.out.println("Halls \"" + name + "\":");
		cinemaHalls.forEach(System.out::println);
		System.out.println();
	}


	@Override
	public String toString() {
		if (name == "Forest") {
			return (String) name;
		} else
			return "Movie \"" + name + "\"";
	}
}

