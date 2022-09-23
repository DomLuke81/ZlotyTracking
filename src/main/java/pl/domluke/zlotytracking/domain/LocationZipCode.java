package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "zip_codes")
public class LocationZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "zip_code",
            unique = true,
            columnDefinition = "CHAR",
            length = 6,
            nullable = false)
    @NotBlank
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Musi mieć formę 00-000")
    private String zipCode;

    @Column(nullable = false)
    @NotBlank
    private String place;

    @Column(nullable = false)
    @NotBlank
    private String voivodeship;

    @Column(nullable = false)
    @NotBlank
    private String county;

    @Column
    private boolean active;

    public LocationZipCode() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean inUse) {
        this.active = inUse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public void setCounty(String county) {
        this.county = county;
    }

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
