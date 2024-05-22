package com.example.it_company.frontend;

import com.example.it_company.backend.Project;
import com.example.it_company.backend.ProjectTask;
import com.example.it_company.backend.services.ProjectService;
import com.example.it_company.backend.services.ProjectTaskService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Project Tasks")
@Route(value = "project-tasks", layout = MainLayout.class)
public class ProjectTaskView extends VerticalLayout {

    public ProjectTaskView(ProjectTaskService taskService, ProjectService projectService) {
        GridCrud<ProjectTask> crud = new GridCrud<>(ProjectTask.class);

        crud.getGrid().setColumns("title", "description", "status", "startDate", "endDate", "createdAt", "updatedAt");

        crud.getGrid().addColumn(task -> {
            Project project = task.getProject();
            return project != null ? project.getName() : "No Project";
        }).setHeader("Project Name");

        // Set visible properties for the form
        crud.getCrudFormFactory().setVisibleProperties("title", "description", "status", "startDate", "endDate", "project");

        // Customize the form factory to include a ComboBox for projects
        crud.getCrudFormFactory().setFieldProvider("project", field -> {
            ComboBox<Project> comboBox = new ComboBox<>();
            comboBox.setItems(projectService.findAll());
            comboBox.setItemLabelGenerator(Project::getName);
            return comboBox;
        });

        crud.setFindAllOperation(taskService::findAll);
        crud.setAddOperation(taskService::save);
        crud.setUpdateOperation(taskService::save);
        crud.setDeleteOperation(taskService::delete);

        add(crud);
    }
}
