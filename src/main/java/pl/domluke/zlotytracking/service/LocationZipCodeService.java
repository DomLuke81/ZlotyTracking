package pl.domluke.zlotytracking.service;

import org.springframework.stereotype.Service;
import pl.domluke.zlotytracking.repository.LocationZipCodeRepository;

@Service
public class LocationZipCodeService {
    private final LocationZipCodeRepository locationRepository;

    public LocationZipCodeService(LocationZipCodeRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

}
