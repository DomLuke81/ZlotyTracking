package pl.domluke.zlotytracking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.Note;

import java.util.Optional;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findByEmisja_IdAndSerialNumber(int noteTypeId, String serialnumber);

    Optional<Note> findBySpotsId(long spotId);

    @Query(value = "select n2.* from " +
            "(SELECT n.*, count(n.id) AS spots FROM notes n JOIN note_spot ns on n.id = ns.note_id GROUP BY n.id) n2 " +
            "JOIN note_spot ns2 ON n2.id = ns2.note_id WHERE user_id = :userId AND spots > :numberSpots",
            countQuery = "select count(n2.id) from " +
                    "(SELECT n.*, count(n.id) AS spots FROM notes n JOIN note_spot ns on n.id = ns.note_id GROUP BY n.id) n2 " +
                    "JOIN note_spot ns2 ON n2.id = ns2.note_id WHERE user_id = :userId AND spots > :numberSpots",
            nativeQuery = true)
    Page<Note> findByUserWhereSpotsCountIsMoreThan(int numberSpots, long userId, Pageable pageable);

    @Query(value = "SELECT * FROM " +
            "(SELECT *, count(id) AS spots FROM " +
            "(SELECT n.* FROM notes n JOIN note_spot ns on n.id = ns.note_id ORDER BY spot_time DESC) AS n2 " +
            "GROUP BY id) AS n3 " +
            "WHERE spots > :numberSpots",
            countQuery = "SELECT * FROM " +
                    "(SELECT *, count(id) AS spots FROM " +
                    "(SELECT n.* FROM notes n JOIN note_spot ns on n.id = ns.note_id ORDER BY spot_time DESC) AS n2 " +
                    "GROUP BY id) AS n3 " +
                    "WHERE spots > :numberSpots",
            nativeQuery = true)
    Page<Note> findWhereSpotsCountIsMoreThanByOrderBySpotTimeDesc(int numberSpots, Pageable pageable);
}
