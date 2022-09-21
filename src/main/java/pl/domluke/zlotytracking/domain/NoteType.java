package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    @Transient
    private String imageToShow;

    @Column
    private boolean active;

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

    public String getImageToShow() {
        return imageToShow;
    }

    public void setImageToShow(String imageToShow) {
        this.imageToShow = imageToShow;
    }
}
