package com.example.it_company.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Agriculture Management Softwarwe");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {

        RouterLink clientsLink = new RouterLink("Clienti", ClientView.class);
        clientsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink contractsLink = new RouterLink("Contracte", ContractView.class);
        contractsLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink contractSocumentsLink = new RouterLink("Documente asociate la contracte", ContractDocumentView.class);
        contractSocumentsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
            clientsLink,
            contractsLink,
            contractSocumentsLink
        ));
    }
}
