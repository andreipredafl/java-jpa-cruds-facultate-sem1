package com.example.it_company;

import com.example.it_company.backend.*;
import com.example.it_company.backend.repositories.*;
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
			ContractDocumentRepository contractDocumentRepository,
			ProjectRepository projectRepository,
			ProjectTaskRepository projectTaskRepository,
			UserRepository userRepository,
			TeamRepository teamRepository,
			TeamMemberRepository teamMemberRepository,
			ProjectTeamRepository projectTeamRepository
	) {
		return (args) -> {
			// Clean the database
			projectTeamRepository.deleteAll();
			teamMemberRepository.deleteAll();
			teamRepository.deleteAll();
			userRepository.deleteAll();
			projectTaskRepository.deleteAll();
			projectRepository.deleteAll();
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

			// Add initial data for Users
			User user1 = new User();
			user1.setName("John Doe");
			user1.setEmail("john.doe@example.com");
			user1.setPassword("password");
			user1.setRole("developer");
			user1.setCreatedAt(LocalDate.now());
			user1.setUpdatedAt(LocalDate.now());
			userRepository.save(user1);

			User user2 = new User();
			user2.setName("Jane Smith");
			user2.setEmail("jane.smith@example.com");
			user2.setPassword("password");
			user2.setRole("project_manager");
			user2.setCreatedAt(LocalDate.now());
			user2.setUpdatedAt(LocalDate.now());
			userRepository.save(user2);

			User user3 = new User();
			user3.setName("Mike Johnson");
			user3.setEmail("mike.johnson@example.com");
			user3.setPassword("password");
			user3.setRole("client");
			user3.setCreatedAt(LocalDate.now());
			user3.setUpdatedAt(LocalDate.now());
			userRepository.save(user3);

			// Add initial data for Teams
			Team team1 = new Team();
			team1.setName("Development Team");
			team1.setDescription("Team responsible for development");
			team1.setCreatedAt(LocalDate.now());
			team1.setUpdatedAt(LocalDate.now());
			teamRepository.save(team1);

			Team team2 = new Team();
			team2.setName("QA Team");
			team2.setDescription("Team responsible for quality assurance");
			team2.setCreatedAt(LocalDate.now());
			team2.setUpdatedAt(LocalDate.now());
			teamRepository.save(team2);

			// Add initial data for Team Members
			TeamMember member1 = new TeamMember();
			member1.setTeam(team1);
			member1.setUser(user1);
			member1.setRole("team_leader");
			member1.setJoinedAt(LocalDate.now());
			teamMemberRepository.save(member1);

			TeamMember member2 = new TeamMember();
			member2.setTeam(team1);
			member2.setUser(user2);
			member2.setRole("member");
			member2.setJoinedAt(LocalDate.now());
			teamMemberRepository.save(member2);

			// Add initial data for Projects
			Project project1 = new Project();
			project1.setClient(client1);
			project1.setName("Proiect Dezvoltare");
			project1.setDescription("Proiect de dezvoltare software.");
			project1.setStartDate(LocalDate.now());
			project1.setEndDate(LocalDate.now().plusMonths(6));
			project1.setStatus("in_progress");
			project1.setCreatedAt(LocalDate.now());
			project1.setUpdatedAt(LocalDate.now());
			projectRepository.save(project1);

			// Add initial data for Project Tasks
			ProjectTask task1 = new ProjectTask();
			task1.setProject(project1);
			task1.setTitle("Task 1");
			task1.setDescription("Primul task al proiectului.");
			task1.setStatus("pending");
			task1.setStartDate(LocalDate.now());
			task1.setEndDate(LocalDate.now().plusWeeks(1));
			task1.setCreatedAt(LocalDate.now());
			task1.setUpdatedAt(LocalDate.now());
			projectTaskRepository.save(task1);

			// Add initial data for Project Teams
			ProjectTeam projectTeam1 = new ProjectTeam();
			projectTeam1.setProject(project1);
			projectTeam1.setTeam(team1);
			projectTeam1.setAssignedAt(LocalDate.now());
			projectTeamRepository.save(projectTeam1);
		};
	}
}
