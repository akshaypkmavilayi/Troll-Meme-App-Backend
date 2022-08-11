package com.mtmapp.entities;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="meme_table")
public class MemeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memeId;
	private String actorName;
	@CreationTimestamp
	private Date timeStamp;
	private String fileName;
	private String fileType;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = MovieDetailsEntity.class)
	@JoinColumn(name = "movie_id",referencedColumnName = "movie_id")
	private MovieDetailsEntity movieDetails;
	@Lob
	private byte[] memeImage; 
	
	 
	
	public int getMemeId() {
		return memeId;
	}
	public void setMemeId(int memeId) {
		this.memeId = memeId;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public MovieDetailsEntity getMovieDetails() {
		return movieDetails;
	}
	public void setMovieDetails(MovieDetailsEntity movieDetails) {
		this.movieDetails = movieDetails;
	}
	public byte[] getMemeImage() {
		return memeImage;
	}
	public void setMemeImage(byte[] memeImage) {
		this.memeImage = memeImage;
	}
	
}
