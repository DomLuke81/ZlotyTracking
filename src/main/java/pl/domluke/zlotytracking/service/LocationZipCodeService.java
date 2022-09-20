package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.repository.LocationZipCodeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationZipCodeService {
    private final LocationZipCodeRepository zipCodeRepository;

    @Autowired
    public LocationZipCodeService(LocationZipCodeRepository locationRepository) {
        this.zipCodeRepository = locationRepository;
    }

    public List<LocationZipCode> getLast5Entries() {
        return zipCodeRepository.findLastLimitedTo(5);
    }

    public Page<LocationZipCode> getAllOnPages(int page, int itemsOnPage) {
        return zipCodeRepository.findAllByOrderByZipCode(PageRequest.of(page, itemsOnPage));
    }

    public Optional<LocationZipCode> getLocationById(int id) {
        return zipCodeRepository.findById(id);
    }

    public LocationZipCode save (LocationZipCode locationZipCode) {
        return zipCodeRepository.save(locationZipCode);
    }
}
