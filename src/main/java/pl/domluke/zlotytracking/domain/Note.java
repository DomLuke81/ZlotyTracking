package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "notes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"note_type_id", "serial_number"}))
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_number", nullable = false, columnDefinition = "CHAR", length = 9)
    @NotNull
    @Pattern(regexp = "[A-Z]{2}\\d{7}")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "note_type_id", nullable = false)
    @NotNull
    private NoteType emisja;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "note")
    private Set<NoteSpot> spots;

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

    public NoteType getEmisja() {
        return emisja;
    }

    public void setEmisja(NoteType emisja) {
        this.emisja = emisja;
    }

    public Set<NoteSpot> getSpots() {
        return spots;
    }

    public void setSpots(Set<NoteSpot> spots) {
        this.spots = spots;
    }

    public Note() {
    }

    public Note(String serialNumber, NoteType emisja) {
        this.serialNumber = serialNumber;
        this.emisja = emisja;
    }

    public NoteDto toDto() {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(id);
        noteDto.setSerialNumber(serialNumber);
        noteDto.setNoteType(emisja.toDto());
        noteDto.setSpots(spots.stream()
                .map(NoteSpot::toDto)
//                .sorted()
                .collect(Collectors.toList())
        );
        return noteDto;
    }
}
