package com.example.application.views.deviceConnection;


import com.example.application.data.entity.Connection;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "device-connection", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("device-connection")
public class DeviceConnectionView extends Div {

    Select<String> adresse = new Select<>();
    Select<String> port = new Select<>();
    private ZkService<Connection> zkService = new ZkService<>();
    FormLayout formLayout = new FormLayout();


    private Button cancel = new Button("reset");
    private Button save = new Button("connecter");

    private Binder<Object> binder = new Binder(Object.class);

    public DeviceConnectionView() {
        addClassName("person-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        adresse.setItems("192.168.100.201", "192.168.100.202");
        port.setItems("Protocol UDP", "Protocol TCP");
        adresse.setLabel("Address Ip");
        port.setLabel("Protocol");
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {

            try {

                System.out.println(adresse.getValue());
                String url = "http://127.0.0.1:5000/";
                url += adresse.getValue() + "/";
                if (port.getValue().compareTo("Protocol UDP") == 0) {
                    url += "True";
                } else {
                    url += "False";
                }
                zkService.get(url);

                Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
                clearForm();
            } catch (Exception exception) {

                System.out.println(exception);
            }


        });
    }

    private void clearForm() {
        adresse.clear();
        port.clear();
    }

    private Component createTitle() {
        return new H3("connect to  Device MA300");
    }

    private Component createFormLayout() {

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("1px", 1));
        formLayout.add(adresse, port);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }


}