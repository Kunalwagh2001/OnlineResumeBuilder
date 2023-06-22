package com.onlineresumebuilder;
import java.util.ArrayList;
import java.util.List;

public class Resume {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String summary;
    private List<String> skills;
    private List<String> experiences;
    private List<String> education;
    
    public Resume() {
        
    }

	public Resume(int id, String name, String email, String phone, String address, String summary, List<String> skills,
			List<String> experiences, List<String> education) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.summary = summary;
		this.skills = new ArrayList<>();
        this.experiences = new ArrayList<>();
        this.education = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<String> experiences) {
		this.experiences = experiences;
	}

	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}
    
}
