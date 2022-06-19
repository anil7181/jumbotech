package com.tecart.pqp.common;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecart.pqp.entity.base.User;
import com.tecart.pqp.utils.constants.MasterConstants;

@SuppressWarnings("serial")
public class MyUserPrincipal implements UserDetails {

	private User user;

	private Collection<? extends GrantedAuthority> authorities;

	public MyUserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getActive() == MasterConstants.DEFAULT_INT_ACTIVE__STATUS ? MasterConstants.DEFAULT_YES
				: MasterConstants.DEFAULT_NO;
	}

	public User getUser() {
		return user;
	}
}
