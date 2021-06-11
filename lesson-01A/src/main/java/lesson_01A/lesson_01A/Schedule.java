package lesson_01A.lesson_01A;

import java.util.Map;

import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Schedule implements Comparable<Schedule> {
	
	private String name = "Schedule of cinema";
	private Set<Seance> schedule = new TreeSet<>();
	private Map<Time, Boolean> isTimeFree;
	private Map<Days, Seance> scheduleSeance;
	
	private final Time technicalBreakTime = new Time(0, 25);
	private final Movie technicalBreakMovie = new Movie("Break", technicalBreakTime);
	
	public Schedule() throws TimeException{
		this.name = name;
		this.schedule = new TreeSet<Seance>();
		this.scheduleSeance = new TreeMap<Days, Seance>();
		Time maxTime = Time.getMaxValue();
		for (int t = 0; t <= Time.timeToMinutes(maxTime); t++) {
			isTimeFree.put(Time.minutesToTime(t), true);
		}
	}
	
	public Map<Time,Boolean> getIsTimeFree(){
		return isTimeFree;
	}
	
	public Set<Seance> getSchedule() {
		return schedule;
	}

	public Map<Days, Seance> getScheduleSeance() {
		return scheduleSeance;
	}

	public String getName() {
		return name;
	}
	
	public Optional<Seance> getMovieSeanceFromSet(Movie movie) {
		Optional<Seance> seanceFound = schedule.stream().
				filter(entry -> entry.getMovie().
				equals(movie)).findFirst();
		
		return seanceFound;
	}
	
	public Schedule inputSchedule() throws TimeException{
		return new Schedule();
	}
	

	public boolean isSeanceTimeFree(Seance seance)throws TimeException {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();
		boolean isSeanceTimeFree = true;

		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			if (!isTimeFree.get(Time.minutesToTime(t))) {
				isSeanceTimeFree = false;
				break;
			}
		}
		return isSeanceTimeFree;
	}

	public void registerSeanceInSchedule(Seance seance)throws TimeException {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();

		schedule.add(seance);
		schedule.add(new Seance(technicalBreakMovie, Time.sumTime(seanceEndTime, new Time(0, 1))));
		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			isTimeFree.replace(Time.minutesToTime(t), false);
		}
	}

	public boolean addSeance(Movie movie)throws TimeException {
		Seance seance = Seance.inputSeance(movie);
		return true;
	}

	public Optional<Seance> findSeance(Seance seance2) {
		Predicate<Seance> isEqual = seance -> seance.getMovie().getTitle()
				.equalsIgnoreCase(seance2.getMovie().getTitle()) && seance.getStartTime().equals(seance2.getStartTime())
				&& seance.getEndTime().equals(seance2.getEndTime());
		Optional<Seance> seanceFound = schedule.stream().filter(isEqual).findFirst();

		return seanceFound;
	}

	public void unregisterSeanceInSchedule(Seance seance, Optional<Seance> seanceFound)throws TimeException {
		Time seanceStartTime = seance.getStartTime();
		Time seanceEndTime = seance.getEndTime();

		schedule.remove(seanceFound.get());
		schedule.remove(new Seance(technicalBreakMovie, Time.sumTime(seanceEndTime, new Time(0, 1))));
		for (int t = Time.timeToMinutes(seanceStartTime); t <= Time
				.timeToMinutes(Time.sumTime(seanceEndTime, technicalBreakTime)); t++) {
			isTimeFree.replace(Time.minutesToTime(t), true);
		}
	}

	public boolean removeSeance(Seance removingSeance) throws TimeException{
		Optional<Seance> removingSeanceFound = findSeance(removingSeance);

		if (removingSeanceFound.isPresent()) {
			unregisterSeanceInSchedule(removingSeance, removingSeanceFound);
			System.out.println("Seance is deleted");
			return true;
		} else {
			System.out.println("Don't have this seance ");
			return false;
		}
	}

	public void viewSchedule() {
		System.out.println("Schedule :");
		schedule.forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isTimeFree == null) ? 0 : isTimeFree.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((scheduleSeance == null) ? 0 : scheduleSeance.hashCode());
		result = prime * result + ((technicalBreakMovie == null) ? 0 : technicalBreakMovie.hashCode());
		result = prime * result + ((technicalBreakTime == null) ? 0 : technicalBreakTime.hashCode());
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
		Schedule other = (Schedule) obj;
		if (isTimeFree == null) {
			if (other.isTimeFree != null)
				return false;
		} else if (!isTimeFree.equals(other.isTimeFree))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (scheduleSeance == null) {
			if (other.scheduleSeance != null)
				return false;
		} else if (!scheduleSeance.equals(other.scheduleSeance))
			return false;
		if (technicalBreakMovie == null) {
			if (other.technicalBreakMovie != null)
				return false;
		} else if (!technicalBreakMovie.equals(other.technicalBreakMovie))
			return false;
		if (technicalBreakTime == null) {
			if (other.technicalBreakTime != null)
				return false;
		} else if (!technicalBreakTime.equals(other.technicalBreakTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Seance seance : schedule) {
			sb.append(seance.toString() + "\n");
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Schedule other) {
		return this.name.compareTo(other.getName());
	}

	
	
}
