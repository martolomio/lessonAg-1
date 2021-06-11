package lesson_01A.lesson_01A;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Function;


public class TimeOfDay {

	private Map<Days, ArrayList<Time>> timeOfDay;

	public TimeOfDay() {
		this.timeOfDay = new TreeMap<Days, ArrayList<Time>>();
	}

	public Optional<Entry<Days, ArrayList<Time>>> findDayInTimeTable(Days day) {
		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = timeOfDay.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();
		return timeTableEntryFound;
	}

	public Time getOpeningTime(Days day) {
		return timeOfDay.get(day).get(0);
	}

	public Time getClosingTime(Days day) {
		return timeOfDay.get(day).get(1);
	}

	public boolean addTimeTableEntry()throws TimeException {
		Days day = Days.inputDay();
		if (day == null)
			return false;

		System.out.print("Openind time - ");
		Time openingTime = Time.inputTime();

		System.out.print("Closing time - ");
		Time closingTime = Time.inputTime();

		if (Time.timeToMinutes(closingTime) <= Time.timeToMinutes(openingTime)) {
			System.out.println("Wrong time !\n");
			return false;
		}

		timeOfDay.put(day, new ArrayList<Time>(Arrays.asList(openingTime, closingTime)));
		System.out.println("Time duraction " + day.toLiteral(true) + " edded\n");
		return true;
	}

	public boolean removeTimeTableEntry() {
		Days day = Days.inputDay();
		if (day == null)
			return false;

		Optional<Entry<Days, ArrayList<Time>>> timeTableEntryFound = findDayInTimeTable(day);

		if (timeTableEntryFound.isPresent()) {
			timeOfDay.remove(timeTableEntryFound.get().getKey());
			System.out.println("Time duraction " + day.toLiteral(true) + " deleted\n");
			return true;
		}
			return false;
		
	}
	
	public Function<Entry<Days, ArrayList<Time>>, String> timeTableToString () {
		return entry -> entry.getKey().toLiteral(false) + " " + entry.getValue().get(0).toString()
				+ " - " + entry.getValue().get(1).toString();
	}
		
	public void viewTimeTable() {
		System.out.println("Schedule :");
		timeOfDay.entrySet().stream().map(timeTableToString()).forEach(System.out::println);
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeOfDay == null) ? 0 : timeOfDay.hashCode());
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
		TimeOfDay other = (TimeOfDay) obj;
		if (timeOfDay == null) {
			if (other.timeOfDay != null)
				return false;
		} else if (!timeOfDay.equals(other.timeOfDay))
			return false;
		return true;
	}

}
