package cn.nh.kevin.demo.Controller;

import cn.nh.kevin.demo.DTO.UserDTO;
import cn.nh.kevin.demo.Enum.ResultEnum;
import cn.nh.kevin.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 标题:
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-26 19:01
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/index")
	public ModelAndView getInfo(){

		return new ModelAndView("index");
	}

	//@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public UserDTO login(@RequestBody UserDTO user){
		String name = user.getName();
		String password = user.getPassword();
		//return loginService.Check(name,password);

		user.setName(user.getName()+" by deal");
		return user;
	}

}
