package uitmp3API.music_player.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.dto.PlaylistSong;
import uitmp3API.music_player.dto.PlaylistUser;
import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.model.Song;
import uitmp3API.music_player.model.User;
import uitmp3API.music_player.repository.PlaylistRepository;
import uitmp3API.music_player.repository.SongRepository;
import uitmp3API.music_player.repository.UserRepository;

@Service
public class PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	SongRepository songRepository;
	
	@Autowired
	UserRepository userRepository;
	
	// create playlist
	public Playlist createPlaylist(Playlist p) {
		return playlistRepository.save(p);
	}
	
	// add user
	public PlaylistUser addUser(Long playlistId, Long userId) {
		try {
			Playlist p = playlistRepository.findById(playlistId).get();
			User u = userRepository.findById(userId).get();
			
			p.setUser(u);
			playlistRepository.save(p);
			return new PlaylistUser(p);
		} catch (Exception e) {
			return new PlaylistUser();
		}
	}
	
	// read all playlists
	public List<PlaylistUser> getAllPlaylists(){
		List<Playlist> playlists = playlistRepository.findAll();
		List<PlaylistUser> result = new ArrayList<PlaylistUser>();
		for (Playlist playlist : playlists) {
			PlaylistUser playlistUser = new PlaylistUser(playlist);
			result.add(playlistUser);
		}
		return result;
	}
	
	// read playlist by id
	public PlaylistSong getPlaylistById(Long id) {
		Optional<Playlist> p = playlistRepository.findById(id);
		if(p.isPresent()) {
			return new PlaylistSong(p.get());
		}
		return new PlaylistSong();
	}
	
	// read all user's playlists
	public List<PlaylistSong> getAllCreatedPlaylistsByUserId(Long userId) {
		var playlists = playlistRepository.findAll();
		var result = new ArrayList<PlaylistSong>();
		for (Playlist playlist : playlists) {
			if(userId != 0) {
				if(playlist.getUser() != null) {
					if(playlist.getUser().getId() == userId) {
						PlaylistSong playlistUser = new PlaylistSong(playlist);
						result.add(playlistUser);
					}
				}
			}
			else {
				if(playlist.getUser() == null) {
					PlaylistSong playlistUser = new PlaylistSong(playlist);
					result.add(playlistUser);
				}
			}
		}
		return result;
	}
	
	// add song
	public PlaylistSong addSongPlaylist(Long playlistId, Long songId) {
		try {
			Playlist playlist = playlistRepository.findById(playlistId).get();
			Song song = songRepository.findById(songId).get();
			
//			List<Song> songPlaylist = playlist.getSongPlaylist();
//			for (Song song2 : songPlaylist) {
//				if(!song2.getId().equals(songId)) {
//					playlist.addSongPlaylist(song);
//				}
//			}
			playlist.addSongPlaylist(song);
			playlistRepository.save(playlist);
			return new PlaylistSong(playlist);
		} catch (Exception e) {
			return new PlaylistSong();
		}
	}
	
	// update playlist
	public Playlist updatePlaylist(Long PlaylistId, Playlist pDetails) {
		Playlist playlist = playlistRepository.findById(PlaylistId)
				.map(p -> {
					p.setPlaylistImg(pDetails.getPlaylistImg());
					p.setPlaylistName(pDetails.getPlaylistName());
					return playlistRepository.save(p);
				}).orElseGet(() -> {
					return playlistRepository.save(pDetails);
				});
		return playlist;		
	}
	
	// delete playlist
	public Boolean deletePlaylist(Long playlistId) {
		Optional<Playlist> p = playlistRepository.findById(playlistId);
		if(p.isPresent()) {
			Playlist playlist = p.get();
			playlist.setUser(null);
			List<User> users = playlist.getFavplaylists();
			for (User user : users) {
				user.removeFavPlaylist(playlist);
			}
			if(playlist.getSongPlaylist().size() > 0) {
				List<Song> songs = playlist.getSongPlaylist();
				for (Song song : songs) {
					song.removePlaylist(playlist);
				}
			}
			playlistRepository.delete(playlist);
			return true;
		}
		return false;
	}
	
	// remove song
	public PlaylistSong removeSong(Long playlistId, Long songId) {
		try {
			Playlist p = playlistRepository.findById(playlistId).get();
			Song s = songRepository.findById(songId).get();
			
			p.removeSongPlaylist(s);
			s.removePlaylist(p);
			songRepository.save(s);
			playlistRepository.save(p);
			return new PlaylistSong(p);
		} catch (Exception e) {
			return new PlaylistSong();
		}
	}
	
	// get number of playlists
	public Long getNumberOfPlaylists() {
		return playlistRepository.count();
	}
}
