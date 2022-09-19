package pl.domluke.zlotytracking.domain;

import javax.persistence.*;
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
    private int value;

    @Column(nullable = false)
    @NotNull
    private short edition;

    @Column(columnDefinition = "MEDIUMBLOB")
    private byte image;

}
