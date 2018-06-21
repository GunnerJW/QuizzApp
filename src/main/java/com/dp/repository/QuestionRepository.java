package com.dp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dp.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	List<Question> findAllByDiscipline(String discipline);
}
