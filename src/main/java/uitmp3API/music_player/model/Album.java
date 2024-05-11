package uitmp3API.music_player.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id")
	private Long id;
	
	@Column(name = "album_name")
	private String albumName;
	
	@Column(name = "interest_times")
	private Long interestTimes;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Column(name = "album_image")
	private String albumImage;
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	public Country country;
	
	@ManyToMany
	@JoinTable(name = "artistalbum", 
	joinColumns = @JoinColumn(name = "album_id"), 
	inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> artist = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
	private List<Song> songs = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "favAlbums")
	private List<User> favAlbums = new ArrayList<>();
	
	public List<User> getFavAlbums() {
		return favAlbums;
	}

	public void setFavAlbums(List<User> favAlbums) {
		this.favAlbums = favAlbums;
	}

	public Album() {
		// TODO Auto-generated constructor stub
	}
	
	public Album(Album album) {
		this.id = album.getId();
		this.albumImage = album.getAlbumImage();
		this.albumName = album.getAlbumName();
		this.interestTimes = album.getInterestTimes();
		this.releaseDate = album.getReleaseDate();
		this.country = album.getCountry();
		this.artist = album.getArtist();
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

	public String getAlbumImage() {
		return albumImage;
	}

	public void setAlbumImage(String albumImage) {
		this.albumImage = albumImage;
	}

	public List<Artist> getArtist() {
		return artist;
	}

	public void setArtist(List<Artist> artist) {
		this.artist = artist;
	}
	
	public void addArtist(Artist a) {
		this.artist.add(a);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public void removeArtist(Artist a) {
		this.artist.remove(a);
	}
	
	public void addSong(Song song) {
		this.songs.add(song);
	}
	
	public void removeSong(Song song) {
		this.songs.remove(song);
	}
}
