package com.example.it_company;

import com.example.it_company.backend.*;
import com.example.it_company.backend.repositories.ClientRepository;
import com.example.it_company.backend.repositories.ContractDocumentRepository;
import com.example.it_company.backend.repositories.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@SpringBootApplication
@Configuration
public class ItCompanyApplication {

	private static final Logger log = LoggerFactory.getLogger(ItCompanyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ItCompanyApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(
			ClientRepository clientRepository,
			ContractRepository contractRepository,
			ContractDocumentRepository contractDocumentRepository
	) {
		return (args) -> {
			// Clean the database
			contractDocumentRepository.deleteAll();
			contractRepository.deleteAll();
			clientRepository.deleteAll();

			// Add initial data for Clients
			Client client1 = new Client();
			client1.setCompanyName("Inovatori Tech");
			client1.setContactName("Alice Popescu");
			client1.setContactEmail("alice@inovatoritech.com");
			client1.setPhoneNumber("0723456789");
			client1.setAddress("Strada Tech 123, Bucuresti");
			client1.setCreatedAt(LocalDate.now());
			client1.setUpdatedAt(LocalDate.now());
			clientRepository.save(client1);

			Client client2 = new Client();
			client2.setCompanyName("Solutii IT");
			client2.setContactName("Bogdan Ionescu");
			client2.setContactEmail("bogdan@solutiiit.com");
			client2.setPhoneNumber("0729876543");
			client2.setAddress("Calea IT 45, Cluj-Napoca");
			client2.setCreatedAt(LocalDate.now());
			client2.setUpdatedAt(LocalDate.now());
			clientRepository.save(client2);

			Client client3 = new Client();
			client3.setCompanyName("Dezvoltare Software SRL");
			client3.setContactName("Carmen Vasilescu");
			client3.setContactEmail("carmen@dezvoltare.com");
			client3.setPhoneNumber("0731234567");
			client3.setAddress("Bulevardul Software 78, Timisoara");
			client3.setCreatedAt(LocalDate.now());
			client3.setUpdatedAt(LocalDate.now());
			clientRepository.save(client3);

			// Add initial data for Contracts
			Contract contract1 = new Contract();
			contract1.setClient(client1);
			contract1.setContractName("Contract Dezvoltare");
			contract1.setSeries("DEV");
			contract1.setNumber("001");
			contract1.setPeriodMonths(12);
			contract1.setStartDate(LocalDate.now());
			contract1.setEndDate(LocalDate.now().plusMonths(12));
			contract1.setStatus("activ");
			contract1.setDescription("Contract pentru servicii de dezvoltare software.");
			contract1.setCreatedAt(LocalDate.now());
			contract1.setUpdatedAt(LocalDate.now());
			contractRepository.save(contract1);

			Contract contract2 = new Contract();
			contract2.setClient(client2);
			contract2.setContractName("Contract Mentenanta");
			contract2.setSeries("MNT");
			contract2.setNumber("002");
			contract2.setPeriodMonths(6);
			contract2.setStartDate(LocalDate.now());
			contract2.setEndDate(LocalDate.now().plusMonths(6));
			contract2.setStatus("activ");
			contract2.setDescription("Contract pentru servicii de mentenanta IT.");
			contract2.setCreatedAt(LocalDate.now());
			contract2.setUpdatedAt(LocalDate.now());
			contractRepository.save(contract2);

			Contract contract3 = new Contract();
			contract3.setClient(client3);
			contract3.setContractName("Contract Consultanta");
			contract3.setSeries("CNS");
			contract3.setNumber("003");
			contract3.setPeriodMonths(3);
			contract3.setStartDate(LocalDate.now());
			contract3.setEndDate(LocalDate.now().plusMonths(3));
			contract3.setStatus("activ");
			contract3.setDescription("Contract pentru servicii de consultanta IT.");
			contract3.setCreatedAt(LocalDate.now());
			contract3.setUpdatedAt(LocalDate.now());
			contractRepository.save(contract3);

			// Add initial data for Contract Documents
			ContractDocument document1 = new ContractDocument();
			document1.setContract(contract1);
			document1.setDocumentName("Contract Document 1");
			document1.setDocumentPath("/path/to/document1.pdf");
			document1.setDocumentType("contract");
			document1.setCreatedAt(LocalDate.now());
			document1.setUpdatedAt(LocalDate.now());
			contractDocumentRepository.save(document1);

			ContractDocument document2 = new ContractDocument();
			document2.setContract(contract2);
			document2.setDocumentName("Contract Document 2");
			document2.setDocumentPath("/path/to/document2.pdf");
			document2.setDocumentType("contract");
			document2.setCreatedAt(LocalDate.now());
			document2.setUpdatedAt(LocalDate.now());
			contractDocumentRepository.save(document2);

			ContractDocument document3 = new ContractDocument();
			document3.setContract(contract3);
			document3.setDocumentName("Contract Document 3");
			document3.setDocumentPath("/path/to/document3.pdf");
			document3.setDocumentType("contract");
			document3.setCreatedAt(LocalDate.now());
			document3.setUpdatedAt(LocalDate.now());
			contractDocumentRepository.save(document3);
		};
	}
}
