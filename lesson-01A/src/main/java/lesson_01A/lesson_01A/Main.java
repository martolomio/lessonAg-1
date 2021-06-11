package lesson_01A.lesson_01A;

import java.util.Scanner;

public class Main {
	public static int menu() {
		System.out.println("1 - create cinema");
		System.out.println("2 - create hall");
		System.out.println("3 - show halls");
		System.out.println("4 - delete hall ");
		System.out.println("5 - create schedule on Day");
		System.out.println("6 - delete schedule on Day");
		System.out.println("7 - �������� ������� ���� �� ����");
		System.out.println("8 - �������� ������� ���� �� ����");
		System.out.println("9 - edd movie");
		System.out.println("10 - delete movie");
		System.out.println("0 - exint");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of list --->   ");
		int menuChoise = scanner.nextInt();

		return menuChoise;
	}

	public static void main(String[] args) throws TimeException {
		Cinema cinema = null;

		while (true) {
			switch (menu()) {

			case 1: {
				cinema = Cinema.inputCinema();
				break;
			}
			case 2: {
				cinema.addHallToCinema();
				break;
			}
			case 4: {
				cinema.removeHallFromCinema();
				break;
			}
			case 3: {
				cinema.viewCinemaHalls();
				break;
			}
			case 5: {
				cinema.addTimeTableToHallInCinema();
				break;
			}
			case 6: {
				cinema.removeTimeTableFromHallInCinema();
				break;
			}
			case 7: {
				cinema.addScheduleToHallInCinema();
				break;
			}
			case 8: {
				cinema.removeScheduleFromHallInCinema();
				break;
			}
			case 9: {
				cinema.addMovieToSeanceInScheduleInHallInCinema();
				break;
			}
			case 10: {
				cinema.removeMovieFromSeanceInScheduleInAllHallsInCinema();
				break;
			}
			case 0: {
				System.exit(0);
				break;
			}
			default: {
				System.err.println("exeption, make you'r chooise");
				break;
			}
			}
		}
	}

	
}
	
	

