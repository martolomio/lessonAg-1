package lesson_01A.lesson_01A;

import java.util.Scanner;

public class Movie {
	
	private String title;
	private Time duraction;
	
	public Movie(String title,Time duraction) {
		this.title = title;
		this.duraction =duraction;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Time getDuration() {
		return duraction;
	}

	public void setDuration(Time duraction) {
		this.duraction = duraction;
	}

	public static Movie inputMovie() throws TimeException {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter name of movie: ");
		String title = scanner.nextLine();
		if (title.equals("")) {
			System.err.println("Wrong name");
			title = "Example: Forest Gamb";
		}

		System.out.print("duraction - ");
		Time duration = Time.inputTime();

		return new Movie(title, duration);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duraction == null) ? 0 : duraction.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (duraction == null) {
			if (other.duraction != null)
				return false;
		} else if (!duraction.equals(other.duraction))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie \"" + title + "\", duraction=" + duraction + "]";
	}

}