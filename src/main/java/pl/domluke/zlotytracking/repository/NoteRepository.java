package pl.domluke.zlotytracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.Note;

import java.util.Optional;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByEmisja_IdAndSerialNumber(int noteTypeId, String serialnumber);

    Optional<Note> findBySpotsId(long spotId);
}
