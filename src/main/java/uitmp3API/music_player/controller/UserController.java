package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;
import uitmp3API.music_player.model.LoginForm;
import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;
	
	// create user
	@RequestMapping(value = "", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	// read all users
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	// read user by id
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUserById(@PathVariable(value = "userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	//update user's avatar
	@RequestMapping(value = "/{userId}/avatar", method = RequestMethod.PUT)
	public User updateAvatar(@PathVariable(value = "userId") Long userId, @RequestBody String avatar) {
		return userService.updateAvatar(userId, avatar);
	}
	
	// login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public User login(@RequestBody LoginForm loginForm) {
		return userService.login(loginForm);
	}
	
	// follow
	@RequestMapping(value = "/{userId}/follow/{artistId}", method = RequestMethod.PUT)
	public Boolean follow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "artistId") Long artistId) {
		return userService.follow(userId, artistId);
	}
	
	// unfollow
	@RequestMapping(value = "/{userId}/unfollow/{artistId}", method = RequestMethod.PUT)
	public Boolean unfollow(@PathVariable(value = "userId") Long userId, @PathVariable(value = "artistId") Long artistId) {
		return userService.unfollow(userId, artistId);
	}
	
	// is following
	@RequestMapping(value = "/{userId}/following/{artistId}", method = RequestMethod.GET)
	public Boolean isFollowing(@PathVariable(value = "userId") Long userId, @PathVariable(value = "artistId") Long artistId) {
		return userService.isFollowing(userId, artistId);
	}
	
	// get user's favorite songs
	@RequestMapping(value = "/{userId}/favorite-songs", method = RequestMethod.GET)
	public List<Song> getUserFavSongs(@PathVariable(value = "userId") Long userId) {
		return userService.getUserFavSongs(userId);
	}
	
	// get user's favorite albums
	@RequestMapping(value = "/{userId}/favorite-albums", method = RequestMethod.GET)
	public List<Album> getUserFavAlbums(@PathVariable(value = "userId") Long userId) {
		return userService.getUserFavAlbums(userId);
	}
	
	// get user's favorite playlists
	@RequestMapping(value = "/{userId}/favorite-playlists", method = RequestMethod.GET)
	public List<Playlist> getUserFavPlaylists(@PathVariable(value = "userId") Long userId) {
		return userService.getUserFavPlaylists(userId);
	}
	
	// get user's following
	@RequestMapping(value = "/{userId}/following", method = RequestMethod.GET)
	public List<Artist> getUserFollowing(@PathVariable(value = "userId") Long userId){
		return userService.getUserFollowing(userId);
	}
	
	// add favorite song
	@RequestMapping(value = "/{userId}/favorite-song/{songId}", method = RequestMethod.PUT)
	public Boolean addFavSong(@PathVariable(value = "userId") Long userId, @PathVariable(value = "songId") Long songId) {
		return userService.addFavSong(userId, songId);
	}
	
	// is favorite song
	@RequestMapping(value = "/{userId}/favorite-song/{songId}", method = RequestMethod.GET)
	public Boolean isFavSong(@PathVariable(value = "userId") Long userId, @PathVariable(value = "songId") Long songId) {
		return userService.isFavSong(userId, songId);
	}
	
	// remove favorite song
	@RequestMapping(value = "/{userId}/favorite-song/remove/{songId}", method = RequestMethod.PUT)
	public Boolean removeFavSong(@PathVariable(value = "userId") Long userId, @PathVariable(value = "songId") Long songId) {
		return userService.removeFavSong(userId, songId);
	}
	
	// add favorite album
	@RequestMapping(value = "/{userId}/favorite-album/{albumId}", method = RequestMethod.PUT)
	public Boolean addFavAlbum(@PathVariable(value = "userId") Long userId, @PathVariable(value = "albumId") Long albumId) {
		return userService.addFavAlbum(userId, albumId);
	}
	
	// is favorite album
	@RequestMapping(value = "/{userId}/favorite-album/{albumId}", method = RequestMethod.GET)
	public Boolean isFavAlbum(@PathVariable(value = "userId") Long userId, @PathVariable(value = "albumId") Long albumId) {
		return userService.isFavAlbum(userId, albumId);
	}
	
	// remove favorite album
	@RequestMapping(value = "/{userId}/favorite-album/remove/{albumId}", method = RequestMethod.PUT)
	public Boolean removeFavAlbum(@PathVariable(value = "userId") Long userId, @PathVariable(value = "albumId") Long albumId) {
		return userService.removeFavAlbum(userId, albumId);
	}
	
	// add favorite playlist
	@RequestMapping(value = "/{userId}/favorite-playlist/{playlistId}", method = RequestMethod.PUT)
	public Boolean addFavPlaylist(@PathVariable(value = "userId") Long userId, @PathVariable(value = "playlistId") Long playlistId) {
		return userService.addFavPlaylist(userId, playlistId);
	}
	
	// is favorite playlist
	@RequestMapping(value = "/{userId}/favorite-playlist/{playlistId}", method = RequestMethod.GET)
	public Boolean isFavPlaylist(@PathVariable(value = "userId") Long userId, @PathVariable(value = "playlistId") Long playlistId) {
		return userService.isFavPlaylist(userId, playlistId);
	}
	
	// remove favorite playlist
	@RequestMapping(value = "/{userId}/favorite-playlist/remove/{playlistId}", method = RequestMethod.PUT)
	public Boolean removeFavPlaylist(@PathVariable(value = "userId") Long userId, @PathVariable(value = "playlistId") Long playlistId) {
		return userService.removeFavPlaylist(userId, playlistId);
	}
	
	// get number of users
	@RequestMapping(value = "/number-of-users", method = RequestMethod.GET)
	public Long getNumberOfUsers() {
		return userService.getNumberOfUsers();
	}
}
