package demo.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springboot.model.Tuser;
import demo.springboot.service.TuserService;

@Controller
// @RestController 此注解指明该控制器直接返回数据，而不进行页面跳转
@RequestMapping("/tuser")
// 配置spring 扫描路径
@Api(value = "TuserController-用户接口模拟")
public class TuserController {
	@Autowired
	private TuserService tuerService;

	// @ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ApiOperation(value = "展示全部用户信息", notes = "无")
	// swagger使用注释
	public String getUser(HttpServletRequest request) {
		List<Tuser> users = tuerService.getAllUser();
		request.setAttribute("user", users);
		// JSONArray json = JSONArray.fromObject(users);
		// return json.toString();
		return "/First";
	}

	// @ResponseBody
	@RequestMapping(value = "/fidUserByID", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID查询用户信息", notes = "无")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户编号", required = true, dataType = "Integer")
	@ApiResponses({ @ApiResponse(code = 400, message = "请求参数没有填好"),
			@ApiResponse(code = 404, message = "请求地址错误或者页面跳转路径不正确"), })
	public String fidUserByID(HttpServletRequest request,
			@RequestParam Integer id) {
		Tuser tuser = tuerService.fidUserByID(id);
		request.setAttribute("user", tuser);
		return "/Edit";
		// JSONArray json = JSONArray.fromObject(users);
		// return json.toString();
	}

	@ResponseBody
	// @responsebody表示该方法的返回结果直接写入HTTP response body中。也就是返回的数据格式。
	// 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP
	// response body中。
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID编辑用户信息", notes = "无")
	@ApiImplicitParam(paramType = "query", name = "tuser", value = "用户对象信息", required = true, dataType = "Object")
	public String editUser(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String tuser) {
		Tuser user = (Tuser) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("tuser")),
				Tuser.class);
		int rs = tuerService.editUser(user);
		return rs > 0 ? "1" : "0";
	}

	@ResponseBody
	// @responsebody表示该方法的返回结果直接写入HTTP response body中。也就是返回的数据格式。
	// 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP
	// response body中。
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	@ApiOperation(value = "新增用户信息", notes = "无")
	@ApiImplicitParam(paramType = "query", name = "tuser", value = "用户对象信息", required = true, dataType = "Object")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String tuser) {
		Tuser user = (Tuser) JSONObject.toBean(
				JSONObject.fromObject(request.getParameter("tuser")),
				Tuser.class);
		int rs = tuerService.addUser(user);
		return rs > 0 ? "1" : "0";
	}

	// @ResponseBody
	// @responsebody表示该方法的返回结果直接写入HTTP response body中。也就是返回的数据格式。
	// 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP
	// response body中。
	@RequestMapping(value = "/addJsp", method = RequestMethod.GET)
	@ApiOperation(value = "新增用户信息", notes = "无")
	public String addUserJsp(HttpServletRequest request) {
		return "/Add";
	}

	@ResponseBody
	// @responsebody表示该方法的返回结果直接写入HTTP response body中。也就是返回的数据格式。
	// 一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP
	// response body中。
	@RequestMapping(value = "/delUser", method = RequestMethod.GET)
	@ApiOperation(value = "根据ID删除用户信息", notes = "无")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户编号", required = true, dataType = "Integer")
	public String delUser(HttpServletRequest request, String id) {
		int rs = tuerService.delUser(Integer.valueOf(id));
		return rs > 0 ? "1" : "0";
	}

}
