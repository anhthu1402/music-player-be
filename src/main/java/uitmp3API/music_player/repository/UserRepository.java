package uitmp3API.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uitmp3API.music_player.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}