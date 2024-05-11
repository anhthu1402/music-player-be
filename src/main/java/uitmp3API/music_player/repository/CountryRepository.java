package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{
	Country findByCountryName(String countryName);
}
