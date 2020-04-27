package com.cognizant.orm;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.orm.service.AttemptService;
import com.cognizant.orm.model.Attempt;
import com.cognizant.orm.model.AttemptOption;
import com.cognizant.orm.model.AttemptQuestion;
import com.cognizant.orm.model.Options;
import com.cognizant.orm.service.exception.UserAndAttemptNotFoundException;

@SpringBootApplication
public class OrmApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(OrmApplication.class);
	private static AttemptService attemptService;
	public static void main(String[] args) throws UserAndAttemptNotFoundException {
		ApplicationContext applicationContext=SpringApplication.run(OrmApplication.class, args); 
		attemptService=applicationContext.getBean(AttemptService.class);
		testGetAttemptDetails(1, 1);
	}

	public static void testGetAttemptDetails(int userId, int attemptId ) throws UserAndAttemptNotFoundException {
	
			LOGGER.info("Start");
			//LOGGER.debug("Attempts:{}", attemptService.getAll());
			Attempt attempt = attemptService.getAttemptDetails(userId, attemptId);
			LOGGER.debug("Attempt:{}", attempt);
			LOGGER.debug("User:{}", attempt.getUser());
			LOGGER.debug("Questions:{}", attempt.getAttemptQuestionList());
			Set<AttemptQuestion> attemptQuestionList=attempt.getAttemptQuestionList();
			System.out.println();
			for (AttemptQuestion attemptQuestion : attemptQuestionList) {
				System.out.println(attemptQuestion.getQuestion().getId()+") "+attemptQuestion.getQuestion().getQuestionText());
				Set<AttemptOption> attemptOptionList = attemptQuestion.getAttemptOptionList();
				int i =1;
				for (AttemptOption attemptOption : attemptOptionList) {
					Options options = attemptOption.getOptions();
					System.out.println(i+")  "+options.getOptionText()+"    "+options.getScore()+"   "+attemptOption.getSelected());
					i+=1;
				}
				System.out.println();

			}
			LOGGER.info("End");
		}
		
	}

