package pl.domluke.zlotytracking.repository;

import pl.domluke.zlotytracking.domain.NoteType;

public interface NoteTypeAuxRepository {

    boolean saveWithoutImage(NoteType noteType);

    boolean saveOnlyImage(int id, byte[] image);

    boolean deleteImage(int id);

}
