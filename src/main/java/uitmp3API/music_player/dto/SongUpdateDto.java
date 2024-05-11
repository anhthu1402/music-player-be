package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.model.Song;

public class SongUpdateDto {
	public Long id;
	public String songName;
	public String songImage;
	public String timeLimit;
	public String songLink;
	public Date releaseDate;
	public String lyrics;
	public Country country = new Country();
	public List<Long> representationId = new ArrayList<>();
	public List<Long> composersId = new ArrayList<>();
	public Album album = new Album();
	
	public SongUpdateDto() {
		// TODO Auto-generated constructor stub
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

	public List<Long> getRepresentationId() {
		return representationId;
	}

	public void setRepresentationId(List<Long> representationId) {
		this.representationId = representationId;
	}

	public List<Long> getComposersId() {
		return composersId;
	}

	public void setComposersId(List<Long> composersId) {
		this.composersId = composersId;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
