/*

package com.example.application.views.timeZone;


import com.example.application.data.entity.TimeAndPicker;
import com.example.application.data.entity.TimeAndPickerVaadin;
import com.example.application.data.entity.TimeZoneDevice;
import com.example.application.data.entity.TzItem;
import com.example.application.data.repository.TzItemRepository;
import com.example.application.data.service.TimeAndPickerVaadinService;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Route(value = "time-picker/:timeAndPickerID?/:action?(edit)", layout = MainView.class)
@PageTitle("time-picker")

public class TimePickerView extends Div implements BeforeEnterObserver {

    private final String TIMEANDPICKER_ID = "timeAndPickerID";
    private final String TIMEANDPICKER_EDIT_ROUTE_TEMPLATE = "time-picker/%d/edit";

    private Grid<TimeAndPickerVaadin> grid = new Grid<>(TimeAndPickerVaadin.class, false);

    TextField name = new TextField("Time Zone ");
    Select<Integer> jour = new Select<>();
    private TimePicker lundi;
    private TimePicker mardi;
    private TimePicker mercredi;
    private TimePicker jeudi;
    private TimePicker vendredi;
    private TimePicker samedi;
    private TimePicker dimanche;

    private TimePicker lundiE;
    private TimePicker mardiE;
    private TimePicker mercrediE;
    private TimePicker jeudiE;
    private TimePicker vendrediE;
    private TimePicker samediE;
    private TimePicker dimancheE;

    private List<TzItem> tzItem = new ArrayList<>();
    Label lundilab = new Label("Lundi");
    Label mardilab = new Label("Mardi");
    Label mercredilab = new Label("Mercredi");
    Label jeudilab = new Label("Jeudi");
    Label vendredilab = new Label("Vendredi");
    Label samedilab = new Label("Samedi");
    Label dimanchelab = new Label("Dimanche");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    Button disableOnClickButton = new Button("Update");
    Button deleteOnClickButton = new Button("Delete");

    private BeanValidationBinder<TimeAndPicker> binder;

    private TimeAndPicker timeAndPicker;
    private TimeAndPickerVaadin timeAndPickerVaadin;

    private TimeAndPickerVaadinService timeAndPickerService;

    MultiselectComboBox<TzItem> timezone = new MultiselectComboBox();
    private ZkService<TimeZoneDevice> zkService = new ZkService<>();

    @Autowired
    TzItemRepository tzItemRepository;

    public TimePickerView(@Autowired TimeAndPickerVaadinService timeAndPickerService) {
        addClassNames("timepicker-view", "flex", "flex-col", "h-full");
        this.timeAndPickerService = timeAndPickerService;
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        timezone.setLabel("Select items");
        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);
        jour.setLabel("Jour");
        jour.setItems(0, 1, 2, 3, 4, 6, 5, 6);
        // Configure Grid
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("timezone").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(timeAndPickerService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {


                populateForm(event.getValue());
                deleteOnClickButton.setEnabled(true);
                disableOnClickButton.setEnabled(true);
                save.setEnabled(false);
                UI.getCurrent().navigate(String.format(TIMEANDPICKER_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                deleteOnClickButton.setEnabled(false);
                disableOnClickButton.setEnabled(false);
                save.setEnabled(true);
                UI.getCurrent().navigate(TimePickerView.class);
            }

        });


        deleteOnClickButton.addClickListener(buttonClickEvent -> {

            timeAndPickerService.delete(this.timeAndPickerVaadin);
            refreshGrid();

        });

        disableOnClickButton.addClickListener(buttonClickEvent -> {
            try {


                if (this.timeAndPickerVaadin == null) {
                    this.timeAndPickerVaadin = new TimeAndPickerVaadin();
                }
                if (this.timeAndPicker == null) {
                    this.timeAndPicker = new TimeAndPicker();
                    binder.writeBean(this.timeAndPicker);
                    this.timeAndPickerVaadin = mapperForDb2(this.timeAndPicker);

                } else {
                    binder.writeBean(this.timeAndPicker);
                    this.timeAndPickerVaadin = mapperForDb(this.timeAndPicker);
                }

                timeAndPickerService.save(this.timeAndPickerVaadin);
                refreshGrid();

            } catch (Exception e) {
                System.out.println(e);
            }

        });
        // Configure Form
        binder = new BeanValidationBinder<>(TimeAndPicker.class);
        binder.forField(jour).bind(TimeAndPicker::getJour, TimeAndPicker::setJour);
        binder.forField(name).bind(TimeAndPicker::getName, TimeAndPicker::setName);
        binder.forField(lundi).bind(TimeAndPicker::getLundi, TimeAndPicker::setLundi);
        binder.forField(mardi).bind(TimeAndPicker::getMardi, TimeAndPicker::setMardi);
        binder.forField(mercredi).bind(TimeAndPicker::getMercredi, TimeAndPicker::setMercredi);
        binder.forField(jeudi).bind(TimeAndPicker::getJeudi, TimeAndPicker::setJeudi);
        binder.forField(vendredi).bind(TimeAndPicker::getVendredi, TimeAndPicker::setVendredi);
        binder.forField(samedi).bind(TimeAndPicker::getSamedi, TimeAndPicker::setSamedi);
        binder.forField(dimanche).bind(TimeAndPicker::getDimanche, TimeAndPicker::setDimanche);

        binder.forField(lundiE).bind(TimeAndPicker::getLundiE, TimeAndPicker::setLundiE);
        binder.forField(mardiE).bind(TimeAndPicker::getMardiE, TimeAndPicker::setMardiE);
        binder.forField(mercrediE).bind(TimeAndPicker::getMercrediE, TimeAndPicker::setMercrediE);
        binder.forField(jeudiE).bind(TimeAndPicker::getJeudiE, TimeAndPicker::setJeudiE);
        binder.forField(vendrediE).bind(TimeAndPicker::getVendrediE, TimeAndPicker::setVendrediE);
        binder.forField(samediE).bind(TimeAndPicker::getSamediE, TimeAndPicker::setSamediE);
        binder.forField(dimancheE).bind(TimeAndPicker::getDimancheE, TimeAndPicker::setDimancheE);
        // Bind fields. This where you'd define e.g. validation rules

        // binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> {
            save.setEnabled(true);
            disableOnClickButton.setEnabled(false);
            deleteOnClickButton.setEnabled(false);
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.timeAndPickerVaadin == null) {
                    this.timeAndPickerVaadin = new TimeAndPickerVaadin();
                }
                if (this.timeAndPicker == null) {
                    this.timeAndPicker = new TimeAndPicker();
                    binder.writeBean(this.timeAndPicker);
                    this.timeAndPickerVaadin = mapperForDb2(this.timeAndPicker);

                } else {
                    binder.writeBean(this.timeAndPicker);
                    this.timeAndPickerVaadin = mapperForDb(this.timeAndPicker);
                }

                try {
                    zkService.post("http://127.0.0.1:5000/setTimeZone", mapperForDevise(this.timeAndPickerVaadin));
                } catch (Exception validationException) {
                    System.out.println(validationException);
                    Notification.show("An exception happened while trying to store the timeAndPicker details.");


                }
                //

                timeAndPickerService.save(this.timeAndPickerVaadin);

                clearForm();
                refreshGrid();
                Notification.show("TimeAndPicker details stored.");
                UI.getCurrent().navigate(TimePickerView.class);
            } catch (Exception validationException) {
                System.out.println(validationException);
                Notification.show("An exception happened while trying to store the timeAndPicker details.");
            }
        });

    }

    public TimeAndPickerVaadin mapperForDb(TimeAndPicker timeAndPicker) {

        TimeAndPickerVaadin timeAndPickerVaadin = new TimeAndPickerVaadin();
        List<TzItem> tzItems = new ArrayList<>();
        timeAndPickerVaadin.setId(timeAndPicker.getId());
        timeAndPickerVaadin.setName(timeAndPicker.getName());
        TzItem item = new TzItem(timeAndPicker.getLundi().getHour(), timeAndPicker.getLundi().getMinute(), timeAndPicker.getLundiE().getHour(), timeAndPicker.getLundiE().getMinute());
        TzItem item1 = new TzItem(timeAndPicker.getMardi().getHour(), timeAndPicker.getMardi().getMinute(), timeAndPicker.getMardiE().getHour(), timeAndPicker.getMardiE().getMinute());
        TzItem item2 = new TzItem(timeAndPicker.getMercredi().getHour(), timeAndPicker.getMercredi().getMinute(), timeAndPicker.getMercrediE().getHour(), timeAndPicker.getMercrediE().getMinute());
        TzItem item3 = new TzItem(timeAndPicker.getJeudi().getHour(), timeAndPicker.getJeudi().getMinute(), timeAndPicker.getJeudiE().getHour(), timeAndPicker.getJeudiE().getMinute());
        TzItem item4 = new TzItem(timeAndPicker.getVendredi().getHour(), timeAndPicker.getVendredi().getMinute(), timeAndPicker.getVendrediE().getHour(), timeAndPicker.getVendrediE().getMinute());
        TzItem item5 = new TzItem(timeAndPicker.getSamedi().getHour(), timeAndPicker.getSamedi().getMinute(), timeAndPicker.getSamediE().getHour(), timeAndPicker.getSamediE().getMinute());
        TzItem item6 = new TzItem(timeAndPicker.getDimanche().getHour(), timeAndPicker.getDimanche().getMinute(), timeAndPicker.getDimancheE().getHour(), timeAndPicker.getDimancheE().getMinute());

        tzItems.add(item);
        tzItems.add(item1);
        tzItems.add(item2);
        tzItems.add(item3);
        tzItems.add(item4);
        tzItems.add(item5);
        tzItems.add(item6);

        timeAndPickerVaadin.setTimezone(tzItems);

        tzItemRepository.save(item);
        tzItemRepository.save(item1);
        tzItemRepository.save(item2);
        tzItemRepository.save(item3);
        tzItemRepository.save(item4);
        tzItemRepository.save(item5);
        tzItemRepository.save(item6);

        return timeAndPickerVaadin;
    }

    public TimeAndPickerVaadin mapperForDb2(TimeAndPicker timeAndPicker) {

        TimeAndPickerVaadin timeAndPickerVaadin = new TimeAndPickerVaadin();
        List<TzItem> tzItems = new ArrayList<>();
        //timeAndPickerVaadin.setId(timeAndPicker.getId());
        timeAndPickerVaadin.setName(timeAndPicker.getName());
        TzItem item = new TzItem(timeAndPicker.getLundi().getHour(), timeAndPicker.getLundi().getMinute(), timeAndPicker.getLundiE().getHour(), timeAndPicker.getLundiE().getMinute());
        TzItem item1 = new TzItem(timeAndPicker.getMardi().getHour(), timeAndPicker.getMardi().getMinute(), timeAndPicker.getMardiE().getHour(), timeAndPicker.getMardiE().getMinute());
        TzItem item2 = new TzItem(timeAndPicker.getMercredi().getHour(), timeAndPicker.getMercredi().getMinute(), timeAndPicker.getMercrediE().getHour(), timeAndPicker.getMercrediE().getMinute());
        TzItem item3 = new TzItem(timeAndPicker.getJeudi().getHour(), timeAndPicker.getJeudi().getMinute(), timeAndPicker.getJeudiE().getHour(), timeAndPicker.getJeudiE().getMinute());
        TzItem item4 = new TzItem(timeAndPicker.getVendredi().getHour(), timeAndPicker.getVendredi().getMinute(), timeAndPicker.getVendrediE().getHour(), timeAndPicker.getVendrediE().getMinute());
        TzItem item5 = new TzItem(timeAndPicker.getSamedi().getHour(), timeAndPicker.getSamedi().getMinute(), timeAndPicker.getSamediE().getHour(), timeAndPicker.getSamediE().getMinute());
        TzItem item6 = new TzItem(timeAndPicker.getDimanche().getHour(), timeAndPicker.getDimanche().getMinute(), timeAndPicker.getDimancheE().getHour(), timeAndPicker.getDimancheE().getMinute());

        tzItems.add(item);
        tzItems.add(item1);
        tzItems.add(item2);
        tzItems.add(item3);
        tzItems.add(item4);
        tzItems.add(item5);
        tzItems.add(item6);

        timeAndPickerVaadin.setTimezone(tzItems);

        tzItemRepository.save(item);
        tzItemRepository.save(item1);
        tzItemRepository.save(item2);
        tzItemRepository.save(item3);
        tzItemRepository.save(item4);
        tzItemRepository.save(item5);
        tzItemRepository.save(item6);

        return timeAndPickerVaadin;
    }

    public TimeZoneDevice mapperForDevise(TimeAndPickerVaadin timeAndPickerVaadin) {
        TimeZoneDevice timeZoneDevice = new TimeZoneDevice();
        List<List<Integer>> tzms = new ArrayList<>();
        List<TzItem> tzItems = timeAndPickerVaadin.getTimezone();
        timeZoneDevice.setId(timeAndPickerVaadin.getId());
        timeZoneDevice.setName(timeAndPickerVaadin.getName());

        tzItems.stream().forEach(t -> tzms.add(Arrays.asList(t.getHStart(), t.getMStart(), t.getHEnd(), t.getMEnd())
        ));
        timeZoneDevice.setItems(tzms);
        return timeZoneDevice;
    }


    public TimeAndPicker mapperForForm(TimeAndPickerVaadin timeAndPickerVaadin) {
        TimeAndPicker timeAndPicker = new TimeAndPicker();
        if (timeAndPickerVaadin != null) {
            if (!timeAndPickerVaadin.getTimezone().isEmpty()) {
                String name = timeAndPickerVaadin.getName();
                LocalTime lundi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(0).getHStart(), timeAndPickerVaadin.getTimezone().get(0).getMStart(), 0);
                LocalTime mardi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(1).getHStart(), timeAndPickerVaadin.getTimezone().get(1).getMStart(), 0);
                LocalTime mercredi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(2).getHStart(), timeAndPickerVaadin.getTimezone().get(2).getMStart(), 0);
                LocalTime jeudi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(3).getHStart(), timeAndPickerVaadin.getTimezone().get(3).getMStart(), 0);
                LocalTime vendredi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(4).getHStart(), timeAndPickerVaadin.getTimezone().get(4).getMStart(), 0);
                LocalTime samedi = LocalTime.of(timeAndPickerVaadin.getTimezone().get(5).getHStart(), timeAndPickerVaadin.getTimezone().get(5).getMStart(), 0);
                LocalTime dimanche = LocalTime.of(timeAndPickerVaadin.getTimezone().get(6).getHStart(), timeAndPickerVaadin.getTimezone().get(6).getMStart(), 0);

                LocalTime lundiE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(0).getHEnd(), timeAndPickerVaadin.getTimezone().get(0).getMEnd(), 0);
                LocalTime mardiE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(1).getHEnd(), timeAndPickerVaadin.getTimezone().get(1).getMEnd(), 0);
                LocalTime mercrediE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(2).getHEnd(), timeAndPickerVaadin.getTimezone().get(2).getMEnd(), 0);
                LocalTime jeudiE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(3).getHEnd(), timeAndPickerVaadin.getTimezone().get(3).getMEnd(), 0);
                LocalTime vendrediE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(4).getHEnd(), timeAndPickerVaadin.getTimezone().get(4).getMEnd(), 0);
                LocalTime samediE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(5).getHEnd(), timeAndPickerVaadin.getTimezone().get(5).getMEnd(), 0);
                LocalTime dimancheE = LocalTime.of(timeAndPickerVaadin.getTimezone().get(6).getHEnd(), timeAndPickerVaadin.getTimezone().get(6).getMEnd(), 0);

                timeAndPicker.setId(timeAndPickerVaadin.getId());
                timeAndPicker.setJour(10);
                timeAndPicker.setName(name);
                timeAndPicker.setLundi(lundi);
                timeAndPicker.setMardi(mardi);
                timeAndPicker.setMercredi(mercredi);
                timeAndPicker.setJeudi(jeudi);
                timeAndPicker.setVendredi(vendredi);
                timeAndPicker.setSamedi(samedi);
                timeAndPicker.setDimanche(dimanche);
                timeAndPicker.setLundiE(lundiE);
                timeAndPicker.setMardiE(mardiE);
                timeAndPicker.setMercrediE(mercrediE);
                timeAndPicker.setJeudiE(jeudiE);
                timeAndPicker.setVendrediE(vendrediE);
                timeAndPicker.setSamediE(samediE);
                timeAndPicker.setDimancheE(dimancheE);

                return timeAndPicker;
            }
        }
        return null;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> timeAndPickerId = event.getRouteParameters().getInteger(TIMEANDPICKER_ID);
        if (timeAndPickerId.isPresent()) {
            Optional<TimeAndPickerVaadin> timeAndPickerFromBackend = timeAndPickerService.get(timeAndPickerId.get());
            if (timeAndPickerFromBackend.isPresent()) {
                populateForm(timeAndPickerFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested timeAndPicker was not found, ID = %d", timeAndPickerId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(TimePickerView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("40em", 1),
                new FormLayout.ResponsiveStep("40em", 2),
                new FormLayout.ResponsiveStep("40em", 3));


        lundi = new TimePicker("From");
        lundi.setStep(Duration.ofMinutes(30));
        lundi.setValue(LocalTime.of(12, 30));

        mardi = new TimePicker("From");
        mardi.setStep(Duration.ofMinutes(30));
        mardi.setValue(LocalTime.of(12, 30));

        mercredi = new TimePicker("From");
        mercredi.setStep(Duration.ofMinutes(30));
        mercredi.setValue(LocalTime.of(12, 30));

        jeudi = new TimePicker("From");
        jeudi.setStep(Duration.ofMinutes(30));
        jeudi.setValue(LocalTime.of(12, 30));

        vendredi = new TimePicker("From");
        vendredi.setStep(Duration.ofMinutes(30));
        vendredi.setValue(LocalTime.of(12, 30));

        samedi = new TimePicker("From");
        samedi.setStep(Duration.ofMinutes(30));
        samedi.setValue(LocalTime.of(12, 30));


        dimanche = new TimePicker("From");
        dimanche.setStep(Duration.ofMinutes(30));
        dimanche.setValue(LocalTime.of(12, 30));


        lundiE = new TimePicker("To");
        lundiE.setStep(Duration.ofMinutes(30));
        lundiE.setValue(LocalTime.of(12, 30));

        mardiE = new TimePicker("To");
        mardiE.setStep(Duration.ofMinutes(30));
        mardiE.setValue(LocalTime.of(12, 30));

        mercrediE = new TimePicker("To");
        mercrediE.setStep(Duration.ofMinutes(30));
        mercrediE.setValue(LocalTime.of(12, 30));

        jeudiE = new TimePicker("To");
        jeudiE.setStep(Duration.ofMinutes(30));
        jeudiE.setValue(LocalTime.of(12, 30));

        vendrediE = new TimePicker("To");
        vendrediE.setStep(Duration.ofMinutes(30));
        vendrediE.setValue(LocalTime.of(12, 30));

        samediE = new TimePicker("To");
        samediE.setStep(Duration.ofMinutes(30));
        samediE.setValue(LocalTime.of(12, 30));


        dimancheE = new TimePicker("To");
        dimancheE.setStep(Duration.ofMinutes(30));
        dimancheE.setValue(LocalTime.of(12, 30));


        Component[] fields = new Component[]{
                lundilab, lundi, lundiE,
                mardilab, mardi, mardiE,
                mercredilab, mercredi, mercrediE,
                jeudilab, jeudi, jeudiE,
                vendredilab, vendredi, vendrediE,
                samedilab, samedi, samediE,
                dimanchelab, dimanche, dimancheE};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(jour);
        formLayout.add(name, 2);

        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        disableOnClickButton.setEnabled(false);
        deleteOnClickButton.setEnabled(false);

        buttonLayout.add(save, disableOnClickButton, deleteOnClickButton, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(TimeAndPickerVaadin value) {
        this.timeAndPicker = mapperForForm(value);
        this.timeAndPickerVaadin = value;
        binder.readBean(this.timeAndPicker);

    }
}
*/
