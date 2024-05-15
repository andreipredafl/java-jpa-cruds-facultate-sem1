package com.example.agc.frontend;

import com.example.agc.backend.Employee;
import com.example.agc.backend.EmployeeService;
import com.example.agc.backend.Farm;
import com.example.agc.backend.FarmService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import com.vaadin.flow.data.binder.Binder;
import java.util.List;

@PageTitle("Farms")
@Route(value = "farms", layout = MainLayout.class)
public class FarmView extends VerticalLayout {

    private ComboBox<Employee> comboBox;
    private Binder<Farm> binder;

    public FarmView(FarmService service, EmployeeService employeeService) {
        var crud = new GridCrud<>(Farm.class, service);

        String[] columns = {
                "name",
                "location",
                "farmType",
                "owner",
                "establishedDate",
                "contactInfo",
        };

        crud.getGrid().setColumns(columns);

        crud.getGrid().addColumn(farm -> {
            Employee manager = farm.getManager();
            return manager != null ? manager.getFirstName() + " " + manager.getLastName() : "No Manager";
        }).setHeader("Manager Name");
        crud.getCrudFormFactory().setVisibleProperties(columns);

        // Initialize the ComboBox for employees
        comboBox = new ComboBox<>("Employee");
        comboBox.setItemLabelGenerator(employee -> employee.getFirstName() + " " + employee.getLastName());
        comboBox.getStyle().set("--vaadin-combo-box-overlay-width", "350px");

        // Directly use the Collection returned by getAll()
        comboBox.setItems(employeeService.getAll());

        // Initialize Binder
        binder = new Binder<>(Farm.class);
        binder.bind(comboBox, Farm::getManager, Farm::setManager);

        // Handle ComboBox value changes
        comboBox.addValueChangeListener(event -> {
            Farm selectedFarm = crud.getGrid().asSingleSelect().getValue();
            if (selectedFarm != null) {
                selectedFarm.setManager(event.getValue());
                service.update(selectedFarm);
                Notification.show("Manager updated");
            } else {
                Notification.show("Please select a farm first");
            }
        });


        // Handle ComboBox value changes
        comboBox.addValueChangeListener(event -> {
            Farm selectedFarm = crud.getGrid().asSingleSelect().getValue();
            if (selectedFarm != null) {
                selectedFarm.setManager(event.getValue());
                service.update(selectedFarm);
                Notification.show("Manager updated");
                crud.getGrid().setItems(service.findAll());
            } else {
                Notification.show("Please select a farm first");
            }
        });

        // Adding components to the layout
        add(
                new H3("Farms"),
                crud,
                comboBox
        );
    }
}
