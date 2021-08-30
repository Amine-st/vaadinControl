package com.example.application.views.device;

import com.example.application.data.entity.DeviceInfo;
import com.example.application.data.service.ZkService;
import com.example.application.views.main.MainView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.List;

@Route(value = "device", layout = MainView.class)
@PageTitle("device")
public class DeviceView extends Div {
    private ZkService<DeviceInfo> zkService = new ZkService<>();

    List<DeviceInfo> attendances = new ArrayList<>();
    public DeviceView() {

        Grid<DeviceInfo> grid = new Grid<>(DeviceInfo.class);

        try {
            String data = zkService.get("http://127.0.0.1:5000/getDeviceInfo");
            ObjectMapper mapper = new ObjectMapper();
            attendances = mapper.readValue(data, new TypeReference<List<DeviceInfo>>() {
            });

            System.out.println(attendances.get(0).getDevice_name());



        } catch (Exception e) {
            System.out.println(e);

        }

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setItems(attendances);
        grid.setColumns( "current_time", "firmware_version", "device_name", "serial_number", "mac_address", "face_version", "finger_algorithm", "platform_information", "ip", "netmask", "geteway");
        add(grid);
    }

}








