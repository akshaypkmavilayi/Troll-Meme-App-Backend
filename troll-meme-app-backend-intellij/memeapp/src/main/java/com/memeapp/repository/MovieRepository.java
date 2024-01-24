package com.memeapp.repository;

import com.memeapp.entities.MovieDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MovieRepository extends JpaRepository<MovieDetailsEntity,Integer> {

}
