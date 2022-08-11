package com.mtmapp.service;

import java.util.List;

import com.mtmapp.entities.MemeAndMovieConEntity;
import com.mtmapp.entities.MemeEntity;

public interface MemeService {
	
	 MemeEntity saveImage(MemeAndMovieConEntity conEntity);
	 MemeEntity getMemeById(int fileId);
	 List<MemeEntity> getAllMemes();
	 String deleteById(int id);
	 MemeEntity updateImage(MemeAndMovieConEntity conEntity);
	 

}
