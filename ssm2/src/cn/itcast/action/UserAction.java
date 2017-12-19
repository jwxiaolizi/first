package cn.itcast.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.pojo.User;
@Controller
@RequestMapping("/user")
public class UserAction {
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user) {
		if (user.getUsername().equals("123") && user.getPassword().equals("123")) {//登录成功
			request.getSession().setAttribute("user", user);
			return "forward:/item/itemList";
		}else {
			return "login";
		}
		
	}
}
