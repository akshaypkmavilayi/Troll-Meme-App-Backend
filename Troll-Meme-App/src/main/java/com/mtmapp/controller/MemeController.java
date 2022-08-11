package com.mtmapp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.mtmapp.entities.MemeAndMovieConEntity;
import com.mtmapp.entities.MemeEntity;
import com.mtmapp.entities.MovieDetailsEntity;
import com.mtmapp.model.ResponseData;
import com.mtmapp.service.MemeService;
import com.mtmapp.service.MemeServiceImpl;



@RestController
@RequestMapping("/meme")
@CrossOrigin(origins = "http://localhost:4200")
public class MemeController {
	
	@Autowired
	private MemeService memeService;
	
		@PostMapping("/image")
		public @ResponseBody ResponseData createProduct(@RequestParam("name") String actorName,
				@RequestParam("movie") String movieName, Model model, HttpServletRequest request
				,final @RequestParam("image") MultipartFile file) throws IOException {
			ResponseData responseData = new ResponseData();
			MemeEntity meme = new MemeEntity();
			MovieDetailsEntity movie = new MovieDetailsEntity();
			MemeAndMovieConEntity conEntity = new MemeAndMovieConEntity();
			
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String actor = actorName.toString().trim(); 
			String fileType = file.getContentType();
			String movieN = movieName.toString().trim();
			meme.setFileName(fileName);
			meme.setActorName(actor);
			meme.setFileType(fileType);
			meme.setMemeImage(file.getBytes());
			
			movie.setMovieName(movieN);
			
			conEntity.setMemeEntity(meme);
			conEntity.setMovieEntity(movie);
			
			
			
			memeService.saveImage(conEntity);
			String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
					.path(String.valueOf(meme.getMemeId())).toUriString();
			responseData.setFileId(meme.getMemeId());
			responseData.setFileName(meme.getFileName());
			responseData.setDownloadURL(downloadURL); 
			responseData.setFileType(meme.getFileType());
			responseData.setFileSize(file.getSize());
			
			System.out.println(downloadURL);
			
					return responseData;
		}
		
//		@GetMapping("/download/{memeId}")
//		public ResponseEntity<Resource> downloadFile(@PathVariable int memeId){
//			MemeEntity meme = null;
//			meme = memeService.getMemeById(memeId);
//			return ResponseEntity.ok().contentType(MediaType.parseMediaType(meme.getFileType()))
//					.header(HttpHeaders.CONTENT_DISPOSITION, "meme; filename=\"" + meme.getFileName()
//					+"\"")
//					.body(new ByteArrayResource(meme.getMemeImage()));
//			}
		
//		@GetMapping("/view-all")
//		public List<MemeEntity> getAllMemes(){
//			List<MemeEntity> memeEn = memeService.getAllMemes();
//			for(MemeEntity itr:memeEn) {
//				System.out.println(itr.getMemeImage());
//			}
//			
//			return memeService.getAllMemes();
//		}
		
		@GetMapping("/view-all")
		public List<MemeEntity> getAllMemes(){
			
			List<MemeEntity> meme = memeService.getAllMemes();
			
			
			
		     
		     return meme;
		      
				
		}
		
		
	
		@DeleteMapping("/delete/{memeId}")
		public String deleteMeme(@PathVariable int memeId) {
			String status = memeService.deleteById(memeId);
			if(status =="success") {
				return "deleted successfully...";
			}else {
				return "failed";
			}
			
			
		}
		
		@PutMapping("/update")
		public MemeEntity updateMeme(@RequestParam("id") int memeId,@RequestParam("name") String actorName,
				@RequestParam("movie") String movieName,final @RequestParam("image") MultipartFile file) throws IOException {
			MemeAndMovieConEntity conEntity = new MemeAndMovieConEntity();
			MovieDetailsEntity movie = new MovieDetailsEntity();
			MemeEntity meme = memeService.getMemeById(memeId);
			meme.setActorName(actorName);
			meme.setMemeImage(file.getBytes());
			
			movie.setMovieName(movieName);
			
			conEntity.setMemeEntity(meme);
			conEntity.setMovieEntity(movie);
			
			return memeService.updateImage(conEntity);
			
		}
		
		
}