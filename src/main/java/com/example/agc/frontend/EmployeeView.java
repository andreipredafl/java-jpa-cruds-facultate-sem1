package com.example.agc.frontend;

import com.example.agc.backend.Employee;
import com.example.agc.backend.EmployeeService;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Angajati")
@Route(value = "employees", layout = MainLayout.class)
public class EmployeeView extends VerticalLayout {


    public EmployeeView(EmployeeService service) {

        var crud = new GridCrud<>(Employee.class, service);

        String[] columns = {
                "firstName",
                "lastName",
                "age",
                "profession",
                "dateOfHiring",
                "monthlySalary"
        };

        crud.getGrid().setColumns(columns);
        crud.getCrudFormFactory().setVisibleProperties(columns);

        add(
                new H3("Employees"),
                crud
        );
    }
}
