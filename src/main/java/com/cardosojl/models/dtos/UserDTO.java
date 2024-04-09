package com.cardosojl.models.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 6026062986300437308L;
	
	private Long id;
	private String email;
	private String name;
	private String Password;
	private Boolean accontNonExpired;
	private Boolean accontNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;
	private String permission;
	

}
