package pl.domluke.zlotytracking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    public String getZipCode() {
        return zipCode;
    }

    public String getPlace() {
        return place;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCounty() {
        return county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationZipCode that = (LocationZipCode) o;
        return zipCode.equals(that.zipCode) && place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, place);
    }
}
