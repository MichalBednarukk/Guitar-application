package com.example.guitar.gui;

import com.example.guitar.service.TrackService;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("trackget")
public class TrackGetGui extends VerticalLayout {
    TrackService trackService;

    TextField trackName;
    TextArea trackBody;

    @Autowired
    public TrackGetGui(TrackService trackService) {
        this.trackService = trackService;
        initialize();
    }

    public void initialize() {

        trackName = new TextField();
        trackName.setValue(trackService.getTrack("1").getName());
        trackBody = new TextArea("Description");
        trackBody.getStyle().set("50%","50%");

        trackBody.setValue(trackService.getTrack("1").getBody());

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(trackName, trackBody);
        verticalLayout.setAlignItems(Alignment.CENTER);
        add(verticalLayout);
    }
}
