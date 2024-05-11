package uitmp3API.music_player.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.LoginForm;
import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.repository.AlbumRepository;
import uitmp3API.music_player.repository.ArtistRepository;
import uitmp3API.music_player.repository.PlaylistRepository;
import uitmp3API.music_player.repository.SongRepository;
import uitmp3API.music_player.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	SongRepository songRepository;
	
	@Autowired 
	AlbumRepository albumRepository;
	
	@Autowired
	PlaylistRepository playlistRepository;
	
	// create user, register: specific username
	public User createUser(User user) {
		User u = userRepository.findByUserName(user.getUserName().trim());
		
		if(u != null) {
			return new User();
		}
		
		return userRepository.save(user);
	}
	
	// read all users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// read user by id
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return new User();
	}
	
	// get user's following
	public List<Artist> getUserFollowing(Long userId) {
		Optional<User> u = userRepository.findById(userId);
		if(u.isPresent()) {
			User user = u.get();
			if(user.getFollowing() != null) {
				return user.getFollowing();
			}
		}
		return null;
	}
	
	// get user's favorite songs
	public List<Song> getUserFavSongs(Long userId) {
		Optional<User> u = userRepository.findById(userId);
		if(u.isPresent()) {
			User user = u.get();
			if(user.getFavSongs() != null) {
				return user.getFavSongs();
			}
		}
		return null;
	}
	
	// get user's favorite playlists
	public List<Playlist> getUserFavPlaylists(Long userId) {
		Optional<User> u = userRepository.findById(userId);
		if(u.isPresent()) {
			User user = u.get();
			if(user.getFavPlaylists() != null) {
				return user.getFavPlaylists();
			}
		}
		return null;
	}
	
	// get user's favorite albums
	public List<Album> getUserFavAlbums(Long userId) {
		Optional<User> u = userRepository.findById(userId);
		if(u.isPresent()) {
			User user = u.get();
			if(user.getFavAlbums() != null) {
				return user.getFavAlbums();
			}
		}
		return null;
	}
	
	// update user's avatar
	public User updateAvatar(Long userId, String avatar) {
		Optional<User> u = userRepository.findById(userId);
		if(u.isPresent()) {
			User user = u.get();
			user.setAvatar(avatar);
			return userRepository.save(user);
		}
		return new User();
	}
	
	// login
	public User login(LoginForm loginForm) {
		User user = userRepository.findByUserName(loginForm.getUsername().trim());
		if(user != null) {
			if(user.getPassword().equals(loginForm.getPassword())) {
				return user;
			}
			return new User();
		}
		return new User();
	}
	
	// follow 
	public Boolean follow(Long userId, Long artistId) {
		try {
			if(isFollowing(userId, artistId)) {
				return false;
			}
			else {
				User user = userRepository.findById(userId).get();
				Artist artist = artistRepository.findById(artistId).get();
				Long followers = artist.getNumberOfFollower();
				
				user.addFollow(artist);
				artist.setNumberOfFollower(followers + 1);
				userRepository.save(user);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	// unfollow
	public Boolean unfollow(Long userId, Long artistId) {
		try {
			if(!isFollowing(userId, artistId)) {
				return false;
			}
			else {
				User user = userRepository.findById(userId).get();
				Artist artist = artistRepository.findById(artistId).get();
				Long followers = artist.getNumberOfFollower();
				
				user.unfollow(artist);
				artist.setNumberOfFollower(followers - 1);
				userRepository.save(user);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	// is following
	public Boolean isFollowing(Long userId, Long artistId) {
		try {
			List<Artist> artists = getUserFollowing(userId);
			if(artists.isEmpty()) {
				return false;
			}
			else {
				for (Artist artist : artists) {
					if(artist.getId().equals(artistId)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	// add favorite song
	public Boolean addFavSong(Long userId, Long songId) {
		try {
			User user = userRepository.findById(userId).get();
			Song song = songRepository.findById(songId).get();
			
			user.addFavSong(song);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// is favorite song
	public Boolean isFavSong(Long userId, Long songId) {
		try {
			List<Song> songs = getUserFavSongs(userId);
			if(songs.isEmpty()) {
				return false;
			}
			else {
				for (Song song : songs) {
					if(song.getId().equals(songId)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	// remove favorite song
		public Boolean removeFavSong(Long userId, Long songId) {
			try {
				User user = userRepository.findById(userId).get();
				Song song = songRepository.findById(songId).get();
				
				user.removeFavSong(song);
				userRepository.save(user);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	
	// add favorite album
	public Boolean addFavAlbum(Long userId, Long albumId) {
		try {
			User user = userRepository.findById(userId).get();
			Album album = albumRepository.findById(albumId).get();
			
			user.addFavAlbum(album);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// is favorite album
	public Boolean isFavAlbum(Long userId, Long albumId) {
		try {
			List<Album> albums = getUserFavAlbums(userId);
			if(albums.isEmpty()) {
				return false;
			}
			else {
				for (Album album : albums) {
					if(album.getId().equals(albumId)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	// remove favorite album
	public Boolean removeFavAlbum(Long userId, Long albumId) {
		try {
			User user = userRepository.findById(userId).get();
			Album album = albumRepository.findById(albumId).get();
			
			user.removeFavAlbum(album);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// add favorite playlist
	public Boolean addFavPlaylist(Long userId, Long playlistId) {
		try {
			User user = userRepository.findById(userId).get();
			Playlist playlist = playlistRepository.findById(playlistId).get();
			
			user.addFavPlaylist(playlist);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// is favorite playlist
	public Boolean isFavPlaylist(Long userId, Long playlistId) {
		try {
			List<Playlist> playlists = getUserFavPlaylists(userId);
			if(!playlists.isEmpty()) {
				for (Playlist playlist : playlists) {
					if(playlist.getId().equals(playlistId)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	// remove favorite playlist
	public Boolean removeFavPlaylist(Long userId, Long playlistId) {
		try {
			User user = userRepository.findById(userId).get();
			Playlist playlist = playlistRepository.findById(playlistId).get();
			
			user.removeFavPlaylist(playlist);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// get number of users
	public Long getNumberOfUsers() {
		return userRepository.count();
	}
}
