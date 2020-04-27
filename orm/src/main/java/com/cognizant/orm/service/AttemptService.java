package com.cognizant.orm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm.model.Attempt;
import com.cognizant.orm.repository.AttemptRepository;
import com.cognizant.orm.service.exception.UserAndAttemptNotFoundException;


@Service
public class AttemptService {
	@Autowired
	private AttemptRepository attemptRepository;
	@Transactional
	public Attempt getAttemptDetails(int userId, int attemptId) throws UserAndAttemptNotFoundException {
		Attempt attemptDetails = attemptRepository.getAttempt(userId, attemptId); 
		if (attemptDetails == null) { 
			throw new UserAndAttemptNotFoundException("No User with ID Found or Attempt Found");
	}	
		return attemptDetails;
		
	}

}
