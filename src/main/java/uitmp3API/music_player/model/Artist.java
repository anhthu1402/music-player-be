package uitmp3API.music_player.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_id")
	private Long id;
	
	@Column(name = "artist_image")
	private String artistImage;
	
	@Column(name = "artist_name")
	private String artistName;
	
	@Column(name = "introduce")
	private String introduce;
	
	@Column(name = "number_of_follower")
	private Long numberOfFollower;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "artist")
	private List<Album> artistAlbum = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "representation")
	private List<Song> representation = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "composing")
	private List<Song> composing = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "following")
	private List<User> followers = new ArrayList<>();
	
	public Artist() {
		// TODO Auto-generated constructor stub
	}
	
	public Artist(Artist a) {
		this.id=a.getId();
		this.artistName=a.getArtistName();
		this.artistImage=a.getArtistImage();
		this.artistAlbum=a.getArtistAlbum();
		this.introduce=a.getIntroduce();
		this.numberOfFollower=a.getNumberOfFollower();
		this.composing=a.getComposing();
		this.representation=a.getRepresentation();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtistImage() {
		return artistImage;
	}

	public void setArtistImage(String artistImage) {
		this.artistImage = artistImage;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Long getNumberOfFollower() {
		return numberOfFollower;
	}

	public void setNumberOfFollower(Long numberOfFollower) {
		this.numberOfFollower = numberOfFollower;
	}

	public List<Album> getArtistAlbum() {
		return artistAlbum;
	}
	
	public void setArtistAlbum(List<Album> artistAlbum) {
		this.artistAlbum = artistAlbum;
	}
	
	public void addAlbum(Album album) {
		artistAlbum.add(album);
	}

	public List<Song> getRepresentation() {
		return representation;
	}

	public void setRepresentation(List<Song> representation) {
		this.representation = representation;
	}

	public List<Song> getComposing() {
		return composing;
	}

	public void setComposing(List<Song> composing) {
		this.composing = composing;
	}

	public void addSongRepresentation(Song song) {
		this.representation.add(song);
	}
	
	public void addSongComposing(Song song) {
		this.composing.add(song);
	}
	
	public void removeAlbum(Album a) {
		this.artistAlbum.remove(a);
	}
	
	public void removeSongRepresentation(Song s) {
		this.representation.remove(s);
	}
	
	public void removeSongComposing(Song s) {
		this.composing.remove(s);
	}
	

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
}
