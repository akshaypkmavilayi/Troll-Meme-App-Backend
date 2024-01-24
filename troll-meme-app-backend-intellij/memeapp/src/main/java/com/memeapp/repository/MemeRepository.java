package com.memeapp.repository;

import com.memeapp.entities.MemeAndMovieConEntity;
import com.memeapp.entities.MemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemeRepository extends JpaRepository<MemeEntity, Integer>{
    @Query("SELECT m FROM MemeEntity m WHERE LOCATE(:actorName, m.actorName) > 0")
    List<MemeEntity> findMemesByActorName(@Param("actorName") String actorName);
}
