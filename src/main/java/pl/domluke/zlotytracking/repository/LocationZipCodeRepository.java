package pl.domluke.zlotytracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domluke.zlotytracking.domain.LocationZipCode;

@Repository
public interface LocationZipCodeRepository extends JpaRepository<LocationZipCode, String> {
}