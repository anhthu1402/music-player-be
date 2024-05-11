package uitmp3API.music_player.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AlbumUpdateDto {
	private Long id;
	private String albumName;
	private Long interestTimes;
	private Date releaseDate;
	private String albumImage;
	private List<Long> artistId = new ArrayList<Long>();
	private Long countryId;
	
	public AlbumUpdateDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Long getInterestTimes() {
		return interestTimes;
	}

	public void setInterestTimes(Long interestTimes) {
		this.interestTimes = interestTimes;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAlbumImage() {
		return albumImage;
	}

	public void setAlbumImage(String albumImage) {
		this.albumImage = albumImage;
	}

	public List<Long> getArtistId() {
		return artistId;
	}

	public void setArtistId(List<Long> artistId) {
		this.artistId = artistId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
}
