package demo.springboot.service;

import java.util.List;

import demo.springboot.model.Tuser;

public interface TuserService {
	List<Tuser> getAllUser();
	
	Tuser fidUserByID(Integer id);
	
	int delUser(Integer id);
	
	int editUser(Tuser tuser);
	
	int addUser(Tuser tuser);
}
