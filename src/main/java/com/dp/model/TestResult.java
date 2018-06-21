package com.dp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String discipline;
	
	private String type;
	
	private int correctAnswers;
	
	private Date takenOn;
	
	private String verdict;
	
	public TestResult() {
		
	}

	public TestResult(User user, String discipline, String type, int correctAnswers, Date takenOn, String verdict) {
		this.user = user;
		this.discipline = discipline;
		this.type = type;
		this.correctAnswers = correctAnswers;
		this.takenOn = takenOn;
		this.verdict = verdict;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Date getTakenOn() {
		return takenOn;
	}

	public void setTakenOn(Date takenOn) {
		this.takenOn = takenOn;
	}

	public String getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}

	@Override
	public String toString() {
		return "TestResult [discipline=" + discipline + ", type=" + type + ", correctAnswers=" + correctAnswers
				+ ", takenOn=" + takenOn + ", verdict=" + verdict + "]";
	}
}
