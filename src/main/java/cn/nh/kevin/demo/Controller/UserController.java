package cn.nh.kevin.demo.Controller;

import cn.nh.kevin.demo.DTO.ResultDTO;
import cn.nh.kevin.demo.DTO.UserDTO;
import cn.nh.kevin.demo.Enum.ResultEnum;
import cn.nh.kevin.demo.Service.LoginService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping("/index")
	public ModelAndView getInfo(){
		return new ModelAndView("index");
	}

	//@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ModelAndView login(@RequestBody UserDTO user) {
		String id = user.getId();
		String password = user.getPassword();
		if (loginService.check(id,password).getResult().equals(ResultEnum.SUCCESS)){
			return new ModelAndView("main");
		}
		else return new ModelAndView("error");
	}


	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResultDTO register(@RequestBody UserDTO userDTO){
		if (userDTO!=null){
			LOGGER.info("name 是{}",userDTO.getName());
			try{
				loginService.register(userDTO);
			}catch (DuplicateKeyException e) {
				return new ResultDTO(ResultEnum.FAIL,"id已存在，无需重复注册");
			}catch (Exception e){
				return new ResultDTO(ResultEnum.FAIL,"注册失败");
			}
			return new ResultDTO(ResultEnum.SUCCESS,"注册成功");
		}
		else return new ResultDTO(ResultEnum.FAIL,"输入不准为空");
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	public UserDTO queryById(String id){
		return loginService.queryById(id);
	}

}