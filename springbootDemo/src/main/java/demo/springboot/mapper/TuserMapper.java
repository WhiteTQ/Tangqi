package demo.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import demo.springboot.model.Tuser;

@Mapper
public interface TuserMapper {
	List<Tuser> getAllUser();

	Tuser fidUserByID(Integer id);

	int delUser(Integer id);

	int editUser(Tuser tuser);

	int addUser(Tuser tuser);
}
