package pl.domluke.zlotytracking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zip_codes")
public class LocationZipCode {
    @Id
    @Column(name = "zip_code",
            unique = true,
            columnDefinition = "CHAR",
            length = 6)
    private String zipCode;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String voivodeship;

    @Column(nullable = false)
    private String county;
}
