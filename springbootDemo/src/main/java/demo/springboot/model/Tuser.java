package demo.springboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户对象模型")
public class Tuser {
    @ApiModelProperty(value="id" ,required=true)
	private int id;
    @ApiModelProperty(value="name" ,required=true)
    private String name;
    @ApiModelProperty(value="password" ,required=true)
    private String password;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
