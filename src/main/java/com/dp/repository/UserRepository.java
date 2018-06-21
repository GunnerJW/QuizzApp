package com.dp.repository;

import org.springframework.data.repository.CrudRepository;

import com.dp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
	Iterable<User> findAllByEnabled(boolean enabled);
}
