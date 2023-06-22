package com.onlineresumebuilder;

import java.util.ArrayList;

public class ResumeBuilder {
	private Resume resume;

	public ResumeBuilder() {
		this.resume = new Resume();
	}

	public void setName(String name) {
		resume.setName(name);
	}

	public void setEmail(String email) {
		resume.setEmail(email);
	}

	public void setPhone(String phone) {
		resume.setPhone(phone);
	}

	public void setAddress(String address) {
		resume.setAddress(address);
	}

	public void setSummary(String summary) {
		resume.setSummary(summary);
	}

	public void addSkill(String skill) {
		if(resume.getSkills() == null)
			resume.setSkills(new ArrayList<>());
		resume.getSkills().add(skill);
	}

	public void addExperience(String experience) {
		if(resume.getExperiences() == null)
			resume.setExperiences(new ArrayList<>());
		resume.getExperiences().add(experience);
	}

	public void addEducation(String education) {
		if(resume.getEducation() == null)
			resume.setEducation(new ArrayList<>());
		resume.getEducation().add(education);
	}

	public Resume build() {
		return resume;
	}
}
