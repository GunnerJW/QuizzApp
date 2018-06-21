package com.dp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;;

@Entity
public class Question {

	@Id
	@Column(name="question_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String discipline;
	
	private String question;
	
	private String correctAnswer;
	
	@ElementCollection
	@CollectionTable(name="possibilities", joinColumns=@JoinColumn(name="question_id"))
	@Fetch(FetchMode.SUBSELECT)
	@Column(name="possibility_name")
	private Set<String> possibilities=new HashSet<String>();
	
	@Transient
	private List<String> possibilitiesList;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Set<String> getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(Set<String> possibilities) {
		this.possibilities = possibilities;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<String> getPossibilitiesList() {
		return possibilitiesList;
	}

	public void setPossibilitiesList(List<String> possibilitiesList) {
		this.possibilitiesList = possibilitiesList;
	}

	@Override
	public String toString() {
		return "Question [discipline=" + discipline + ", question=" + question + ", correctAnswer=" + correctAnswer
				+ ", possibilities=" + possibilities + ", possibilitiesList=" + possibilitiesList + "]";
	}
	
}