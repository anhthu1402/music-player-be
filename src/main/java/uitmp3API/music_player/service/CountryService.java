package uitmp3API.music_player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uitmp3API.music_player.model.Country;
import uitmp3API.music_player.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;
	
	// create country
	public Country createCountry(Country country) {
		Country ctry = countryRepository.findByCountryName(country.getCountryName().trim());
		
		if(ctry != null)
			return new Country();
		
		return countryRepository.save(country);
	}
	
	// read all countries
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
	
	// update country by id
	public Country updateCountry(Long countryId, Country countryDetails) {
		Country country = countryRepository.findById(countryId)
				.map(ctry -> {
					ctry.setCountryName(countryDetails.getCountryName());
					return countryRepository.save(ctry);
				}).orElseGet(() -> {
					return countryRepository.save(countryDetails);
				});
		return country;
	}
}
