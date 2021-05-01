package co.edu.eafit.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.eafit.bank.domain.UserType;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserTypeRepositoryIT {

	@Autowired
	UserTypeRepository userTypeRepository;
	
	
	@Test
	void findAll() {

		// Arrange
			List<UserType> userTypes = null;
		
		//Act
			userTypes = userTypeRepository.findAll();
		
		//Assert
			assertNotNull(userTypes);
			assertFalse(userTypes.isEmpty());
			
		// No debería hacer esto
			userTypes.forEach(userType ->{
				log.info(userType.getName());
			});
	}

}
