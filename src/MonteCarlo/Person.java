
public class Person {
	//integer that will be filled by numbers 1-365 (days of the year)
	private int birthday;
	//each person will take in that birthday as an argument
	public Person(int birthday) {
		this.birthday = birthday;
	}
	//getter for birthday
	public int getBirthday() {
		return birthday;
	}
	//setter for birthday
	public void setBirthday(int newDay) {
		birthday = newDay;
	}
}
