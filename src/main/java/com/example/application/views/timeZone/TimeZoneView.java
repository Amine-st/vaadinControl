package com.example.application.views.timeZone;

import com.example.application.data.entity.*;
import com.example.application.data.repository.TzItemRepository;
import com.example.application.data.service.TimeZoneService;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Route(value = "time-detail/:timeZoneID?/:action?(edit)", layout = MainView.class)
@PageTitle("time-Detail")
public class TimeZoneView extends Div {

    private ZkService<Person> zkService = new ZkService<>();


    public TimeZoneView(TimeZoneService timeZoneService , TzItemRepository tzItemRepository) {
        // crud instance
        GridCrud<TimeZone> crud = new GridCrud<>(TimeZone.class);

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        // grid configuration
        crud.getGrid().setColumns("name", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche");
        crud.getGrid().setColumnReorderingAllowed(true);
        // form configuration
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.READ, "name","lundiTime", "lundiETime","mardiTime","mardiETime", "mercrediTime", "mercrediETime","jeudiTime", "jeudiETime","vendrediTime", "vendrediETime","samediTime", "samediETime", "dimancheTime", "dimancheETime");

        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "name","lundiTime", "lundiETime","mardiTime","mardiETime", "mercrediTime", "mercrediETime","jeudiTime", "jeudiETime","vendrediTime", "vendrediETime","samediTime", "samediETime", "dimancheTime", "dimancheETime");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "name","lundiTime", "lundiETime",
                "mardiTime","mardiETime", "mercrediTime", "mercrediETime","jeudiTime", "jeudiETime","vendrediTime", "vendrediETime","samediTime", "samediETime", "dimancheTime", "dimancheETime");
        crud.getGrid().addThemeVariants(GridVariant.LUMO_NO_BORDER);


        crud.getCrudFormFactory().setFieldProvider("lundiTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("lundiETime",() -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("mardiTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("mardiETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("mercrediTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("mercrediETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("jeudiTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("jeudiETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("vendrediTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("vendrediETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("samediTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });

        crud.getCrudFormFactory().setFieldProvider("samediETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });
        crud.getCrudFormFactory().setFieldProvider("dimancheTime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });
        crud.getCrudFormFactory().setFieldProvider("dimancheETime", () -> {
            TimePicker timePicker = new TimePicker();
            return timePicker;
        });




        setSizeFull();
        add(crud);

        // logic configuration

        crud.setCrudListener(new CrudListener<TimeZone>() {
            @Override
            public Collection<TimeZone> findAll() {
                return timeZoneService.findAll();
            }

            @Override
            public TimeZone add(TimeZone user) {
                List<TzItem> tzItems = new ArrayList<>();
                TzItem item = new TzItem(user.getLundiTime().getHour(), user.getLundiTime().getMinute(), user.getLundiETime().getHour(), user.getLundiETime().getMinute());
                TzItem item1 = new TzItem(user.getMardiTime().getHour(), user.getMardiTime().getMinute(), user.getMardiETime().getHour(), user.getMardiETime().getMinute());
                TzItem item2 = new TzItem(user.getMercrediTime().getHour(), user.getMercrediTime().getMinute(), user.getMercrediETime().getHour(), user.getMercrediETime().getMinute());
                TzItem item3 = new TzItem(user.getJeudiTime().getHour(), user.getJeudiTime().getMinute(), user.getJeudiETime().getHour(), user.getJeudiETime().getMinute());
                TzItem item4 = new TzItem(user.getVendrediTime().getHour(), user.getVendrediTime().getMinute(), user.getVendrediETime().getHour(), user.getVendrediETime().getMinute());
                TzItem item5 = new TzItem(user.getSamediTime().getHour(), user.getSamediTime().getMinute(), user.getSamediETime().getHour(), user.getSamediETime().getMinute());
                TzItem item6 = new TzItem(user.getDimancheTime().getHour(), user.getDimancheTime().getMinute(), user.getDimancheETime().getHour(), user.getDimancheETime().getMinute());
                tzItems.add(item);
                tzItems.add(item1);
                tzItems.add(item2);
                tzItems.add(item3);
                tzItems.add(item4);
                tzItems.add(item5);
                tzItems.add(item6);


                user.setTimezone(tzItems);
                tzItemRepository.save(item);
                tzItemRepository.save(item1);
                tzItemRepository.save(item2);
                tzItemRepository.save(item3);
                tzItemRepository.save(item4);
                tzItemRepository.save(item5);
                tzItemRepository.save(item6);

                user.setLundi("From :"+user.getLundiTime()+" To :"+user.getLundiETime());
                user.setMardi("From :"+user.getMardiTime()+" To :"+user.getMardiETime());
                user.setMercredi("From :"+user.getMercrediTime()+" To :"+user.getMercrediETime());
                user.setJeudi("From :"+user.getJeudiTime()+" To :"+user.getJeudiETime());
                user.setVendredi("From :"+user.getVendrediTime()+" To :"+user.getVendrediETime());
                user.setSamedi("From :"+user.getSamediTime()+" To :"+user.getSamediETime());
                user.setDimanche("From :"+user.getDimancheTime()+" To :"+user.getDimancheETime());


                return timeZoneService.save(user);
            }

            @Override
            public TimeZone update(TimeZone user) {

                List<TzItem> tzItems = new ArrayList<>();
                TzItem item = new TzItem(user.getLundiTime().getHour(), user.getLundiTime().getMinute(), user.getLundiETime().getHour(), user.getLundiETime().getMinute());
                TzItem item1 = new TzItem(user.getMardiTime().getHour(), user.getMardiTime().getMinute(), user.getMardiETime().getHour(), user.getMardiETime().getMinute());
                TzItem item2 = new TzItem(user.getMercrediTime().getHour(), user.getMercrediTime().getMinute(), user.getMercrediETime().getHour(), user.getMercrediETime().getMinute());
                TzItem item3 = new TzItem(user.getJeudiTime().getHour(), user.getJeudiTime().getMinute(), user.getJeudiETime().getHour(), user.getJeudiETime().getMinute());
                TzItem item4 = new TzItem(user.getVendrediTime().getHour(), user.getVendrediTime().getMinute(), user.getVendrediETime().getHour(), user.getVendrediETime().getMinute());
                TzItem item5 = new TzItem(user.getSamediTime().getHour(), user.getSamediTime().getMinute(), user.getSamediETime().getHour(), user.getSamediETime().getMinute());
                TzItem item6 = new TzItem(user.getDimancheTime().getHour(), user.getDimancheTime().getMinute(), user.getDimancheETime().getHour(), user.getDimancheETime().getMinute());
                tzItems.add(item);
                tzItems.add(item1);
                tzItems.add(item2);
                tzItems.add(item3);
                tzItems.add(item4);
                tzItems.add(item5);
                tzItems.add(item6);

                user.setTimezone(tzItems);
                tzItemRepository.save(item);
                tzItemRepository.save(item1);
                tzItemRepository.save(item2);
                tzItemRepository.save(item3);
                tzItemRepository.save(item4);
                tzItemRepository.save(item5);
                tzItemRepository.save(item6);

                user.setLundi("From :"+user.getLundiTime()+" To :"+user.getLundiETime());
                user.setMardi("From :"+user.getMardiTime()+" To :"+user.getMardiETime());
                user.setMercredi("From :"+user.getMercrediTime()+" To :"+user.getMercrediETime());
                user.setJeudi("From :"+user.getJeudiTime()+" To :"+user.getJeudiETime());
                user.setVendredi("From :"+user.getVendrediTime()+" To :"+user.getVendrediETime());
                user.setSamedi("From :"+user.getSamediTime()+" To :"+user.getSamediETime());
                user.setDimanche("From :"+user.getDimancheTime()+" To :"+user.getDimancheETime());

                return timeZoneService.save(user);
            }

            @Override
            public void delete(TimeZone user) {
                user.getTimezone().stream().forEach(tzItem -> tzItemRepository.delete(tzItem));
                timeZoneService.delete(user);
            }
        });
        filter.addValueChangeListener(e -> crud.refreshGrid());
    }


}