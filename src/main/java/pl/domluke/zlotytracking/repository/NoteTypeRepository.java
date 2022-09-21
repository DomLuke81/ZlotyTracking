package pl.domluke.zlotytracking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.NoteType;

import java.util.List;

@Repository
@Transactional
public interface NoteTypeRepository extends JpaRepository<NoteType, Integer>, NoteTypeAuxRepository {

    @Query(value = "SELECT * FROM note_type ORDER BY id DESC LIMIT :numberOfEntries", nativeQuery = true)
    List<NoteType> findLastLimitedTo(int numberOfEntries);

    Page<NoteType> findAllByOrderByValueAscEditionAsc(Pageable pageable);

}