package com.example.it_company.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
        H1 logo = new H1("Software management firma IT");
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
        Icon clientsIcon = new Icon(VaadinIcon.USER);
        clientsIcon.setSize("16px");  // Setăm dimensiunea iconiței
        clientsIcon.getStyle().set("margin-right", "10px");
        clientsLink.addComponentAsFirst(clientsIcon);

        RouterLink contractsLink = new RouterLink("Contracte", ContractView.class);
        contractsLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon contractsIcon = new Icon(VaadinIcon.FILE_TEXT);
        contractsIcon.setSize("16px");
        contractsIcon.getStyle().set("margin-right", "10px");
        contractsLink.addComponentAsFirst(contractsIcon);

        RouterLink contractDocumentsLink = new RouterLink("Documente asociate la contracte", ContractDocumentView.class);
        contractDocumentsLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon contractDocumentsIcon = new Icon(VaadinIcon.FILE_PRESENTATION);
        contractDocumentsIcon.setSize("16px");
        contractDocumentsIcon.getStyle().set("margin-right", "10px");
        contractDocumentsLink.addComponentAsFirst(contractDocumentsIcon);

        RouterLink projectsLink = new RouterLink("Proiecte", ProjectView.class);
        projectsLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon projectsIcon = new Icon(VaadinIcon.CLIPBOARD_TEXT);
        projectsIcon.setSize("16px");
        projectsIcon.getStyle().set("margin-right", "10px");
        projectsLink.addComponentAsFirst(projectsIcon);

        RouterLink projectTasksLink = new RouterLink("Task-uri proiect", ProjectTaskView.class);
        projectTasksLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon projectTasksIcon = new Icon(VaadinIcon.TASKS);
        projectTasksIcon.setSize("16px");
        projectTasksIcon.getStyle().set("margin-right", "10px");
        projectTasksLink.addComponentAsFirst(projectTasksIcon);

        RouterLink usersLink = new RouterLink("Utilizatori", UserView.class);
        usersLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon usersIcon = new Icon(VaadinIcon.USERS);
        usersIcon.setSize("16px");
        usersIcon.getStyle().set("margin-right", "10px");
        usersLink.addComponentAsFirst(usersIcon);

        RouterLink teamsLink = new RouterLink("Echipe", TeamView.class);
        teamsLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon teamsIcon = new Icon(VaadinIcon.GROUP);
        teamsIcon.setSize("16px");
        teamsIcon.getStyle().set("margin-right", "10px");
        teamsLink.addComponentAsFirst(teamsIcon);

        RouterLink teamMembersLink = new RouterLink("Membri Echipe", TeamMemberView.class);
        teamMembersLink.setHighlightCondition(HighlightConditions.sameLocation());
        Icon teamMembersIcon = new Icon(VaadinIcon.USER_CHECK);
        teamMembersIcon.setSize("16px");
        teamMembersIcon.getStyle().set("margin-right", "10px");
        teamMembersLink.addComponentAsFirst(teamMembersIcon);

        addToDrawer(new VerticalLayout(
                clientsLink,
                contractsLink,
                contractDocumentsLink,
                projectsLink,
                projectTasksLink,
                usersLink,
                teamsLink,
                teamMembersLink
        ));
    }
}
