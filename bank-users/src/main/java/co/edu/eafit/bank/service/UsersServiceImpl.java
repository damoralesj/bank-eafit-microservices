package co.edu.eafit.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.bank.domain.Customer;
import co.edu.eafit.bank.domain.Users;
import co.edu.eafit.bank.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	Validator validator;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		
		return usersRepository.findAll();
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String id) {
		
		return usersRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users save(Users entity) throws Exception {
		
		//Validar que la entidad recibida sea un objeto
		if (entity == null) {
			throw new Exception("El user es nulo");
		}
		
		//Validaciones definidas en el modelo domain
		validate(entity);
		
		//Validación que el customer no exista previamente
		if(usersRepository.existsById(entity.getUserEmail()) == true) {
			throw new Exception("El user ya existe");
		}
		
		return usersRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users update(Users entity) throws Exception {
		
		//Validar que la entidad recibida sea un objeto
		if (entity == null) {
			throw new Exception("El user es nulo");
		}
		
		//Validaciones definidas en el modelo domain
		validate(entity);
		
		//Validación que el customer no exista previamente
		if(usersRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El user no existe");
		}
		
		return usersRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Users entity) throws Exception {

		//Validar que la entidad recibida sea un objeto
		if (entity == null) {
			throw new Exception("El user es nulo");
		}
		
		//Validaciones definidas en el modelo domain
		validate(entity);
		
		//Validacion que el customer no exista previamente
		if(usersRepository.existsById(entity.getUserEmail()) == false) {
			throw new Exception("El customer no existe");
		}
		
		findById(entity.getUserEmail()).ifPresent(user-> {
			
			if (user.getTransactions()!= null && user.getTransactions().isEmpty() == false) {
				throw new RuntimeException ("El user tienet transacciones asociadas");
			}
			
			usersRepository.deleteById(entity.getUserEmail());

		});
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		
		if (id ==null) {
			throw new Exception("El id de user es nulo");
		}
		
		if (usersRepository.existsById(id)) {
			delete(usersRepository.findById(id).get());
		}
		
	}

	@Override
	public void validate(Users entity) throws Exception {
		
		Set<ConstraintViolation<Users>> constrainsViolations = validator.validate(entity);
		
		if (constrainsViolations.isEmpty()==false) {
			throw new ConstraintViolationException(constrainsViolations);
		}		
	}

	@Override
	public Long count() {

		return usersRepository.count();
	}

}
