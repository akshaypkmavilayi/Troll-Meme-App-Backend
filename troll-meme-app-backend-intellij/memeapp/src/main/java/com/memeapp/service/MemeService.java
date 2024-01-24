package com.memeapp.service;

import java.util.List;

import com.memeapp.entities.MemeAndMovieConEntity;
import com.memeapp.entities.MemeEntity;


public interface MemeService {
	
	 MemeEntity saveImage(MemeAndMovieConEntity conEntity);
	 MemeEntity getMemeById(int fileId);
	 List<MemeEntity> getAllMemes();
	 String deleteById(int id);
	 MemeEntity updateImage(MemeAndMovieConEntity conEntity);

	 List<MemeEntity> getMemeByActorName(MemeEntity movieConEntity);

}
