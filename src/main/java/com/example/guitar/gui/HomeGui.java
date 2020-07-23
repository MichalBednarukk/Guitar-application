package com.example.guitar.gui;

import com.example.guitar.entity.TrackApp;
import com.example.guitar.service.TrackService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("home")
public class HomeGui extends VerticalLayout {

    private String username;
    private String trackName;
    private Long id = Long.valueOf(1);
    TrackService trackService;
    TrackApp trackApp;

    @Autowired
    public HomeGui(TrackService trackService) {
        this.trackService = trackService;

        List<TrackApp> trackList = new ArrayList<>();

        trackApp = trackService.getTrack("1");
        trackList.add(0,trackApp);

        Grid<TrackApp> grid = new Grid<>(TrackApp.class);

        grid.setItems(trackList);
        grid.setColumns("name", "username", "addDate");

        add(grid);
    }

}






