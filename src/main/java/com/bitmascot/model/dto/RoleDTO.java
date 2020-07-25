package com.bitmascot.model.dto;


import com.bitmascot.model.entity.User;

public class RoleDTO {

	  private  Long roleId;
	  private String role;
	  private User user;

	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	  
}
