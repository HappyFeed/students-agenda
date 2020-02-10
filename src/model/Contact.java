package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class Contact implements Serializable{

	private Image avatar;
	private String code;
	private String semester;
	private String name;
	private String lastName;
	private String carrera;
	private int age;

	private ArrayList<Subject> subjects;
	
	public Contact(String code, String semester, String name, String lastName, int age, String carrera) {
		this.code=code;
		this.semester=semester;
		this.name=name;
		this.lastName=lastName;
		this.age=age;
		this.carrera=carrera;
		this.avatar=avatar;
		subjects = new ArrayList<Subject>();
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

	public String getCarrera() {
		return this.carrera;
	}

	/**
	 * 
	 * @param carrera
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
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

	/**
	 * 
	 * @param subject
	 */
	public void setSubject(Subject subject) {
		// TODO - implement Contact.setSubject
		subjects.add(subject);
	}
	
	public ArrayList<Subject> getSubject(){
		return subjects;
	}

	public int promCredits() {
		// TODO - implement Contact.promCredits
		throw new UnsupportedOperationException();
	}

	public void numberOfSubject() {
		// TODO - implement Contact.numberOfSubject
		throw new UnsupportedOperationException();
	}
	
    public int binarySearchSubject(ArrayList<Subject> subject, int l, int r, String x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (subject.get(mid).getName().equalsIgnoreCase(x)) {
                return mid; 
            }
            if (subject.get(mid).getName().compareTo(x) > 0) { 
                return binarySearchSubject(subject, l, mid - 1, x); 
            }
            return binarySearchSubject(subject, mid + 1, r, x); 
        } 
        return -1; 
    }

    public void sortByNames() { 
        int n = subjects.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (subjects.get(j).getName().compareTo(subjects.get(j+1).getName()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Subject temp = subjects.get(j); 
                    subjects.set(j, subjects.get(j+1)); 
                    subjects.set(j+1, temp); 
                } 
    }
    
}