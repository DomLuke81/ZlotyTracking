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
import java.util.stream.Collectors;

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

    public Page<NoteTypeDto> getAllOnPages(int page, int itemsOnPage) {
        Page<NoteType> pages = noteTypeRepository.findAllByOrderByValueAscEditionAsc(PageRequest.of(page, itemsOnPage));
        List<NoteTypeDto> noteTypeDtoList = pages.getContent().stream().map(NoteType::toDto).collect(Collectors.toList());
        return new PageImpl<>(noteTypeDtoList, PageRequest.of(page, itemsOnPage), pages.getTotalElements());
    }
}
