package com.HauLuong.HotelBook.Service;

import java.util.List;

import com.HauLuong.HotelBook.Model.Role;
import com.HauLuong.HotelBook.Model.User;

public interface IRoleService {
	  List<Role> getRoles();
	    Role createRole(Role theRole);

	    void deleteRole(Long id);
	    Role findByName(String name);

	    User removeUserFromRole(Long userId, Long roleId);
	    User assignRoleToUser(Long userId, Long roleId);
	    Role removeAllUsersFromRole(Long roleId);
}
