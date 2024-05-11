package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.List;

import uitmp3API.music_player.model.Album;
import uitmp3API.music_player.model.Artist;

public class ArtistAlbumDto {
	public Long id;
	public String artistImage;
	public String artistName;
	public String introduce;
	public Long numberOfFollower;
	public List<Album> albums = new ArrayList<>();
	
	public ArtistAlbumDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ArtistAlbumDto(Artist artist) {
		this.id = artist.getId();
		this.artistName = artist.getArtistName();
		this.artistImage = artist.getArtistImage();
		this.introduce = artist.getIntroduce();
		this.numberOfFollower = artist.getNumberOfFollower();
		this.albums = artist.getArtistAlbum();
	}
}
