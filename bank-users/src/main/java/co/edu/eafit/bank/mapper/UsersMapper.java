package co.edu.eafit.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.eafit.bank.domain.Users;
import co.edu.eafit.bank.dto.UsersDTO;

@Mapper
public interface UsersMapper {
	
	@Mapping(source = "userType.ustyId", target = "ustyId")
	public UsersDTO usersToUsersDTO ( Users user);
	
	
	@Mapping(source = "ustyId", target = "userType.ustyId")	
	public Users usersDTOToUsers (UsersDTO usersDTO);
	
	
	public List<UsersDTO> usersToUsersDTOs ( List<Users> users);
	
	public List<Users> usersDTOsToUsers (List<UsersDTO> usersDTO);
}
