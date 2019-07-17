package dao;

import java.util.List;

import models.Userdata;

public interface UserDao {
	
	public Userdata selectUser(String username);
	public List<Userdata> selectAllUser();
	public boolean createUser(Userdata newUser);


}
