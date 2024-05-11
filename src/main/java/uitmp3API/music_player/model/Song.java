package uitmp3API.music_player.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uitmp3API.music_player.dto.SongDto;
import uitmp3API.music_player.dto.SongUpdateDto;
import uitmp3API.music_player.repository.ArtistRepository;
import uitmp3API.music_player.service.ArtistService;

@Entity
@Table(name = "song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id")
	private Long id;
	
	@Column(name = "song_name")
	private String songName;
	
	@Column(name = "song_image")
	private String songImage;
	
	@Column(name = "time_limit")
	private String timeLimit;
	
	@Column(name = "song_link")
	private String songLink;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Column(name = "lyrics")
	private String lyrics;
	
	@ManyToMany
	@JoinTable(name = "representation",
	joinColumns = @JoinColumn(name = "song_id"),
	inverseJoinColumns = @JoinColumn(name = "singer_id"))
	private List<Artist> representation = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private Country country = new Country();
	
	@ManyToMany
	@JoinTable(name = "composing",
	joinColumns = @JoinColumn(name = "song_id"),
	inverseJoinColumns = @JoinColumn(name = "composer_id"))
	private List<Artist> composing = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "album_id", referencedColumnName = "album_id")
	private Album album = new Album();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "songPlaylist")
	private List<Playlist> playlists = new ArrayList<>();
	
	
	public List<User> getFavSongs() {
		return favSongs;
	}

	public void setFavSongs(List<User> favSongs) {
		this.favSongs = favSongs;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "favSongs")
	private List<User> favSongs = new ArrayList<>();
	
	public Song() {
		// TODO Auto-generated constructor stub
	}
	
	public Song(SongDto s) {
		this.id = s.getId();
		this.songName = s.getSongName();
		this.songImage = s.getSongImage();
		this.songLink = s.getSongLink();
		this.timeLimit = s.getTimeLimit();
		this.releaseDate = s.getReleaseDate();
		this.lyrics = s.getLyrics();
		this.country = s.getCountry();
		this.album = s.getAlbum();
		this.representation = s.getRepresentation();
		this.composing = s.getComposing();
	}
	
	public Song(SongUpdateDto s) {
		this.id = s.getId();
		this.songName = s.getSongName();
		this.songImage = s.getSongImage();
		this.songLink = s.getSongLink();
		this.timeLimit = s.getTimeLimit();
		this.releaseDate = s.getReleaseDate();
		this.lyrics = s.getLyrics();
		this.country = s.getCountry();
		this.album = s.getAlbum();
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
	
	public void addRepresentation(Artist a) {
		this.representation.add(a);
	}
	
	public void addComposer(Artist a) {
		this.composing.add(a);
	}
	
	public void removeRepresentation(Artist a) {
		this.representation.remove(a);
	}
	
	public void removeComposer(Artist a) {
		this.composing.remove(a);
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public void addPlaylist(Playlist p) {
		this.playlists.add(p);
	}
	
	public void removePlaylist(Playlist p) {
		this.playlists.remove(p);
	}
}
