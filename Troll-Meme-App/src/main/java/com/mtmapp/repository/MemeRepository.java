package com.mtmapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtmapp.entities.MemeEntity;



public interface MemeRepository extends JpaRepository<MemeEntity, Integer>{

}
