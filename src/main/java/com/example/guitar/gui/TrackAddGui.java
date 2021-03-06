package com.example.guitar.gui;

import com.example.guitar.repository.TrackRepo;
import com.example.guitar.service.TrackService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

@Route("trackaddgui")
public class TrackAddGui extends VerticalLayout {

    TrackService trackService;
    TrackRepo trackRepo;

    TextField trackName;
    TextArea trackBody;
    Button addButton;
    DatePicker datePicker;


    @Autowired
    public TrackAddGui(TrackRepo trackRepo,TrackService trackService) {
    this.trackRepo = trackRepo;
    this.trackService = trackService;

    initialize();

    addButton.addClickListener(buttonClickEvent -> {
            if (this.trackService.addTrack(SecurityContextHolder.getContext().getAuthentication().getName(),trackBody.getValue(),trackName.getValue(),datePicker.getValue()).getStatusCodeValue() == 200) {
                Notification notification = new Notification(
                        "Track add Succesfully", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            } else {
                Notification notification = new Notification(
                        "Track add Unsuccesfull", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            }
        });

    }

    public void initialize() {
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        trackName = new TextField("Track name");

        trackBody = new TextArea("Description");
        trackBody.getStyle().set("50%","50%");

        trackBody.setPlaceholder("Write here ...");

        addButton = new Button("Confirm");

        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(trackName, trackBody, addButton);
        verticalLayout.setAlignItems(Alignment.CENTER);
        add(verticalLayout);
    }

}
