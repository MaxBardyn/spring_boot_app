package ul.lviv.iot.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.lviv.iot.springjpa.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
