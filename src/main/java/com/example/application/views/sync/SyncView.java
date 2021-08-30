package com.example.application.views.sync;

import com.example.application.data.entity.Person;
import com.example.application.data.entity.User;
import com.example.application.data.service.PersonService;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.example.application.views.user.UserView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

import java.util.ArrayList;
import java.util.List;


@Route(value = "sync-detail/:timeZoneID?/:action?(edit)", layout = MainView.class)
@PageTitle("sync-Detail")
public class SyncView extends Div {

    private ZkService<Person> zkService = new ZkService<>();


    public SyncView(PersonService personService) {
        List<Person> personList = onlyInOnePlace(personService.findAll(),getListDevice());
        Grid<Person> grid = new Grid<>(Person.class);



        grid.setItems(personList);




        // Use the component constructor that accepts an item ->
        // new PersonComponent(Person person)

        // Or you can use an ordinary function to setup the component
        grid.setColumns("uid", "name");
        grid.addComponentColumn(item -> createRemoveButton(grid, item,personService))
                .setHeader("Actions");

        grid.addComponentColumn(item -> createAddButton(grid, item,personService))
                .setHeader("Actions");

        grid.addComponentColumn(item -> createAddButton1())
                .setHeader("Actions");

        grid.setSelectionMode(Grid.SelectionMode.NONE);

        add(grid);
    }

    private Button createRemoveButton(Grid<Person> grid, Person item ,PersonService personService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Remove", clickEvent -> {
            ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid
                    .getDataProvider();

            ZkService<User> zkService2 = new ZkService<>();

            try {
                item.setUid(item.getUid());

                User user = new User();
                user.setId(item.getId());
                user.setUid(item.getUid());
                user.setName(item.getName());
                user.setGroup_id(item.getGroup_id());
                user.setPrivilege(item.getPrivilege());
                user.setPassword(item.getPassword());
                user.setCard(item.getCard());
                user.setUser_id(item.getUser_id());

                zkService2.post("http://127.0.0.1:5000/deleteUser",user);
                personService.delete(item);
                dataProvider.getItems().remove(item);

            } catch (Exception e) {
                e.printStackTrace();
            }

            dataProvider.refreshAll();
        });
        return button;
    }

    private Button createAddButton1() {
        @SuppressWarnings("unchecked")
        Button button = new Button("navigation",e ->
                getUI().ifPresent(ui -> ui.navigate(
                        UserView.class,
        new RouteParameters("timeZoneID", "123")
                        ))
        );


        return button;
    }


    private Button createAddButton(Grid<Person> grid, Person item,PersonService personService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Save", clickEvent -> {
            ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) grid
                    .getDataProvider();
            ZkService<User> zkService2 = new ZkService<>();

            try {
                item.setUid(item.getUid());

                User user = new User();
                user.setId(item.getId());
                user.setUid(item.getUid());
                user.setName(item.getName());
                user.setGroup_id(item.getGroup_id());
                user.setPrivilege(item.getPrivilege());
                user.setPassword(item.getPassword());
                user.setCard(item.getCard());
                user.setUser_id(item.getUser_id());

                zkService2.post("http://127.0.0.1:5000/setUsers",user);
                personService.save(item);
                dataProvider.getItems().remove(item);

            } catch (Exception e) {
                e.printStackTrace();
            }

            dataProvider.refreshAll();
        });
        return button;
    }

    public List<Person> onlyInOnePlace(List<Person> source,List<Person> target){
        if (target.size()==0) return source;
        if (source.size()==0) return target;
        System.out.println(source.size());
        System.out.println(target.size());

        List<Person> result = new ArrayList<>();
        for (int i = 0; i < target.size(); i++) {
            result.add(target.get(i));
            for (int j = 0; j < source.size(); j++) {
                if (target.get(i).getUid()== source.get(j).getUid()) {
                    result.remove(target.get(i));

                }
            }
        }
        List<Person> result2 = new ArrayList<>();

        for (int i = 0; i < source.size(); i++) {
            result2.add(source.get(i));
            for (int j = 0; j < target.size(); j++) {
                if (source.get(i).getUid()== target.get(j).getUid()) {
                    result2.remove(source.get(i));

                }
            }
        }
        result.addAll(result2);
        return result;
    }


    public List<Person> getListDevice(){

        List<Person> attendances = new ArrayList<>();
        try {
            String data = zkService.get("http://127.0.0.1:5000/getUsers");
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(data, new TypeReference<List<User>>() {});
            users.stream().forEach(user -> {

                Person person = new Person();
                person.setId(user.getId());
                person.setUid(user.getUid());
                person.setName(user.getName());
                person.setGroup_id(user.getGroup_id());
                person.setPrivilege(user.getPrivilege());
                person.setPassword(user.getPassword());
                person.setCard(user.getCard());
                person.setUser_id(user.getUser_id());
                attendances.add(person);
            });
        }catch (Exception e){

            Notification.show("error to connect to device");

        }
        return attendances;
    }



    }

