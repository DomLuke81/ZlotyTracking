package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Entity
@Table(name = "note_type",
        uniqueConstraints = @UniqueConstraint(columnNames = {"denomination", "edition"}))
public class NoteType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull
    @Min(10)
    @Max(2000000000)
    private int denomination;

    @Column(nullable = false)
    @NotNull
    @Min(1994)
    @Max(2050)
    private short edition;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Column
    private boolean active;

    public NoteTypeDto toDto() {
        NoteTypeDto noteTypeDto = new NoteTypeDto();
        noteTypeDto.setId(id);
        noteTypeDto.setDenomination(denomination);
        noteTypeDto.setEdition(edition);
        if (image != null) {
            noteTypeDto.setImage(new String(Base64.getEncoder().encode(image), StandardCharsets.UTF_8));
        }
        noteTypeDto.setActive(active);
        return noteTypeDto;
    }

    public NoteType() {
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int value) {
        this.denomination = value;
    }

    public short getEdition() {
        return edition;
    }

    public void setEdition(short edition) {
        this.edition = edition;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
