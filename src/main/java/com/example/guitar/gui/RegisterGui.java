package com.example.guitar.gui;

import com.example.guitar.entity.UserApp;
import com.example.guitar.repository.UserRepo;
import com.example.guitar.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Route("registry")
public class RegisterGui extends VerticalLayout {

    UserRepo userRepo;
    UserService userService;

    Label label;
    TextField textFieldUsername;
    PasswordField passwordField;
    PasswordField passwordFieldConfirm;
    VerticalLayout verticalLayout;

    Button buttonRegister;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public RegisterGui(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
        initialize();


        buttonRegister.addClickListener(buttonClickEvent -> {
            if (!textFieldUsername.isEmpty() && !passwordField.isEmpty() && !passwordFieldConfirm.isEmpty()) {
                if (passwordField.getValue().equals(passwordFieldConfirm.getValue())) {
                    UserApp userApp = new UserApp(textFieldUsername.getValue(), passwordEncoder().encode(passwordField.getValue()), "ROLE_USER");

                    if (userService.userRegister(userApp).getStatusCodeValue() == 200) {
                        Notification notification = new Notification(
                                "Register Succesfully", 3000,
                                Notification.Position.TOP_START);
                        notification.open();
                    } else {
                        Notification notification = new Notification(
                                "Register Unsuccesfull", 3000,
                                Notification.Position.TOP_START);
                        notification.open();
                    }
                } else {
                    Notification notification = new Notification(
                            "Please make sure your passwords match", 3000,
                            Notification.Position.TOP_START);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                        "Username and Password required", 3000,
                        Notification.Position.TOP_START);
                notification.open();
            }


        });
    }

    public void initialize() {

        this.verticalLayout = new VerticalLayout();

        this.label = new Label("Registration");

        this.textFieldUsername = new TextField("Username", "Enter username");
        this.textFieldUsername.setRequired(true);

        this.passwordField = new PasswordField("Password", "Enter password");
        this.passwordField.setValue("Password");


        this.passwordFieldConfirm = new PasswordField("Reapeat Password", "Repeat password");
        this.passwordFieldConfirm.setValue("Password");

        this.buttonRegister = new Button("Register");

        Notification notification = new Notification(
                "Username and Password required", 3000,
                Notification.Position.TOP_START);
        notification.open();


        verticalLayout.add(label, textFieldUsername, passwordField, passwordFieldConfirm, buttonRegister);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setHeight("100%");
        add(verticalLayout);

    }

}
