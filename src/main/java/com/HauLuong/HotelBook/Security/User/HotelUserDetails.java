package com.HauLuong.HotelBook.Security.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.HauLuong.HotelBook.Model.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelUserDetails  implements UserDetails{
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private Long id;
    private  String email;
    private String password;
    private Collection<GrantedAuthority> authorities;


	public HotelUserDetails(Long id, String email, String password, Collection<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	public static HotelUserDetails buildUserDetails(User user){
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new HotelUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities);

    }
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		   return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		 return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
        return true;

	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
