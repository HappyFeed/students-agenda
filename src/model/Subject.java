package model;

public class Subject {

	private String name;
	private int credits;
	private String nrc;
	private String info;

	public Subject() {
		// TODO - implement Subject.Subject
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return this.credits;
	}

	/**
	 * 
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getNrc() {
		return this.nrc;
	}

	/**
	 * 
	 * @param nrc
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getInfo() {
		return this.info;
	}

	/**
	 * 
	 * @param info
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}