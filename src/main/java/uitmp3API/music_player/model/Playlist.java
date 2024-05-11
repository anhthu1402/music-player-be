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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playlist_id")
	private Long id;
	
	@Column(name = "playlist_image")
	private String playlistImg;
	
	@Column(name = "playlist_name")
	private String playlistName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "songplaylist",
	joinColumns = @JoinColumn(name = "playlist_id"),
	inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> songPlaylist = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "favPlaylists")
	private List<User> favplaylists = new ArrayList<>();
	
	public List<User> getFavplaylists() {
		return favplaylists;
	}

	public void setFavplaylists(List<User> favplaylists) {
		this.favplaylists = favplaylists;
	}

	public Playlist() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaylistImg() {
		return playlistImg;
	}

	public void setPlaylistImg(String playlistImg) {
		this.playlistImg = playlistImg;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongPlaylist() {
		return songPlaylist;
	}

	public void setSongPlaylist(List<Song> songPlaylist) {
		this.songPlaylist = songPlaylist;
	}
	
	public void addSongPlaylist(Song song) {
		this.songPlaylist.add(song);
	}
	
	public void removeSongPlaylist(Song song) {
		this.songPlaylist.remove(song);
	}
	
}
