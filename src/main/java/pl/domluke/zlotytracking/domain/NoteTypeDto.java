package pl.domluke.zlotytracking.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

public class NoteTypeDto {
    private int id;

    @NotNull
    @Min(10)
    @Max(2000000000)
    private int denomination;

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

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
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

    public NoteType toEntity() {
        NoteType noteType = new NoteType();
        noteType.setDenomination(denomination);
        noteType.setEdition(edition);
        noteType.setImage(image.getBytes(StandardCharsets.UTF_8));
        noteType.setActive(active);
        return noteType;
    }
}
