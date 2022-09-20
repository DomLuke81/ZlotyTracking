package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<NoteType> getAllOnPages(int page, int itemsOnPage) {
        return noteTypeRepository.findAllByOrderByValueAscEditionAsc(PageRequest.of(page, itemsOnPage));
    }
}
