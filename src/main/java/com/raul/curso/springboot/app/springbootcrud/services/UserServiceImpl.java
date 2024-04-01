package com.raul.curso.springboot.app.springbootcrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raul.curso.springboot.app.springbootcrud.entities.Role;
import com.raul.curso.springboot.app.springbootcrud.entities.User;
import com.raul.curso.springboot.app.springbootcrud.repositories.RoleRepository;
import com.raul.curso.springboot.app.springbootcrud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User save(User user) {
		
		Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
		List<Role> roles = new ArrayList<>();
		
		optionalRoleUser.ifPresent(roles::add);
		
		if(user.isAdmin()) {
			Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
			optionalRoleAdmin.ifPresent(roles::add);
		}
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

}
