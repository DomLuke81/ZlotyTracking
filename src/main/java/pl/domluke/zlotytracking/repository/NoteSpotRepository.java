package pl.domluke.zlotytracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.NoteSpot;

import java.util.Optional;

@Repository
@Transactional
public interface NoteSpotRepository extends JpaRepository<NoteSpot, Long> {

    Optional<NoteSpot> findById(long id);
}
