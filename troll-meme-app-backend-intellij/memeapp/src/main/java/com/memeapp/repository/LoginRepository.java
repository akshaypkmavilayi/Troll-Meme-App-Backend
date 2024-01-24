package com.memeapp.repository;

import com.memeapp.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
	@Query("SELECT l FROM LoginEntity l WHERE l.emailId = :e and l.password = :p")
	LoginEntity findByEmailAndPassword(@Param("e") String email,@Param("p") String password);

}
