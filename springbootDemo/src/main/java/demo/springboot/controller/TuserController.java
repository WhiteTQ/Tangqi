package demo.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springboot.model.Tuser;
import demo.springboot.service.TuserService;

@Controller  // @RestController 此注解指明该控制器直接返回数据，而不进行页面跳转
@RequestMapping("/tuser") //配置spring 扫描路径
@Api(value = "TuserController-用户接口模拟")
public class TuserController {
	@Autowired
	private TuserService tuerService;
 
	@ResponseBody
	@RequestMapping(value="/getUser",method= RequestMethod.GET)
    @ApiOperation(value="展示用户信息", notes="无")
	public String getUser(HttpServletRequest request) {
		List<Tuser> users = tuerService.getAllUser();
		JSONArray json = JSONArray.fromObject(users);     
        request.setAttribute("user", users);
		return json.toString();
//		return "/First";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/fidUserByID",method= RequestMethod.GET)
    @ApiOperation(value="根据ID查询用户信息", notes="无")
    @ApiImplicitParam(paramType="query", name = "id", value = "用户编号", required = true, dataType = "Integer")
	@ApiResponses({
		@ApiResponse(code=400,message="请求参数没有填好"),
		@ApiResponse(code=404,message="请求地址错误或者页面跳转路径不正确"),
	})
	public String fidUserByID(@RequestParam Integer id) {
		Tuser users = tuerService.fidUserByID(id);
		if(users==null){
			return "null";
		}
		JSONArray json = JSONArray.fromObject(users);     
		return json.toString();
//		return "/First";
	}
	
	
	 @RequestMapping(value="/delUser",method= RequestMethod.GET)
	 @ApiOperation(value="根据ID删除用户信息", notes="无")
	 @ApiImplicitParam(paramType="query", name = "id", value = "用户编号", required = true, dataType = "Integer")
	public String delUser(HttpServletRequest request, @Param("id")String id) {
		int result = tuerService.delUser(Integer.valueOf(id));
        request.setAttribute("result", result);
        if(result>0){
    		return "success";
        }else{
    		return "erro";
        }
	}
	
}

