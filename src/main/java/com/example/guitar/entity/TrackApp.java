package com.example.guitar.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class TrackApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    private UserApp user;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "LONGTEXT")
    private String body;

    @NonNull
    private LocalDate addDate;

    public String getUsername() {
        return user.getUsername();
    }
}
