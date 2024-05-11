package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.model.Song;

public class AlbumSongDto {
	public Long id;
	public String albumName;
	public String albumImage;
	public Long interestTimes;
	public Date releaseDate;
	public Country country;
	public List<Artist> artist = new ArrayList<>();
	public List<Song> songs = new ArrayList<Song>();
	
	public AlbumSongDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AlbumSongDto(Album album) {
		this.id=album.getId();
		this.albumName = album.getAlbumName();
		this.albumImage = album.getAlbumImage();
		this.interestTimes = album.getInterestTimes();
		this.releaseDate = album.getReleaseDate();
		this.artist = album.getArtist();
		this.country = album.getCountry();
		this.songs = album.getSongs();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumImage() {
		return albumImage;
	}

	public void setAlbumImage(String albumImage) {
		this.albumImage = albumImage;
	}

	public Long getInterestTimes() {
		return interestTimes;
	}

	public void setInterestTimes(Long interestTimes) {
		this.interestTimes = interestTimes;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Artist> getArtist() {
		return artist;
	}

	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	
}
