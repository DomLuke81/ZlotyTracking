package pl.domluke.zlotytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.domain.LocationZipCode;
import pl.domluke.zlotytracking.repository.LocationZipCodeRepository;

import java.util.List;

@Service
public class LocationZipCodeService {
    private final LocationZipCodeRepository locationRepository;

    @Autowired
    public LocationZipCodeService(LocationZipCodeRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationZipCode> getLast5Entries() {
        return locationRepository.findLastLimitedTo(5);
    }

    public List<LocationZipCode> getAllOnPages(int page, int itemsOnPage) {
        return locationRepository.findAll();
    }

}
