package com.mtmapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.mtmapp.entities.MemeAndMovieConEntity;
import com.mtmapp.entities.MemeEntity;
import com.mtmapp.entities.MovieDetailsEntity;
import com.mtmapp.repository.MemeRepository;
import com.mtmapp.repository.MovieRepository;
@Service
@Repository
public class MemeServiceImpl implements MemeService {
	@Autowired
	private MemeRepository memeRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	public MemeEntity saveImage(MemeAndMovieConEntity conEntity) {
		
		MemeEntity meme = conEntity.getMemeEntity();
		MovieDetailsEntity movie = conEntity.getMovieEntity();
		movieRepo.save(movie); 
		meme.setMovieDetails(movie);
		memeRepo.save(meme);
		
		return meme;
		
	}
	
	public MemeEntity getMemeById(int fileId) {
	MemeEntity me = memeRepo.findById(fileId).orElseThrow();
	System.out.println(me);
		
		return me;
	}

	@Override
	public List<MemeEntity> getAllMemes() {
	List<MemeEntity> memeList = memeRepo.findAll();
	System.out.println(memeList.size());
	return memeList;
	}

	@Override
	public String deleteById(int id) {
	memeRepo.deleteById(id);
	return "success";
	}

	@Override
	public MemeEntity updateImage(MemeAndMovieConEntity conEntity) {
		MemeEntity meme = conEntity.getMemeEntity();
		MovieDetailsEntity movie = conEntity.getMovieEntity();
		movieRepo.save(movie);
		meme.setMovieDetails(movie);
		memeRepo.save(meme);
		
		return meme;
	}

}
