package com.example.it_company.frontend;

import com.example.it_company.backend.Client;
import com.example.it_company.backend.services.ClientService;
import com.example.it_company.backend.Contract;
import com.example.it_company.backend.ContractDocument;
import com.example.it_company.backend.services.ContractDocumentService;
import com.example.it_company.backend.services.ContractService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@PageTitle("Contracts")
@Route(value = "contracts", layout = MainLayout.class)
public class ContractView extends VerticalLayout {

    private final ContractDocumentService documentService;

    public ContractView(ContractService contractService, ClientService clientService, ContractDocumentService documentService) {
        this.documentService = documentService;

        GridCrud<Contract> crud = new GridCrud<>(Contract.class);

        crud.getGrid().setColumns("contractName", "series", "number", "periodMonths", "startDate", "endDate", "status", "description", "createdAt", "updatedAt");

        crud.getGrid().addColumn(contract -> {
            Client client = contract.getClient();
            return client != null ? client.getCompanyName() + " - " + client.getContactName() : "No Client";
        }).setHeader("Client Name and Contact");

        crud.getCrudFormFactory().setVisibleProperties("contractName", "series", "number", "periodMonths", "startDate", "endDate", "status", "description", "client");

        crud.getCrudFormFactory().setFieldProvider("client", field -> {
            ComboBox<Client> comboBox = new ComboBox<>();
            comboBox.setItems(clientService.findAll());
            comboBox.setItemLabelGenerator(client -> client.getCompanyName() + " " + client.getContactName());
            return comboBox;
        });

        crud.setFindAllOperation(contractService::findAll);
        crud.setAddOperation(contractService::save);
        crud.setUpdateOperation(contractService::save);
        crud.setDeleteOperation(contractService::delete);

        add(crud);

        // Section for Contract Documents
        Grid<ContractDocument> documentGrid = new Grid<>(ContractDocument.class);
        documentGrid.setColumns("documentName", "documentPath", "documentType", "createdAt", "updatedAt");

        documentGrid.addColumn(document -> {
            return document.getContract() != null ? document.getContract().getContractName() : "No Contract";
        }).setHeader("Contract Name");

        documentGrid.setItems(documentService.findAll());

        Button addDocumentButton = new Button("Add Document", e -> {
            Contract selectedContract = crud.getGrid().asSingleSelect().getValue();
            if (selectedContract != null) {
                showUploadDialog(selectedContract);
            } else {
                Notification.show("Please select a contract first");
            }
        });

        add(new HorizontalLayout(documentGrid, addDocumentButton));
    }

    private void showUploadDialog(Contract selectedContract) {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf");

        VerticalLayout uploadLayout = new VerticalLayout(upload);
        upload.addSucceededListener(event -> {
            InputStream inputStream = buffer.getInputStream(event.getFileName());
            String filePath = saveFile(inputStream, event.getFileName());
            if (filePath != null) {
                ContractDocument document = new ContractDocument();
                document.setContract(selectedContract);
                document.setDocumentName(event.getFileName());
                document.setDocumentPath(filePath);
                document.setDocumentType("contract");
                document.setCreatedAt(LocalDate.now());
                document.setUpdatedAt(LocalDate.now());
                documentService.save(document);
                Notification.show("Document uploaded successfully");
            }
        });

        add(uploadLayout);
    }

    private String saveFile(InputStream inputStream, String fileName) {
        try {
            Path projectPath = Paths.get(System.getProperty("user.dir"));
            System.out.println("path:" + projectPath);

            Path uploadDir = projectPath.resolve("document_files");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Path filePath = uploadDir.resolve(fileName);
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                return filePath.toString();
            }
        } catch (IOException e) {
            Notification.show("Failed to save file: " + e.getMessage());
            return null;
        }
    }
}
