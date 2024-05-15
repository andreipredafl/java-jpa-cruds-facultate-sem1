package com.example.agc;

import com.example.agc.backend.*;
import com.example.agc.frontend.FarmView;
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
public class AgcApplication {

	private static final Logger log = LoggerFactory.getLogger(AgcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AgcApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(
			SupplierRepository supplierRepository,
			FarmRepository farmRepository,
			EmployeeRepository employeeRepository
	) {
		return (args) -> {
			LocalSupplier supplier1 = new LocalSupplier();
			supplier1.setFirstName("AGRICOVER");
			supplier1.setLastName("CREDIT SA");
			supplier1.setEmail("agricover@aderp.ro");
			supplier1.setPhoneNumber("07452215376");
			supplierRepository.save(supplier1);

			InternationalSupplier supplier2 = new InternationalSupplier();
			supplier2.setFirstName("INGRASAMINTE");
			supplier2.setLastName("CHIMICE SRL");
			supplier2.setEmail("ingrasaminte@aderp.ro");
			supplier2.setPhoneNumber("074362343");
			supplierRepository.save(supplier2);

			InternationalSupplier supplier3 = new InternationalSupplier();
			supplier3.setFirstName("AGRICOL");
			supplier3.setLastName("SERVICES SRL");
			supplier3.setEmail("agricola@aderp.ro");
			supplier3.setPhoneNumber("034215378");
			supplierRepository.save(supplier3);

			Employee employee1 = new Employee(1L, "Angajat", "1", 20, "sofer tractor", LocalDate.now(), 20000);
			employeeRepository.save(employee1);

			Employee employee2 = new Employee(2L, "Angajat", "2", 25, "manager", LocalDate.now(), 22000);
			employeeRepository.save(employee2);

			Farm farm = new Farm(1L, "Ferma 1", "Iasi", 860.0, "agricultura", "Andrei Preda", LocalDate.now(), employee2, "");
			farmRepository.save(farm);
		};
	}
}
