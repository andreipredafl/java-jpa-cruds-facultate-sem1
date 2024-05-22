package com.example.it_company.frontend;

import com.example.it_company.backend.Contract;
import com.example.it_company.backend.ContractDocument;
import com.example.it_company.backend.services.ContractDocumentService;
import com.example.it_company.backend.services.ContractService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@PageTitle("Contract Documents")
@Route(value = "contract-documents", layout = MainLayout.class)
public class ContractDocumentView extends VerticalLayout {

    public ContractDocumentView(ContractDocumentService documentService, ContractService contractService) {
        GridCrud<ContractDocument> crud = new GridCrud<>(ContractDocument.class);

        crud.getGrid().setColumns("documentName", "documentPath", "documentType", "createdAt", "updatedAt");

        crud.getGrid().addColumn(document -> {
            return document.getContract() != null ? document.getContract().getContractName() : "No Contract";
        }).setHeader("Contract Name");

        // Add a custom column with a download button
        crud.getGrid().addComponentColumn(document -> {
            StreamResource resource = createStreamResource(document);
            Anchor downloadLink = new Anchor(resource, "");
            Button downloadButton = new Button("Download");
            downloadButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            downloadLink.add(downloadButton);
            downloadLink.getElement().setAttribute("download", true);
            return downloadLink;
        }).setHeader("Download");

        // Set visible properties for the form
        crud.getCrudFormFactory().setVisibleProperties("documentName", "documentPath", "documentType", "contract");

        // Customize the form factory to include a ComboBox for contracts
        crud.getCrudFormFactory().setFieldProvider("contract", field -> {
            ComboBox<Contract> comboBox = new ComboBox<>();
            comboBox.setItems(contractService.findAll());
            comboBox.setItemLabelGenerator(Contract::getContractName);
            return comboBox;
        });

        crud.setFindAllOperation(documentService::findAll);
        crud.setAddOperation(documentService::save);
        crud.setUpdateOperation(documentService::save);
        crud.setDeleteOperation(documentService::delete);

        add(crud);
    }

    private StreamResource createStreamResource(ContractDocument document) {
        try {
            Path filePath = Paths.get(document.getDocumentPath());
            if (Files.exists(filePath)) {
                return new StreamResource(filePath.getFileName().toString(), () -> {
                    try {
                        return Files.newInputStream(filePath);
                    } catch (Exception e) {
                        Notification.show("Error reading file: " + e.getMessage());
                        return null;
                    }
                });
            } else {
                Notification.show("File not found");
                return null;
            }
        } catch (Exception e) {
            Notification.show("Error creating download link: " + e.getMessage());
            return null;
        }
    }
}
