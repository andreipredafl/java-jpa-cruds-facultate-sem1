package com.example.agc.frontend;

import com.example.agc.backend.SupplierService;
import com.example.agc.backend.Supplier;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@PageTitle("Clienti")
@Route(value = "customers", layout = MainLayout.class)
public class SupplierView extends VerticalLayout {

    public SupplierView(SupplierService service) {

        var crud = new GridCrud<>(Supplier.class, service);

        String[] columns = {
                "firstName",
                "lastName",
                "email",
                "phoneNumber",
                "supplierType"
        };

        crud.getGrid().setColumns(columns);
        crud.getCrudFormFactory().setVisibleProperties(columns);

//        Grid.Column<Supplier> supplierTypeColumn = crud.getGrid().addColumn(supplier -> {
//            Icon icon = new Icon(VaadinIcon.FLAG);
//            if ("LOCAL".equals(supplier.getSupplierType())) {
//                icon.setColor("green");
//            } else if ("INTERNATIONAL".equals(supplier.getSupplierType())) {
//                icon.setColor("blue");
//            }
//            return icon;
//        }).setHeader("Supplier Type");

        add(
                new H3("Suppliers"),
                crud
        );
    }
}
