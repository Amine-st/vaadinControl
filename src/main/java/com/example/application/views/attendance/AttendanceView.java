package com.example.application.views.attendance;

import com.example.application.data.entity.Attendance;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "attendance", layout = MainView.class)
@PageTitle("attendance")
public class AttendanceView extends Div {
    private ZkService<Attendance> zkService = new ZkService<>();

    public AttendanceView() {

        List<Attendance> attendances = new ArrayList<>();
        Grid<Attendance> grid = new Grid<>(Attendance.class);

        try {
            String data = zkService.get("http://127.0.0.1:5000/getAttendance");
            ObjectMapper mapper = new ObjectMapper();
            attendances = mapper.readValue(data, new TypeReference<List<Attendance>>() {
            });


        } catch (Exception e) {
            System.out.println(e);

        }

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setItems(attendances);
        grid.setColumns("uid", "user_id", "timestamp", "status", "punch");
        add(grid);
    }

}








