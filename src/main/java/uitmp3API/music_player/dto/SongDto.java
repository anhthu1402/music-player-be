package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.model.Song;

public class SongDto {
	public Long id;
	public String songName;
	public String songImage;
	public String timeLimit;
	public String songLink;
	public Date releaseDate;
	public String lyrics;
	public Country country = new Country();
	public List<Artist> representation = new ArrayList<>();
	public List<Artist> composing = new ArrayList<>();
	public Album album = new Album();
	
	public SongDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SongDto(Song song) {
		id = song.getId();
		songName = song.getSongName();
		songImage = song.getSongImage();
		timeLimit = song.getTimeLimit();
		songLink = song.getSongLink();
		releaseDate = song.getReleaseDate();
		lyrics = song.getLyrics();
		country = song.getCountry();
		representation = song.getRepresentation();
		composing = song.getComposing();
		album = song.getAlbum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongImage() {
		return songImage;
	}

	public void setSongImage(String songImage) {
		this.songImage = songImage;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Artist> getRepresentation() {
		return representation;
	}

	public void setRepresentation(List<Artist> representation) {
		this.representation = representation;
	}

	public List<Artist> getComposing() {
		return composing;
	}

	public void setComposing(List<Artist> composing) {
		this.composing = composing;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}
