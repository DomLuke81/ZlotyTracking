package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Entity
@Table(name = "note_type",
        uniqueConstraints = @UniqueConstraint(columnNames = {"value", "edition"}))
public class NoteType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull
    @Min(10)
    private int value;

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
        noteTypeDto.setId(this.id);
        noteTypeDto.setValue(this.value);
        noteTypeDto.setEdition(this.edition);
        noteTypeDto.setImage(new String(Base64.getEncoder().encode(this.image), StandardCharsets.UTF_8));
        noteTypeDto.setActive(this.active);
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
