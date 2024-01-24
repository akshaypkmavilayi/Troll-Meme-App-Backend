package com.memeapp.service;

import java.util.List;

import com.memeapp.entities.MemeAndMovieConEntity;
import com.memeapp.entities.MemeEntity;
import com.memeapp.entities.MovieDetailsEntity;
import com.memeapp.repository.MemeRepository;
import com.memeapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
//	MemeEntity me = memeRepo.findById(fileId).get();
//	System.out.println(me);
		
//		return me;
		return null;
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

	@Override
	public List<MemeEntity> getMemeByActorName(MemeEntity movieConEntity) {
		List<MemeEntity> memeEntityList = memeRepo.findMemesByActorName(movieConEntity.getActorName());
		return memeEntityList;
	}



}
