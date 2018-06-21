package com.dp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dp.model.Question;
import com.dp.repository.QuestionRepository;

@Service("questionService")
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public void saveQuestion(Question question) {
		questionRepository.save(question);
	}
	
	public void listToSet(Question question) {
		for(String s : question.getPossibilitiesList())
			question.getPossibilities().add(s);
	}
	
	public List<Question> findByDiscipline(String discipline){
		return questionRepository.findAllByDiscipline(discipline);
	}
	
	public List<Question> generateQuestionList(String discipline){
		String disciplineFullName;
		if(discipline.equals("maths"))
			disciplineFullName="Mathématiques";
		else if(discipline.equals("cs"))
			disciplineFullName="Informatique";
		else
			disciplineFullName="Histoire";
		List<Question> records=questionRepository.findAllByDiscipline(disciplineFullName);
		int recordsSize=records.size();
		Random rand=new Random();
		int[] randomIndexes=rand.ints(0,recordsSize).distinct().limit(5).toArray();
		List<Question> list=new ArrayList<Question>(5);
		for(int index : randomIndexes)
			list.add(records.get(index));
		return list;
	}
}
