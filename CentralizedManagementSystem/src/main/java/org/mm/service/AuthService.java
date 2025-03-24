package org.mm.service;

import java.util.Optional;

import org.mm.dto.LoginResponseDto;
import org.mm.entity.Session;
import org.mm.entity.UserProfileDetailsEntity;
import org.mm.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService 
{	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private SecondUserService secondUserService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	public LoginResponseDto login(UserProfileDetailsEntity secondUserEntity)
	{
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(secondUserEntity.getEmail(), secondUserEntity.getPassword()));
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(secondUserEntity.getEmail(), secondUserEntity.getPassword())
				);
		
		UserProfileDetailsEntity user = (UserProfileDetailsEntity) authentication.getPrincipal();
		
		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		
		sessionService.generateNewSession(user, refreshToken);
		
		return new LoginResponseDto(user.getId(), accessToken, refreshToken);
	}

	public LoginResponseDto refreshToken(String refreshToken)
	{
		Long userId = jwtService.getUserIdFromToken(refreshToken);
		sessionService.validateSession(refreshToken);
		UserProfileDetailsEntity user = secondUserService.getUserByUserId(userId);
		
		String accessToken = jwtService.generateAccessToken(user);
		
		return new LoginResponseDto(user.getId(), accessToken, refreshToken);	
	}


    public void logout(String refreshToken) {
        // Remove "Bearer " prefix if present
        if (refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }

        Optional<Session> sessionOpt = sessionRepo.findByRefreshToken(refreshToken);

        if (sessionOpt.isPresent()) {
            sessionRepo.delete(sessionOpt.get());
        } else {
            throw new IllegalArgumentException("Invalid refresh token or session not found");
        }
    }
}
