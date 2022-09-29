package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.NoteSpot;
import pl.domluke.zlotytracking.domain.NoteSpotDto;
import pl.domluke.zlotytracking.domain.User;
import pl.domluke.zlotytracking.repository.NoteSpotRepository;

@Service
public class NoteSpotService {

    private final NoteSpotRepository noteSpotRepository;

    @Autowired
    public NoteSpotService(NoteSpotRepository noteSpotRepository) {
        this.noteSpotRepository = noteSpotRepository;
    }

    public NoteSpotDto getNoteSpotById(long id) {
        return noteSpotRepository.findById(id).orElse(new NoteSpot()).toDto();
    }

    public boolean save(NoteSpotDto noteSpotDto, User user) {
        return false;
    }
}
