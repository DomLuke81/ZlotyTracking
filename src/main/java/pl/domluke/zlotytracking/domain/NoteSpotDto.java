package pl.domluke.zlotytracking.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoteSpotDto {
    private long id;

    @NotNull
    private NoteTypeDto noteTypeDto;

    //to keep information for noteSpotForm what not was chosen
    private int denominationRadios;

    @NotNull
    @Pattern(regexp = "[A-z]{2}\\d{7}")
    private String noteSerialNumber;

    @NotNull
    private LocationZipCode place;

    private String spotTime;

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

    public LocationZipCode getPlace() {
        return place;
    }

    public void setPlace(LocationZipCode place) {
        this.place = place;
    }

    public String getSpotTime() {
        return spotTime;
    }

    public void setSpotTime(String spotTime) {
        this.spotTime = spotTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDenominationRadios() {
        return denominationRadios;
    }

    public void setDenominationRadios(int denominationRadios) {
        this.denominationRadios = denominationRadios;
    }
}
