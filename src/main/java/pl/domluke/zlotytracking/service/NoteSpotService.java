package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.*;
import pl.domluke.zlotytracking.repository.NoteRepository;
import pl.domluke.zlotytracking.repository.NoteSpotRepository;
import pl.domluke.zlotytracking.repository.NoteTypeRepository;

import javax.persistence.EntityNotFoundException;
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

    public NoteDto getNoteBySpotIdWithHiddenPoles(long id) {
        NoteDto note = getNoteBySpotId(id);
        note.setSerialNumber(putAsterisks(note.getSerialNumber()));
        note.getSpots()
                .forEach(s -> s.setUserName(putAsterisks(s.getUserName())));
        return note;
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

    public Page<NoteSpotDto> getAllByUserOnPages(User user, int page, int itemsOnPage) {
        PageRequest pageRequest = PageRequest.of(page, itemsOnPage);
        Page<NoteSpot> pages = noteSpotRepository.findAllByUserOrderBySpotTimeDesc(user, pageRequest);
        List<NoteSpotDto> noteSpotDtoList = pages.getContent().stream()
                .map(NoteSpot::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(noteSpotDtoList, pageRequest, pages.getTotalElements());
    }

    public NoteDto getNoteBySpotId(long id) {
        return noteRepository.findBySpotsId(id).orElseThrow(EntityNotFoundException::new).toDto();
    }

    public Page<NoteDto> getAllByUserAndMoreSpotsOnPages(User user, int spots, int page, int itemsOnPage) {
        PageRequest pageRequest = PageRequest.of(page, itemsOnPage);
        Page<Note> pages = noteRepository.findByUserWhereSpotsCountIsMoreThan(spots, user.getId(), pageRequest);
        List<NoteDto> noteDtoList = pages.getContent().stream()
                .map(Note::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(noteDtoList, pageRequest, pages.getTotalElements());
    }

    public Page<NoteDto> getAllHitsSortedByDateDescOnPages(int spots, int page, int itemsOnPage) {
        PageRequest pageRequest = PageRequest.of(page, itemsOnPage);
        Page<Note> pages =
                noteRepository.findWhereSpotsCountIsMoreThanByOrderBySpotTimeDesc(spots, pageRequest);
        List<NoteDto> noteDtoList = pages.getContent().stream()
                .map(Note::toDto)
                .peek(n -> n.setSerialNumber(putAsterisks(n.getSerialNumber())))
                .collect(Collectors.toList());
        return new PageImpl<>(noteDtoList, pageRequest, pages.getTotalElements());
    }

    public Page<NoteSpotDto> getAllSpotsSortedByDateDescOnPages(int page, int itemsOnPage) {
        PageRequest pageRequest = PageRequest.of(page, itemsOnPage);
        Page<NoteSpot> pages = noteSpotRepository.findAllByOrderBySpotTimeDesc(pageRequest);
        List<NoteSpotDto> noteSpotDtoList = pages.getContent().stream()
                .map(NoteSpot::toDto)
                .peek(n -> n.setNoteSerialNumber(putAsterisks(n.getNoteSerialNumber())))
                .peek(n -> n.setUserName(putAsterisks(n.getUserName())))
                .collect(Collectors.toList());
        return new PageImpl<>(noteSpotDtoList, pageRequest, pages.getTotalElements());
    }

    private String putAsterisks(String input) {
        return input.charAt(0) + "*".repeat(Math.max(0, input.length() - 2)) + input.charAt(input.length() - 1);
    }
}
