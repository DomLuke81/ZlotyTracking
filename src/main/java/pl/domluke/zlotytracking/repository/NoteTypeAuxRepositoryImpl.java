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
    public NoteType saveWithoutImage(NoteType noteType) {
        Query query;
        if (noteType.getId() == 0) {
            query = entityManager
                    .createNativeQuery("INSERT INTO note_type (denomination, edition, active, image) " +
                            "VALUES (:denomination, :edition, true, null)")
                    .setParameter("denomination", noteType.getDenomination())
                    .setParameter("edition", noteType.getEdition());
            try {
                noteType = (NoteType) query.getSingleResult();
            } catch (Exception e){
                e.printStackTrace();
                noteType = null;
            }
        } else {
            query = entityManager
                    .createNativeQuery("UPDATE note_type SET denomination=:denomination, edition=:edition WHERE id=:id")
                    .setParameter("denomination", noteType.getDenomination())
                    .setParameter("edition", noteType.getEdition())
                    .setParameter("id", noteType.getId());
            if (query.executeUpdate() == 1) {
                noteType = (NoteType) entityManager.createQuery("SELECT nt FROM NoteType nt WHERE id=:id")
                        .setParameter("id", noteType.getId())
                        .getSingleResult();
            } else {
                noteType = null;
            }
        }
        return noteType;
    }

    @Override
    public boolean saveOnlyImage(int id, byte[] image) {
        return false;
    }

    @Override
    public boolean deleteImage(int id) {
        return false;
    }
}
