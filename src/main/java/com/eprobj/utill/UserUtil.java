package com.eprobj.utill;

import com.eprobj.constants.UserConstants;
import com.eprobj.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

public class UserUtil {

	public static User getCurrentUser() {
		User user = (User) getSession().getAttribute(UserConstants.LOGIN_USER);

		return user;
	}

	private UserUtil() {
	}

	public static void setUserSession(User user) {
		getSession().setAttribute(UserConstants.LOGIN_USER, user);
	}


	public static Session getSession() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		return session;
	}

	public static void reloadSite(List siteList){
		UserUtil.getSession().setAttribute("siteList",siteList);     //重载可读站点
		Map currentSite = (Map)UserUtil.getSession().getAttribute("currentSite");//当前站点
		siteList.stream().forEach(site->{
			if (((Map)site).get("id").equals(currentSite.get("id")))
				UserUtil.getSession().setAttribute("currentSite",site);
		});
	}

}
