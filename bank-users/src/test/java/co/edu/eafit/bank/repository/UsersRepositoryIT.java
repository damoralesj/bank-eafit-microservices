package co.edu.eafit.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.eafit.bank.domain.Users;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UsersRepositoryIT {

	@Autowired
	UsersRepository usersRepository;
	
	
	@Test
	void findAll() {

		// Arrange
			List<Users> users = null;
		
		//Act
			users = usersRepository.findAll();
		
		//Assert
			assertNotNull(users);
			assertFalse(users.isEmpty());
			
		// No debería hacer esto
			users.forEach(user ->{
				log.info(user.getName());
			});
	}

}
