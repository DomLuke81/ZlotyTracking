package pl.domluke.zlotytracking.domain;

import java.util.List;

public class NoteDto {
    private long id;
    private String serialNumber;
    private NoteTypeDto noteType;
    private List<NoteSpotDto> spots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public NoteTypeDto getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteTypeDto noteType) {
        this.noteType = noteType;
    }

    public List<NoteSpotDto> getSpots() {
        return spots;
    }

    public void setSpots(List<NoteSpotDto> spots) {
        this.spots = spots;
    }
}
