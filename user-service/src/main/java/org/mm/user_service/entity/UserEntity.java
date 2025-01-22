package org.mm.user_service.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String name;
	
	private Long aadharId;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return List.of();
	}

	@Override
	public String getPassword() 
	{
		return this.password;
	}
	
	@Override
	public String getUsername() 
	{
		return this.email;
	}


	
//	@OneToOne	
//	private AadharModel aadharModel;
//	
//	@OneToOne
//	private PanModel panModel;
//	
//	@OneToOne
//	private UserDetailModel userDetailModel;
//	
//	@OneToOne
//	private EducationModel educationModel;
}
