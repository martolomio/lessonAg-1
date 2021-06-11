package lesson_01A.lesson_01A;

import java.util.Scanner;

public enum Days {
	
	MONDAY(01),
	TUESDAY(02),
	WEDNESDAY(03), 
	THURSDAY(04),
	FRIDAY(05), 
	SATURDAY(06),
	SUNDAY(07);

	int numberOfDay;
	
	Days(int numberOfDay){
		this.numberOfDay= numberOfDay;
	}

	public int getNumberOfDay() {
		return numberOfDay;
	}
	
	public String toLiteral(boolean name) {
		String dayToLiteralName;
		switch (Days.this) {
		case MONDAY:
			dayToLiteralName = "MONDAY";
			break;
		case TUESDAY:
			dayToLiteralName = "TUESDAY";
			break;
		case WEDNESDAY:
			dayToLiteralName = "WEDNESDAY";
			break;
		case THURSDAY:
			dayToLiteralName = "THURSDAY";
			break;
		case FRIDAY:
			dayToLiteralName = "FRIDAY";
			break;
		case SATURDAY:
			dayToLiteralName = "SATURDAY";
			break;
		case SUNDAY:
			dayToLiteralName = "SUNDAY";
			break;
		default:
			dayToLiteralName = "";
			break;
		}
		return dayToLiteralName;
	}
	
	public static Days inputDay() {
		
		try (Scanner scanner = new Scanner(System.in)) {
			Integer returnNumber = 0;
			Days days = null;
			
			System.out.println("Enter number from 01 to 07 :");
			if(scanner.hasNext()) {
				int nextInt = scanner.nextInt();
				if(nextInt > 0 && nextInt <= Days.values().length) {
					returnNumber = nextInt;	
				}
			}else 
				System.out.println("Wrong number of day");
			
			for(Days day : Days.values()) {
				if(day.getNumberOfDay() == returnNumber) {
					days = day;
				}
			}
			return days;
		}
	}
	
}
