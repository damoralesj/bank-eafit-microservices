package co.edu.eafit.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.eafit.bank.domain.UserType;
import co.edu.eafit.bank.domain.Users;
import co.edu.eafit.bank.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class UsersServiceIT {

	@Autowired
	UsersService usersService;
	
	@Autowired
	UserTypeRepository userTypeRepository;
	
	
	@Test
	@Order(5)
	void debeConsultarUnaListaDeUsers() {

		// Arrange
			List<Users> users = null;
		
		//Act
			users = usersService.findAll();
		
		//Assert
			assertNotNull(users);
			assertFalse(users.isEmpty());
			
		// No debería hacer esto
			users.forEach(user ->{
				log.info(user.getUserEmail());
			});
	}

	
	@Test
	@Order(4)
	void debeConsultarUnUserPorId() {

		// Arrange
			Users users = null;
			Optional <Users>  usersOptional = null;
			String id="ababeox@blog.com";
		
		//Act
			usersOptional = usersService.findById(id);
		
		//Assert
			assertTrue(usersOptional.isPresent(), "User con id:" + id + " No existe");
			
		// No debería hacer eso
			users = usersOptional.get();
			log.info("Nombre del cliente--> " + users.getName());

	}
	
	@Test
	@Order(1)
	void debeCrearUnUser() throws Exception {
		// Arrange
		
		Integer idUserType = 1;
		String idUser="alfonsorivas@gmail.com";
		
		Users user = null;
		UserType userType = null;
		
		Optional<UserType> userTypeOptional = userTypeRepository.findById(idUserType);
		userType = userTypeOptional.get();
		
		user =  new Users();
		user.setEnable("Y");
		user.setName("Alfonso Rivas");
		user.setToken(null);
		user.setUserEmail(idUser);
		user.setUserType(userType);	
		
		//Act
			user = usersService.save(user);
		
		//Assert		
			assertNotNull(user,"El user es nulo no se grabo");
	}
	
	@Test
	@Order(2)
	void debeModificarUnUser() throws Exception  {
		
		//Arrange

		String idUser="alfonsorivas@gmail.com";
		Users user=null;
		
		Optional<Users> usersOptional=null;		
		usersOptional = usersService.findById(idUser);
		
		if(usersOptional.isEmpty()==true) {
			throw new RuntimeException("El user no existe");
		}
		
		String nameExpected="Alfonso Rivas Rivas";
		
		user = usersOptional.get();
		user.setName("Alfonso Rivas Rivas");
	
		
		//Act
			user = usersService.update(user);
			
		//Assert
			assertNotNull(user,"El user es nulo no se grabo");
			assertEquals(nameExpected, user.getName());
	}
	
	@Test
	@Order(3)
	void debeBorrarUnUser() throws Exception  {
		
		//Arrange
		String idUser = "alfonsorivas@gmail.com";
		Users user = null;
		Optional<Users> usersOptional=null;	
		
		usersOptional = usersService.findById(idUser);
		
		if(usersOptional.isEmpty()==true) {
			throw new RuntimeException("El user no existe");
		}
		
		user = usersOptional.get();
		
		//Act
		usersService.delete(user);
			
		//Assert
	}
	
}
