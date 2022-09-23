package pl.domluke.zlotytracking.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.domluke.zlotytracking.domain.NoteType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
public class NoteTypeAuxRepositoryImpl implements NoteTypeAuxRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveWithoutImage(NoteType noteType) {
        Query query;
        if (noteType.getId() == 0) {
            query = entityManager
                    .createNativeQuery("INSERT INTO note_type (denomination, edition, active, image) " +
                            "VALUES (:denomination, :edition, true, null)");
        } else {
            query = entityManager
                    .createNativeQuery("UPDATE note_type SET denomination=:denomination, edition=:edition WHERE id=:id")
                    .setParameter("id", noteType.getId());
        }
        query.setParameter("denomination", noteType.getDenomination())
                .setParameter("edition", noteType.getEdition());
        try {
            if (query.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean saveOnlyImage(int id, byte[] image) {
        return (entityManager.createNativeQuery("UPDATE note_type SET image=:image WHERE id=:id")
                .setParameter("image", image)
                .setParameter("id", id)
                .executeUpdate()) == 1;
    }

}
