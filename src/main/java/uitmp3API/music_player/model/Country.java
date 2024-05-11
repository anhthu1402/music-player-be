package uitmp3API.music_player.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Long id;
	
	@Column(name = "country_name")
	private String countryName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private List<Song> songs = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Album> albums = new ArrayList<>();
	
	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public List<Song> getSongs() {
		return songs;
	}
	
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	public void addAlbum(Album album) {
		this.albums.add(album);
	}
}
