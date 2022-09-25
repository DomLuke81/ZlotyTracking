package pl.domluke.zlotytracking.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class NoteSpotDto {
    private long id;

    @NotNull
    private NoteTypeDto noteTypeDto;

    @NotNull
    @Pattern(regexp = "[A-Z]{2}\\d{7}")
    private String noteSerialNumber;

    @NotNull
    private LocationZipCode zipCode;

    private ZonedDateTime spotTime;

    @Column
    @Size(max = 255)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NoteTypeDto getNoteTypeDto() {
        return noteTypeDto;
    }

    public void setNoteTypeDto(NoteTypeDto noteTypeDto) {
        this.noteTypeDto = noteTypeDto;
    }

    public String getNoteSerialNumber() {
        return noteSerialNumber;
    }

    public void setNoteSerialNumber(String noteSerialNumber) {
        this.noteSerialNumber = noteSerialNumber;
    }

    public LocationZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(LocationZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public ZonedDateTime getSpotTime() {
        return spotTime;
    }

    public void setSpotTime(ZonedDateTime spotTime) {
        this.spotTime = spotTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
