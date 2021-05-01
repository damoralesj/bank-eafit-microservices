package co.edu.eafit.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "users", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_email", unique = true, nullable = false)
	@NotNull
	@Email
	@Size(min=4, max=50)
	private String userEmail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usty_id")
	@NotNull
	private UserType userType;


	@Column(name = "enable", nullable = false)
	@NotNull
	@Size(min=1, max=1)
	private String enable;

	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min=2, max=50)
	private String name;
	
	@Column(name = "token")
	@Size(min=0, max=100)
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Transaction> transactions = new ArrayList<>();

}