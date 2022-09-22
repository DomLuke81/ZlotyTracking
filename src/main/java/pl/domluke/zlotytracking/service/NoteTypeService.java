package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.NoteType;
import pl.domluke.zlotytracking.domain.NoteTypeDto;
import pl.domluke.zlotytracking.repository.NoteTypeRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteTypeService {

    private final NoteTypeRepository noteTypeRepository;

    @Autowired
    public NoteTypeService(NoteTypeRepository noteTypeRepository) {
        this.noteTypeRepository = noteTypeRepository;
    }

    public List<NoteTypeDto> getLast5Entries() {
        return noteTypeRepository.findLastLimitedTo(5)
                .stream().map(NoteType::toDto)
                .collect(Collectors.toList());
    }

    public Page<NoteTypeDto> getAllOnPages(int page, int itemsOnPage) {
        Page<NoteType> pages = noteTypeRepository.findAllByOrderByDenominationAscEditionAsc(PageRequest.of(page, itemsOnPage));
        List<NoteTypeDto> noteTypeDtoList = pages.getContent().stream().map(NoteType::toDto).collect(Collectors.toList());
        return new PageImpl<>(noteTypeDtoList, PageRequest.of(page, itemsOnPage), pages.getTotalElements());
    }

    public NoteTypeDto getNoteTypeById(int id) {
        return noteTypeRepository.findById(id).orElse(new NoteType()).toDto();
    }

    public boolean saveWithoutImage(NoteTypeDto noteTypeDto) {
        return noteTypeRepository.saveWithoutImage(noteTypeDto.toEntity());
    }

    public boolean addImageToNoteType(NoteTypeDto noteTypeDto, byte[] image) {
        if (noteTypeDto.getId() == 0) {
            Optional<NoteType> noteTypeOptional = noteTypeRepository.findNoteTypeByDenominationAndEdition(
                    noteTypeDto.getDenomination(), noteTypeDto.getEdition());
            if (noteTypeOptional.isEmpty()) {
                return false;
            }
            noteTypeDto.setId(noteTypeOptional.get().getId());
        }
        return noteTypeRepository.saveOnlyImage(noteTypeDto.getId(), image);
    }

    public NoteTypeDto delete(int id) {
        Optional<NoteType> noteTypeOptional = noteTypeRepository.findById(id);
        if (noteTypeOptional.isPresent()) {
            NoteType noteType = noteTypeOptional.get();
            noteType.setActive(!noteType.isActive());
            noteTypeRepository.save(noteType);
            return noteType.toDto();
        }
        return null;
    }
}
