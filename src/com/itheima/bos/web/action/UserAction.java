package com.itheima.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserServicce;
import com.itheima.bos.utils.MD5Utils;
import com.itheima.bos.web.action.base.BaseAcion;

@Controller // ("userAction")
@Scope("prototype")
public class UserAction extends BaseAcion<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private UserServicce userService;
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login() {
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
			User user = userService.login(model);
			if (user != null) {
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			} else {
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		} else {
			this.addActionError(this.getText("validateCodeError"));
			return "login";
		}
	}

	/**
	 * 用户退出
	 */
	public String logout() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}

	/**
	 * 修改当前登录用户密码
	 * 
	 * @throws IOException
	 */
	public String editPassword() throws IOException {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();// 新密码
		password = MD5Utils.md5(password);
		String flag = "1";
		try {
			userService.editPassword(password, user.getId());
		} catch (Exception e) {
			// 修改密码失败
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
}
