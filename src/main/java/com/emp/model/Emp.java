package com.emp.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp")
public class Emp extends BasicModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "hire_date")
	private Date hireDate;

	@Column(name = "skills")
	private String skills;

	@Column(name = "career_goal")
	private String careerGoal;

	@Column(name = "training_recommendation")
	private String trainingRecommendation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private Dept dept;
}
