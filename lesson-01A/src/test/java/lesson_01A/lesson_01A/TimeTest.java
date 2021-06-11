package lesson_01A.lesson_01A;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class TimeTest {

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... SUCCEED!");
		};

		protected void failed(Throwable exception, org.junit.runner.Description description) {
			System.out.println("Executing " + description.getMethodName() + "... FAILED!");
		};
	};

	@Test
	public void timeTest1() throws TimeException {
		new lesson_01A.lesson_01A.Time(10, 15);
	}

	@Test(expected = TimeException.class)
	public void timeTest2() throws TimeException {
		new lesson_01A.lesson_01A.Time(10, 75);
	}

	@Test(expected = TimeException.class)
	public void timeTest3() throws TimeException {
		new lesson_01A.lesson_01A.Time(25, 15);
	}

	@Test
	public void toLiteralTest1() throws TimeException {
		lesson_01A.lesson_01A.Time time = new lesson_01A.lesson_01A.Time(2, 15);
		String actualString = time.toLiteral();
		String expectedString = "2 hour. 15 min.";
		Assert.assertEquals(expectedString, actualString);
	}

	@Test
	public void toLiteralTest2() throws TimeException {
		lesson_01A.lesson_01A.Time time = new lesson_01A.lesson_01A.Time(0, 15);
		String actualString = ((lesson_01A.lesson_01A.Time) time).toLiteral();
		String expectedString = "15 min.";
		Assert.assertEquals(expectedString, actualString);
	}



	@Test
	public void sumTimeTest1() throws TimeException {
		lesson_01A.lesson_01A.Time actualSum = lesson_01A.lesson_01A.Time.sumTime(new lesson_01A.lesson_01A.Time(10, 15),
				new lesson_01A.lesson_01A.Time(2, 3));
		lesson_01A.lesson_01A.Time expectedSum = new lesson_01A.lesson_01A.Time(12, 18);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest2() throws TimeException {
		lesson_01A.lesson_01A.Time actualSum = lesson_01A.lesson_01A.Time.sumTime(new lesson_01A.lesson_01A.Time(12, 45),
				new lesson_01A.lesson_01A.Time(2, 25));
		lesson_01A.lesson_01A.Time expectedSum = new lesson_01A.lesson_01A.Time(15, 10);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void sumTimeTest3() throws TimeException {
		lesson_01A.lesson_01A.Time actualSum = lesson_01A.lesson_01A.Time.sumTime(new lesson_01A.lesson_01A.Time(22, 10),
				new lesson_01A.lesson_01A.Time(2, 15));
		lesson_01A.lesson_01A.Time expectedSum = new lesson_01A.lesson_01A.Time(0, 25);
		Assert.assertEquals(expectedSum, actualSum);
	}

	@Test
	public void timeToMinutesTest() throws TimeException {
		Integer actual = lesson_01A.lesson_01A.Time.timeToMinutes(new lesson_01A.lesson_01A.Time(10, 10));
		Assert.assertTrue(actual == 610);
	}

	@Test
	public void minutesToTimeTest() throws TimeException {
		lesson_01A.lesson_01A.Time actual = lesson_01A.lesson_01A.Time.minutesToTime(610);
		Assert.assertEquals(actual, new lesson_01A.lesson_01A.Time(10, 10));
	}
}
