package com.emp.model;

import java.util.Date;


public class EmpBasic  {
   private  String firstName;
    private String lastName;
    private String gender;
    private Date birthDate;
    private Date hireDate;
	private String skills;

	private String careerGoal;


	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getCareerGoal() {
		return careerGoal;
	}

	public void setCareerGoal(String careerGoal) {
		this.careerGoal = careerGoal;
	}

	public String getTrainingRecommendation() {
		return trainingRecommendation;
	}

	public void setTrainingRecommendation(String trainingRecommendation) {
		this.trainingRecommendation = trainingRecommendation;
	}

	private String trainingRecommendation;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
		
    
}
