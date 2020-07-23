package com.example.guitar.service;

import com.example.guitar.entity.TrackApp;
import com.example.guitar.entity.UserApp;
import com.example.guitar.repository.TrackRepo;
import com.example.guitar.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/track")
public class TrackService {

    TrackRepo trackRepo;
    UserRepo userRepo;

    @Autowired
    public TrackService(TrackRepo trackRepo, UserRepo userRepo){
        this.trackRepo = trackRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public ResponseEntity addTrack(String username,String trackBody, String trackName, LocalDate trackAddDate){
        Optional<UserApp> userFromDb = userRepo.findByUsername(username);

        if (userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
       }

        TrackApp trackApp = new TrackApp(userFromDb.get(),trackName,trackBody,trackAddDate);
        TrackApp savedTrackApp = trackRepo.save(trackApp);

        return ResponseEntity.ok(savedTrackApp);
    }


    @GetMapping("/get/{id}")
    public TrackApp getTrack(@PathVariable String id){
        Optional<TrackApp> trackFromDb = trackRepo.findById(Long.parseLong(id));
        return trackFromDb.get();
    }


}
