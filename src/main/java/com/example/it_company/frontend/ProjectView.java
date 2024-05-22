package com.example.it_company.frontend;

import com.example.it_company.backend.Client;
import com.example.it_company.backend.Project;
import com.example.it_company.backend.services.ClientService;
import com.example.it_company.backend.services.ProjectService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Projects")
@Route(value = "projects", layout = MainLayout.class)
public class ProjectView extends VerticalLayout {

    public ProjectView(ProjectService projectService, ClientService clientService) {
        GridCrud<Project> crud = new GridCrud<>(Project.class);

        crud.getGrid().setColumns("name", "description", "startDate", "endDate", "status", "createdAt", "updatedAt");

        crud.getGrid().addColumn(project -> {
            Client client = project.getClient();
            return client != null ? client.getCompanyName() + " - " + client.getContactName() : "No Client";
        }).setHeader("Client Name and Contact");

        // Set visible properties for the form
        crud.getCrudFormFactory().setVisibleProperties("name", "description", "startDate", "endDate", "status", "client");

        // Customize the form factory to include a ComboBox for clients
        crud.getCrudFormFactory().setFieldProvider("client", field -> {
            ComboBox<Client> comboBox = new ComboBox<>();
            comboBox.setItems(clientService.findAll());
            comboBox.setItemLabelGenerator(client -> client.getCompanyName() + " " + client.getContactName());
            return comboBox;
        });

        crud.setFindAllOperation(projectService::findAll);
        crud.setAddOperation(projectService::save);
        crud.setUpdateOperation(projectService::save);
        crud.setDeleteOperation(projectService::delete);

        add(crud);
    }
}
