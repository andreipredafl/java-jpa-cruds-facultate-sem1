package com.example.it_company.frontend;

import com.example.it_company.backend.Client;
import com.example.it_company.backend.services.ClientService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Clients")
@Route(value = "clients", layout = MainLayout.class)
public class ClientView extends VerticalLayout {

    public ClientView(ClientService service) {
        GridCrud<Client> crud = new GridCrud<>(Client.class);

        crud.getGrid().setColumns("companyName", "contactName", "contactEmail", "phoneNumber", "address", "createdAt");
        crud.getCrudFormFactory().setVisibleProperties("companyName", "contactName", "contactEmail", "phoneNumber", "address", "createdAt");

        crud.setFindAllOperation(service::findAll);
        crud.setAddOperation(service::save);
        crud.setUpdateOperation(service::save);
        crud.setDeleteOperation(service::delete);

        add(crud);
    }
}
