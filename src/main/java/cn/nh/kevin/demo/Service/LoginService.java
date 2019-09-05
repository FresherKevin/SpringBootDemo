package cn.nh.kevin.demo.Service;

import cn.nh.kevin.demo.DTO.ResultDTO;
import cn.nh.kevin.demo.DTO.UserDTO;
import cn.nh.kevin.demo.Enum.ResultEnum;
import cn.nh.kevin.demo.Mapper.UserMapper;
import cn.nh.kevin.demo.Util.Redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 标题:
 * 描述:
 * 版权: Kevin
 * 作者: xck
 * 时间: 2019-08-26 19:29
 */
@Service
public class LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param id
     * @param password
     * @return java.lang.String
     * @Description //登录验证
     * @Author xck
     * @Date 2019/8/28 15:30
     **/
    public ResultDTO check(String id, String password) {
        String Message;
        if (id == null) {
            Message = "账户不能为空";
            return new ResultDTO(ResultEnum.FAIL, Message);
        } else if (password == null) {
            Message = "密码不能为空";
            return new ResultDTO(ResultEnum.FAIL, Message);
        } else {
            UserDTO userDTO = userMapper.findUser(id);
            if (userDTO.getPassword().equals(password)) {
                Message = "验证成功";
                int count = (Integer) redisUtil.get(id);
                LOGGER.info("失败次数{}", count);
                return new ResultDTO(ResultEnum.SUCCESS, Message);
            } else {
                Message = "密码错误";
                redisUtil.set(id, 1);
                return new ResultDTO(ResultEnum.FAIL, Message);
            }
        }
    }

    /**
     * @param userDTO
     * @return cn.nh.kevin.demo.Enum.ResultEnum
     * @Description //注册
     * @Author xck
     * @Date 2019/8/28 15:30
     **/
    public ResultEnum register(UserDTO userDTO) {
        LOGGER.info("id为{}", userDTO.getId());
        try {
            userMapper.insertUser(userDTO);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                LOGGER.error("id={},已存在", userDTO.getId());
                throw e;
            } else {
                LOGGER.error("注册id={}异常", userDTO.getId());
            }
        }
        return ResultEnum.SUCCESS;
    }

    /**
     * @param id
     * @return cn.nh.kevin.demo.DTO.UserDTO
     * @Description //查询信息
     * @Author xck
     * @Date 2019/8/28 15:31
     **/
    public UserDTO queryById(String id) {
        return userMapper.findUser(id);
    }
}