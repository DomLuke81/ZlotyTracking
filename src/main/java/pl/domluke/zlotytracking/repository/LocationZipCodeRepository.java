package pl.domluke.zlotytracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.LocationZipCode;

import java.util.List;

@Repository
@Transactional
public interface LocationZipCodeRepository extends JpaRepository<LocationZipCode, String> {

    @Query(value = "SELECT * FROM zip_codes ORDER BY id DESC LIMIT :numberOfEntries", nativeQuery = true)
    List<LocationZipCode> findLastLimitedTo (int numberOfEntries);
}