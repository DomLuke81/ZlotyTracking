package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "note_spot")
public class NoteSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Note note;

    @ManyToOne
    @JoinColumn(name = "zip_code", nullable = false)
    @NotNull
    private LocationZipCode zipCode;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private User user;

    @Column(name = "spot_time", nullable = false)
    @NotNull
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public LocationZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(LocationZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public NoteSpotDto toDto() {
        NoteSpotDto noteSpotDto = new NoteSpotDto();
        noteSpotDto.setId(id);
        noteSpotDto.setNoteTypeDto(note == null ? null : note.getEmisja().toDto());
        noteSpotDto.setDenominationRadios(note == null ? 0 : note.getEmisja().getDenomination());
        noteSpotDto.setNoteSerialNumber(note == null ? null : note.getSerialNumber());
        noteSpotDto.setPlace(zipCode);
        noteSpotDto.setSpotTime(spotTime == null ? null : spotTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        noteSpotDto.setDescription(description);
        noteSpotDto.setUserName(user == null ? null : user.getName());
        return noteSpotDto;
    }
}
