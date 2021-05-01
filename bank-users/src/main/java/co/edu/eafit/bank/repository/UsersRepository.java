package co.edu.eafit.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.eafit.bank.domain.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}
