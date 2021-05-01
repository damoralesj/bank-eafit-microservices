package co.edu.eafit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.eafit.bank.domain.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
	

}
