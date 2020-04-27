package com.cognizant.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.orm.model.Attempt;
@Repository
public interface AttemptRepository extends JpaRepository<Attempt,Integer>{
	
	@Query("Select a from Attempt a left join fetch a.user where a.id=?2 and a.user.id=?1")
	public Attempt getAttempt(int userId,int attemptId);

}
