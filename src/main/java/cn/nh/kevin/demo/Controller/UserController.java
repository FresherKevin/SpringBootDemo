package cn.nh.kevin.demo.Controller;

import cn.nh.kevin.demo.DTO.ResultDTO;
import cn.nh.kevin.demo.DTO.UserDTO;
import cn.nh.kevin.demo.Enum.ResultEnum;
import cn.nh.kevin.demo.Exception.DefineException.ChannleException;
import cn.nh.kevin.demo.Rest.JsonResponse;
import cn.nh.kevin.demo.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(@RequestBody UserDTO user) throws ChannleException {
		String id = user.getId();
		String password = user.getPassword();
		if(loginService.check(id,password).getResult().equals(ResultEnum.SUCCESS)){
			return new ModelAndView("main");
		}
		else return new ModelAndView("error");

	}


	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public JsonResponse<ResultDTO> register(@RequestBody UserDTO userDTO){
		if (userDTO!=null){
			LOGGER.info("name 是{}",userDTO.getName());
			try{
				loginService.register(userDTO);
			}catch (DuplicateKeyException e) {
				return JsonResponse.fail("86613","id已存在，无需重复注册");
			}catch (Exception e){
				return JsonResponse.fail("8661","注册失败");
			}
			return JsonResponse.success(new ResultDTO(ResultEnum.SUCCESS,"注册成功"));
		}
		else return JsonResponse.fail("867","输入不准为空");
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	public JsonResponse<UserDTO> queryById(String id){
		return JsonResponse.success(loginService.queryById(id));
	}

}