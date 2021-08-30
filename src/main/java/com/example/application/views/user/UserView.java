package com.example.application.views.user;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.TimeZone;
import com.example.application.data.entity.User;
import com.example.application.data.service.PersonService;
import com.example.application.data.service.TimeZoneService;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Route(value = "user-detail/:timeZoneID?/:action?(edit)", layout = MainView.class)
@PageTitle("user-Detail")
public class UserView extends Div  implements BeforeEnterObserver {

    private ZkService<User> zkService = new ZkService<>();

    private String userID;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        userID = event.getRouteParameters().get("timeZoneID").
                orElse("");

        Notification.show(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+userID);
    }

    public UserView(PersonService personService, TimeZoneService timeAndPickerVaadinService) {
        // crud instance
        GridCrud<Person> crud = new GridCrud<>(Person.class);

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);


        crud.getGrid().setColumns("uid","name", "privilege", "password", "group_id", "user_id");
        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.READ, "uid","name", "privilege", "password", "group_id", "user_id", "card");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "uid","name", "privilege", "password", "group_id", "user_id", "card");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "name", "privilege", "password", "group_id", "user_id", "card","timezone1","timezone2","timezone3");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.DELETE, "uid","name", "privilege", "password", "group_id", "user_id", "card");

        crud.getGrid().addThemeVariants(GridVariant.LUMO_NO_BORDER);
        crud.getCrudFormFactory().setFieldProvider("password",()->{

            PasswordField passwordField = new PasswordField();
            System.out.println(passwordField.getValue());
            return passwordField;
        });

       crud.getCrudFormFactory().setFieldProvider("timezone1",()->{
           Select<TimeZone> checkBox1 = new Select<>();

            checkBox1.setItemLabelGenerator(TimeZone::getName);
            checkBox1.setItems(timeAndPickerVaadinService.findAll());

             return checkBox1;
        });



        crud.getCrudFormFactory().setFieldProvider("timezone2",()->{
            Select<TimeZone> checkBox1 = new Select<>();

            checkBox1.setItemLabelGenerator(TimeZone::getName);
            checkBox1.setItems(timeAndPickerVaadinService.findAll());

            return checkBox1;
        });
        crud.getCrudFormFactory().setFieldProvider("timezone3",()->{
            Select<TimeZone> checkBox1 = new Select<>();

            checkBox1.setItemLabelGenerator(TimeZone::getName);
            checkBox1.setItems(timeAndPickerVaadinService.findAll());

            return checkBox1;
        });

       // crud.getCrudFormFactory().setFieldProvider("timezone1",
               // new ComboBoxProvider<>("timezone1", timeAndPickerVaadinService.findAll(), new TextRenderer<>(TimeAndPickerVaadin::getName), TimeAndPickerVaadin::getName));





        // layout configuration
      //  setSizeFull();


        add(crud);

        // logic configuration
       /* crud.setOperations(
                () -> PersonService.findByNameContainingIgnoreCase(filter.getValue()),
                person -> {
                    return PersonService.save(person);
                },
                person -> PersonService.save(person),
                person -> PersonService.delete(person)
        );*/
        crud.setCrudListener(new CrudListener<Person>() {
            @Override
            public Collection<Person> findAll() {
                return personService.findAll();
            }
            @Override
            public Person add(Person user) {

                    user.setUid(user.getUid());
                    return personService.save(user);


            }

            @Override
            public Person update(Person person) {

                try {
                    person.setUid(person.getUid());

                    User user = new User();
                    user.setId(person.getId());
                    user.setUid(person.getUid());
                    user.setName(person.getName());
                    user.setGroup_id(person.getGroup_id());
                    user.setPrivilege(person.getPrivilege());
                    user.setPassword(person.getPassword());
                    user.setCard(person.getCard());
                    user.setUser_id(person.getUser_id());
                    zkService.post("http://127.0.0.1:5000/setUsers",user);
                    Notification.show("i am here");
                    return personService.save(person);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return person;
            }

            @Override
            public void delete(Person user) {
                personService.delete(user);
            }
        });
        filter.addValueChangeListener(e -> crud.refreshGrid());
    }

    public boolean getUidUser(Person person){
        List<Person> attendances = new ArrayList<>();
        final boolean[] var = {false};
        var[0] =false;

        try {
            String data = zkService.get("http://127.0.0.1:5000/getUsers");
            ObjectMapper mapper = new ObjectMapper();
            attendances = mapper.readValue(data, new TypeReference<List<Person>>() {});

            attendances.stream().forEach(person1 -> {

                if(person1.getUid()==person.getUid())
                {
                     var[0] =true;
                }
            });


        } catch (Exception e) {
            System.out.println(e);

        }
        return var[0];
    }

}