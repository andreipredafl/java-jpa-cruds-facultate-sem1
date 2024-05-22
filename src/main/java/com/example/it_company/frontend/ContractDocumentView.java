package com.example.it_company.frontend;

import com.example.it_company.backend.Contract;
import com.example.it_company.backend.ContractDocument;
import com.example.it_company.backend.services.ContractDocumentService;
import com.example.it_company.backend.services.ContractService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.io.File;
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
            Button downloadButton = new Button("Download", clickEvent -> {
                downloadDocument(document);
            });
            return downloadButton;
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

    private void downloadDocument(ContractDocument document) {
        try {
            Path filePath = Paths.get(document.getDocumentPath());
            File file = filePath.toFile();
            if (file.exists()) {
                byte[] data = Files.readAllBytes(filePath);
                // Create a temporary file and write the data to it
                Path tempFile = Files.createTempFile(file.getName(), null);
                Files.write(tempFile, data);

                // Use Vaadin's StreamResource for file download
                com.vaadin.flow.server.StreamResource resource = new com.vaadin.flow.server.StreamResource(
                        file.getName(),
                        () -> {
                            try {
                                return Files.newInputStream(tempFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        });

                com.vaadin.flow.component.html.Anchor downloadLink = new com.vaadin.flow.component.html.Anchor(resource, "");
                downloadLink.getElement().setAttribute("download", true);
                downloadLink.click();
            } else {
                com.vaadin.flow.component.notification.Notification.show("File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            com.vaadin.flow.component.notification.Notification.show("Error downloading file: " + e.getMessage());
        }
    }
}
