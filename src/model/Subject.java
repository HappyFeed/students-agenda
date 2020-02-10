package model;

import java.io.Serializable;

public class Subject implements Serializable{

	private String name;
	private int credits;
	private String nrc;
	private String info;

	public Subject(String name, int credits, String nrc, String info) {
		// TODO - implement Subject.Subject
		this.name = name;
		this.credits = credits;
		this.nrc = nrc;
		this.info = info;
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