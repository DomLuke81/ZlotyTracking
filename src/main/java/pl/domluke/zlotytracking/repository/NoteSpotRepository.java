package pl.domluke.zlotytracking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.NoteSpot;
import pl.domluke.zlotytracking.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface NoteSpotRepository extends JpaRepository<NoteSpot, Long> {

    Optional<NoteSpot> findById(long id);

    @Query(value = "SELECT * FROM note_spot WHERE user_id = :userId ORDER BY id DESC LIMIT :numberOfEntries",
            nativeQuery = true)
    List<NoteSpot> findLastByUserIdLimitedTo(long userId, int numberOfEntries);

    Page<NoteSpot> findAllByUserOrderBySpotTimeDesc(User user, Pageable pageable);

    Page<NoteSpot> findAllByOrderBySpotTimeDesc(Pageable pageable);
}
