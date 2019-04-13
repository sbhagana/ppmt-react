package com.ppmtoolfullstack.ppmt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ppmtoolfullstack.ppmt.Domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	 User findByUsername(String username);
	    User getById(Long id);

}
