package com.example.authentication;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
	@Query("SELECT u FROM Users u")
	List<Users> getAllUsers();
	@Query("SELECT u FROM Users u where u.username=:username")
	Users getUser(String username);

}