package com.example.it_company.frontend;

import com.example.it_company.backend.User;
import com.example.it_company.backend.services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Users")
@Route(value = "users", layout = MainLayout.class)
public class UserView extends VerticalLayout {

    public UserView(UserService userService) {
        GridCrud<User> crud = new GridCrud<>(User.class);

        crud.getGrid().setColumns("name", "email", "role", "createdAt", "updatedAt");

        // Set visible properties for the form
        crud.getCrudFormFactory().setVisibleProperties("name", "email", "password", "role");

        crud.setFindAllOperation(userService::findAll);
        crud.setAddOperation(userService::save);
        crud.setUpdateOperation(userService::save);
        crud.setDeleteOperation(userService::delete);

        add(crud);
    }
}
