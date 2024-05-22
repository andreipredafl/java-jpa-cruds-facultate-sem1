package com.example.it_company.frontend;

import com.example.it_company.backend.Team;
import com.example.it_company.backend.services.TeamService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Teams")
@Route(value = "teams", layout = MainLayout.class)
public class TeamView extends VerticalLayout {

    private final TeamService teamService;
    private final Grid<Team> grid;
    private final ComboBox<Team> teamComboBox;
    private final Span nameLabel;
    private final Span descriptionLabel;

    public TeamView(TeamService teamService) {
        this.teamService = teamService;

        teamComboBox = new ComboBox<>("Select Team");
        teamComboBox.setItems(teamService.findAll());
        teamComboBox.setItemLabelGenerator(Team::getName);

        nameLabel = new Span("Name:");
        descriptionLabel = new Span("Description:");

        grid = new Grid<>(Team.class);
        grid.setColumns("name", "description", "createdAt", "updatedAt");

        add(new HorizontalLayout(nameLabel, descriptionLabel, teamComboBox), grid);
        refreshGrid();
    }

    private void refreshGrid() {
        grid.setItems(teamService.findAll());
    }
}