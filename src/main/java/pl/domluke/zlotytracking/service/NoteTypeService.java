package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.domain.NoteType;
import pl.domluke.zlotytracking.repository.NoteTypeRepository;

import java.util.List;

@Service
public class NoteTypeService {

    private final NoteTypeRepository noteTypeRepository;

    @Autowired
    public NoteTypeService(NoteTypeRepository noteTypeRepository) {
        this.noteTypeRepository = noteTypeRepository;
    }

    public List<NoteType> getLast5Entries() {
        return noteTypeRepository.findLastLimitedTo(5);
    }

}
