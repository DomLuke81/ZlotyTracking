package pl.domluke.zlotytracking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.NoteType;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface NoteTypeRepository extends JpaRepository<NoteType, Integer>, NoteTypeAuxRepository {

    @Query(value = "SELECT * FROM note_type ORDER BY id DESC LIMIT :numberOfEntries", nativeQuery = true)
    List<NoteType> findLastLimitedTo(int numberOfEntries);

    Page<NoteType> findAllByOrderByDenominationAscEditionAsc(Pageable pageable);

    Optional<NoteType> findFirstByDenominationAndEdition(int denomination, short edition);

    @Query(value = "SELECT * FROM note_type WHERE id IN (SELECT MAX(id) FROM note_type GROUP BY denomination)",
            nativeQuery = true)
    List<NoteType> getLastForEveryDenominations();
}