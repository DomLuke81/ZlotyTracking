package pl.domluke.zlotytracking.domain;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NoteTypeDto {
    private int id;

    @NotNull
    @Min(10)
    private int value;

    @NotNull
    @Min(1994)
    @Max(2050)
    private short edition;

    private String image;

    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public short getEdition() {
        return edition;
    }

    public void setEdition(short edition) {
        this.edition = edition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
