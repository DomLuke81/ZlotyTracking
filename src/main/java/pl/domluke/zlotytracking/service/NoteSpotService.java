package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.*;
import pl.domluke.zlotytracking.repository.NoteRepository;
import pl.domluke.zlotytracking.repository.NoteSpotRepository;
import pl.domluke.zlotytracking.repository.NoteTypeRepository;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteSpotService {

    private final NoteSpotRepository noteSpotRepository;
    private final NoteRepository noteRepository;
    private final NoteTypeRepository noteTypeRepository;

    @Autowired
    public NoteSpotService(NoteSpotRepository noteSpotRepository, NoteRepository noteRepository,
                           NoteTypeRepository noteTypeRepository) {
        this.noteSpotRepository = noteSpotRepository;
        this.noteRepository = noteRepository;
        this.noteTypeRepository = noteTypeRepository;
    }

    public NoteSpotDto getNoteSpotById(long id) {
        return noteSpotRepository.findById(id).orElse(new NoteSpot()).toDto();
    }

    public void save(NoteSpotDto noteSpotDto, User user) {
        NoteSpot noteSpot = new NoteSpot();
        noteSpot.setId(noteSpotDto.getId());
        noteSpot.setNote(getNote(noteSpotDto.getNoteTypeDto().getId(),
                noteSpotDto.getNoteSerialNumber().toUpperCase()));
        noteSpot.setZipCode(noteSpotDto.getPlace());
        noteSpot.setUser(user);
        System.out.println(ZoneId.systemDefault());
        noteSpot.setSpotTime(ZonedDateTime.of(
                LocalDate.parse(noteSpotDto.getSpotTime(), DateTimeFormatter.ISO_LOCAL_DATE),
                LocalTime.of(12, 0),
                ZoneId.systemDefault()));
        noteSpot.setDescription(noteSpotDto.getDescription());
        noteSpotRepository.save(noteSpot);
    }

    private Note getNote(int noteTypeId, String serialNumber) {
        Optional<Note> optionalNote = noteRepository.findByEmisja_IdAndSerialNumber(noteTypeId, serialNumber);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            Note note = new Note(serialNumber, noteTypeRepository.findById(noteTypeId).get());
            noteRepository.save(note);
            return note;
        }
    }

    public List<NoteSpotDto> getLast5EntriesByUser(User user) {
        return noteSpotRepository.findLastByUserIdLimitedTo(user.getId(), 5)
                .stream().map(NoteSpot::toDto)
                .collect(Collectors.toList());
    }
}
