package co.edu.eafit.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.eafit.bank.domain.Users;
import co.edu.eafit.bank.dto.UsersDTO;
import co.edu.eafit.bank.mapper.UsersMapper;
import co.edu.eafit.bank.service.UsersService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersMapper usersMapper;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsersDTO> detele(@PathVariable("id") String id) throws Exception {
		
		usersService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping
	public ResponseEntity<UsersDTO> save(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		
		Users users = usersMapper.usersDTOToUsers(usersDTO);
		users = usersService.save(users);
		
		usersDTO = usersMapper.usersToUsersDTO(users);
		
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@PutMapping
	public ResponseEntity<UsersDTO> update(@Valid @RequestBody UsersDTO usersDTO) throws Exception {
		
		Users users = usersMapper.usersDTOToUsers(usersDTO);
		users = usersService.update(users);
		
		usersDTO = usersMapper.usersToUsersDTO(users);
		
		return ResponseEntity.ok().body(usersDTO);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsersDTO> findById(@PathVariable("id") String id) {
		
		Optional<Users> usersOptional = usersService.findById(id);
		
		Users users = (usersOptional.isPresent()==true) ? usersOptional.get() : null;
		
		UsersDTO usersDTO = usersMapper.usersToUsersDTO(users);
		
		
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping()
	public ResponseEntity<List<UsersDTO>> findAll(){
		
		List<Users> users = usersService.findAll();
		List<UsersDTO> usersDTOs = usersMapper.usersToUsersDTOs(users);
		
		return ResponseEntity.ok().body(usersDTOs);
	}

	
}
