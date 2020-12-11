package ul.lviv.iot.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.lviv.iot.springjpa.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
