package com.mtmapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtmapp.entities.MovieDetailsEntity;

public interface MovieRepository extends JpaRepository<MovieDetailsEntity,Integer> {

}
