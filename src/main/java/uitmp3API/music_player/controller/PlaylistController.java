package uitmp3API.music_player.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uitmp3API.music_player.dto.PlaylistSong;
import uitmp3API.music_player.dto.PlaylistUser;
import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.service.PlaylistService;

@RestController
@CrossOrigin
@RequestMapping("/api/playlists")
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;
	
	// create playlist
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Playlist createPlaylist(@RequestBody Playlist p) {
		return playlistService.createPlaylist(p);
	}
	
	// add user
	@RequestMapping(value = "/{playlistId}/user/{userId}", method = RequestMethod.PUT)
	public PlaylistUser addUser(@PathVariable(value = "playlistId") Long playlistId, @PathVariable(value = "userId") Long userId) {
		return playlistService.addUser(playlistId, userId);
	}
	
	// read all playlists
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PlaylistUser> getAllPlaylists(){
		return playlistService.getAllPlaylists();
	}
	
	// read playlist by id
	@RequestMapping(value = "/{playlistId}", method = RequestMethod.GET)
	public PlaylistSong getPlaylistById(@PathVariable(value = "playlistId") Long id) {
		return playlistService.getPlaylistById(id);
	}
	
	// read all user's playlists
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public List<PlaylistSong> getAllCreatedPlaylistsByUserId(@PathVariable(value = "userId") Long userId){
		return playlistService.getAllCreatedPlaylistsByUserId(userId);
	}
	
	// add song
	@RequestMapping(value = "/{playlistId}/song/{songId}", method = RequestMethod.PUT)
	public PlaylistSong addSongPlaylist(@PathVariable(value = "playlistId") Long playlistId, @PathVariable(value = "songId") Long songId) {
		return playlistService.addSongPlaylist(playlistId, songId);
	}
	
	// update playlist
	@RequestMapping(value = "/{playlistId}", method = RequestMethod.PUT)
	public Playlist updatePlaylist(@PathVariable(value = "playlistId") Long playlistId, @RequestBody Playlist pDetails) {
		return playlistService.updatePlaylist(playlistId, pDetails);
	}
	
	// delete playlist
	@RequestMapping(value = "/{playlistId}", method = RequestMethod.DELETE)
	public Boolean deletePlaylist(@PathVariable(value = "playlistId") Long playlistId) {
		return playlistService.deletePlaylist(playlistId);
	}
	
	// remove song
	@RequestMapping(value = "/{playlistId}/song/remove/{songId}", method = RequestMethod.PUT)
	public PlaylistSong removeSong(@PathVariable(value = "playlistId") Long playlistId, @PathVariable(value = "songId") Long songId) {
		return playlistService.removeSong(playlistId, songId);
	}
	
	// get number of playlists
	@RequestMapping(value = "/number-of-playlists", method = RequestMethod.GET)
	public Long getNumberOfPlaylists() {
		return playlistService.getNumberOfPlaylists();
	}
}
