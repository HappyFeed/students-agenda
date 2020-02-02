package model;

import javafx.scene.image.Image;

public class Contact {

	private Image avatar;
	private String code;
	private String semester;
	private String name;
	private String lastName;
	private int age;

	public Contact(String code, String semester, String name, String lastName, int age) {
		this.code=code;
		this.semester=semester;
		this.name=name;
		this.lastName=lastName;
		this.age=age;
	}


	public Image getAvatar() {
		return this.avatar;
	}

	/**
	 * 
	 * @param avatar
	 */
	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	public String getCode() {
		return this.code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getSemester() {
		return this.semester;
	}

	/**
	 * 
	 * @param semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
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

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public Subject getSubject() {
		// TODO - implement Contact.getSubject
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param subject
	 */
	public void setSubject(Subject subject) {
		// TODO - implement Contact.setSubject
		throw new UnsupportedOperationException();
	}

	public int promCredits() {
		// TODO - implement Contact.promCredits
		throw new UnsupportedOperationException();
	}

	public void numberOfSubject() {
		// TODO - implement Contact.numberOfSubject
		throw new UnsupportedOperationException();
	}

}