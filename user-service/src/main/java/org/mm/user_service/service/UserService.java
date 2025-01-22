package org.mm.user_service.service;

import java.util.Optional;

import org.mm.user_service.entity.UserEntity;
import org.mm.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		return userRepo.findByEmail(username).orElseThrow();
	}

	public UserEntity signUp(UserEntity userEntity) 
	{
		Optional<UserEntity> userByEmail = userRepo.findByEmail(userEntity.getEmail());
		
		if(userByEmail.isPresent())
		{
			throw new BadCredentialsException("User Already exists ...");
		}
		
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		return userRepo.save(userEntity);
	}

	public Boolean createAadharId(Long userId , Long aadharId)
	{
		UserEntity userEntity = userRepo.findById(userId).orElseThrow();
		
		userEntity.setAadharId(aadharId);
		
		UserEntity savedUser = userRepo.save(userEntity);
		
		if(savedUser.getAadharId() != null)
		{
			return true;
		}
		else {
			return false;
		}
	}

}