package com.example.application.views.test;

import com.example.application.data.entity.Test;
import com.example.application.data.service.TestService;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;


@Route(value = "test/:testID?/:action?(edit)", layout = MainView.class)
@PageTitle("test")
public class TestView extends Div  {


    public TestView(TestService testRepository) {
        // crud instance
        GridCrud<Test> crud = new GridCrud<>(Test.class);

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        // grid configuration
        crud.getGrid().setColumns("label", "name");
        crud.getGrid().setColumnReorderingAllowed(true);
        // form configuration
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.READ, "label", "name");

        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "label", "name");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "label", "name");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.DELETE, "label", "name");
        crud.getGrid().addThemeVariants(GridVariant.LUMO_NO_BORDER);

        crud.setFindAllOperation(() -> testRepository.findAll());
        crud.setAddOperation(testRepository::save);
        crud.setUpdateOperation(testRepository::update);
        crud.setDeleteOperation(testRepository::delete);

        add(crud);


    }
}