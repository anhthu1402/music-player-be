package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.List;

import uitmp3API.music_player.model.Playlist;
import uitmp3API.music_player.model.Song;

public class PlaylistSong {
	public Long id;
	public String playlistName;
	public String playlistImg;
	public Long userId;
	public String userName;
	public List<Song> songPlaylist = new ArrayList<>();
	
	public PlaylistSong() {
		// TODO Auto-generated constructor stub
	}
	
	public PlaylistSong(Playlist p) {
		id=p.getId();
		playlistImg=p.getPlaylistImg();
		playlistName=p.getPlaylistName();
		if(p.getUser() != null) {
			userName=p.getUser().getUserName();
			userId=p.getUser().getId();
		}
		else {
			userName="UIT MP3";
		}
		songPlaylist=p.getSongPlaylist();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public String getPlaylistImg() {
		return playlistImg;
	}

	public void setPlaylistImg(String playlistImg) {
		this.playlistImg = playlistImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Song> getSongPlaylist() {
		return songPlaylist;
	}

	public void setSongPlaylist(List<Song> songPlaylist) {
		this.songPlaylist = songPlaylist;
	}
}
