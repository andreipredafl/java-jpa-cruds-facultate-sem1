package com.example.it_company.frontend;

import com.example.it_company.backend.Team;
import com.example.it_company.backend.TeamMember;
import com.example.it_company.backend.User;
import com.example.it_company.backend.services.TeamMemberService;
import com.example.it_company.backend.services.TeamService;
import com.example.it_company.backend.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.time.LocalDate;

@PageTitle("Team Members")
@Route(value = "team-members", layout = MainLayout.class)
public class TeamMemberView extends VerticalLayout {

    private final TeamMemberService teamMemberService;
    private final TeamService teamService;
    private final UserService userService;
    private final Grid<TeamMember> grid;
    private final ComboBox<Team> teamComboBox;
    private final ComboBox<User> userComboBox;
    private final Button addButton;

    public TeamMemberView(TeamMemberService teamMemberService, TeamService teamService, UserService userService) {
        this.teamMemberService = teamMemberService;
        this.teamService = teamService;
        this.userService = userService;

        teamComboBox = new ComboBox<>("Select Team");
        teamComboBox.setItems(teamService.findAll());
        teamComboBox.setItemLabelGenerator(Team::getName);

        userComboBox = new ComboBox<>("Select User");
        userComboBox.setItems(userService.findAll());
        userComboBox.setItemLabelGenerator(User::getName);

        addButton = new Button("Add to Team", event -> addUserToTeam());

        grid = new Grid<>(TeamMember.class);
        grid.setColumns("team.name", "user.name", "role", "joinedAt", "leftAt");

        grid.addColumn(new ComponentRenderer<>(teamMember -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE));
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            deleteButton.addClickListener(event -> {
                teamMemberService.delete(teamMember);
                Notification.show("User removed from team.");
                refreshGrid();
            });
            return deleteButton;
        })).setHeader("Actions");

        teamComboBox.addValueChangeListener(event -> refreshGrid());

        add(new HorizontalLayout(new Paragraph("Team:"), teamComboBox, new Paragraph("User:"), userComboBox, addButton), grid);
    }

    private void refreshGrid() {
        if (teamComboBox.getValue() != null) {
            grid.setItems(teamMemberService.findByTeam(teamComboBox.getValue()));
        } else {
            grid.setItems();
        }
    }

    private void addUserToTeam() {
        if (teamComboBox.getValue() == null || userComboBox.getValue() == null) {
            Notification.show("Please select both team and user.");
            return;
        }

        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(teamComboBox.getValue());
        teamMember.setUser(userComboBox.getValue());
        teamMember.setRole("member");
        teamMember.setJoinedAt(LocalDate.now());
        teamMemberService.save(teamMember);

        Notification.show("User added to team.");
        refreshGrid();
    }
}
