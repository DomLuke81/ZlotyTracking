package pl.domluke.zlotytracking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.LocationZipCode;

import java.util.List;

@Repository
@Transactional
public interface LocationZipCodeRepository extends JpaRepository<LocationZipCode, Integer> {

    @Query(value = "SELECT * FROM zip_codes ORDER BY id DESC LIMIT :numberOfEntries", nativeQuery = true)
    List<LocationZipCode> findLastLimitedTo(int numberOfEntries);

    Page<LocationZipCode> findAllByOrderByZipCode(Pageable pageable);

    @Query(value = "SELECT distinct zc.voivodeship FROM LocationZipCode zc WHERE zc.active = true ORDER BY zc.voivodeship")
    List<String> findAllVoivodeships();

    @Query(value = "SELECT distinct zc.county FROM LocationZipCode zc WHERE zc.active = true " +
            "and zc.voivodeship = :voivodeship ORDER BY zc.county")
    List<String> findAllCounties(String voivodeship);

}