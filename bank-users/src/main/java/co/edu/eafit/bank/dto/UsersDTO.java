package co.edu.eafit.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.edu.eafit.bank.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//DATA TRANSFER OBJECT
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	
	@NotNull
	@Email
	@Size(min=4, max=50)
	private String userEmail;

	@NotNull
	private Integer ustyId;

	@NotNull
	@Size(min=1, max=1)
	private String enable;

	@NotNull
	@Size(min=2, max=50)
	private String name;
	
	@Size(min=0, max=100)
	private String token;
}
