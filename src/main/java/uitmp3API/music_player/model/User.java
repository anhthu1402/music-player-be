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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_admin")
	private Long isAdmin;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "avatar")
	private String avatar;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Playlist> playlists = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "follow",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "artist_id"))
	private List<Artist> following = new ArrayList<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "favplaylist", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Playlist> favPlaylists = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "favalbum", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "album_id"))
	private List<Album> favAlbums = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "favsong", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<Song> favSongs = new ArrayList<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Long isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Artist> getFollowing() {
		return following;
	}

	public void setFollowing(List<Artist> following) {
		this.following = following;
	}
	
	public void addFollow(Artist a) {
		this.following.add(a);
	}
	
	public void unfollow(Artist a) {
		this.following.remove(a);
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Playlist> getFavPlaylists() {
		return favPlaylists;
	}

	public void setFavPlaylists(List<Playlist> favPlaylists) {
		this.favPlaylists = favPlaylists;
	}

	public List<Album> getFavAlbums() {
		return favAlbums;
	}

	public void setFavAlbums(List<Album> favAlbums) {
		this.favAlbums = favAlbums;
	}

	public List<Song> getFavSongs() {
		return favSongs;
	}

	public void setFavSongs(List<Song> favSongs) {
		this.favSongs = favSongs;
	}

	public void addFavPlaylist(Playlist p) {
		this.favPlaylists.add(p);
	}
	
	public void addFavAlbum(Album a) {
		this.favAlbums.add(a);
	}
	
	public void addFavSong(Song s) {
		this.favSongs.add(s);
	}
	
	public void removeFavPlaylist(Playlist p) {
		this.favPlaylists.remove(p);
	}
	
	public void removeFavAlbum(Album a) {
		this.favAlbums.remove(a);
	}
	
	public void removeFavSong(Song s) {
		this.favSongs.remove(s);
	}
}
