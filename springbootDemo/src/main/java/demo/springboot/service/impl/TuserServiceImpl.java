package demo.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.springboot.mapper.TuserMapper;
import demo.springboot.model.Tuser;
import demo.springboot.service.TuserService;

@Service
public class TuserServiceImpl implements TuserService {
	@Autowired
	private TuserMapper tuserMapper;
 
	@Override
	public List<Tuser> getAllUser() {
		return tuserMapper.getAllUser();
	}

	@Override
	public int delUser(Integer id) {
		return tuserMapper.delUser(id);
	}

	@Override
	public int editUser(Tuser tuser) {
		return tuserMapper.editUser(tuser);
	}

	@Override
	public Tuser fidUserByID(Integer id) {
		return tuserMapper.fidUserByID(id);
	}

}

