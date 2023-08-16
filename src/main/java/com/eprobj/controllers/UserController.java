package com.eprobj.controllers;

import com.alibaba.fastjson.JSON;
import com.eprobj.config.MyLog;
import com.eprobj.entity.EmailProv;
import com.eprobj.entity.Role;
import com.eprobj.entity.User;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;
import com.eprobj.mapper.EmailProvMapper;
import com.eprobj.mapper.UserMapper;
import com.eprobj.service.EmailProvService;
import com.eprobj.service.RoleService;
import com.eprobj.service.UserService;
import com.eprobj.utill.ExportExcelUtils;
import com.eprobj.utill.RespUtil;
import com.eprobj.utill.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by WXX on 2019/9/4.
 * 实现用户的注册登陆等功能
 *
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailProvService emailProvService;

    @Value("${system.resetPassword}")
    String password;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public String index(){
        return "views/user/login";
    }

    @RequestMapping("/register")
    public String register(){
        return "views/user/register";
    }
    @RequestMapping("/repairpassword")
    public String repairpassword(){
        return "repairpassword";
    }

    @MyLog(detail = "用户注册",operationType = OperationType.INSERT,operationUnit = OperationUnit.USER)
    @RequestMapping("/registeruser")
    @ResponseBody
    public Object registeruser(@RequestBody User user){
        User user1 = userService.queryByUserEmail(user.getEmail());
        User user2 = userService.queryInfoByUsername(user.getLoginName());
        EmailProv emailProv = emailProvService.findByEmail(user.getEmail());
        if (emailProv.getCode().equals(user.getCode())) {
            if (user1 != null) {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "邮箱已存在", null);
            } else if (user2 != null) {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户名已存在", null);
            } else {
                if (userService.registerData(user)) {
                    RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "注册成功", null);
                } else {
                    RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "注册失败", null);
                }
            }
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "注册成功", null);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "验证码错误", null);
        }
    }

    @MyLog(detail = "用户登陆",operationType = OperationType.SELECT,operationUnit = OperationUnit.USER)
    @RequestMapping("/loginuser")
    @ResponseBody
    public Object  loginName(@RequestBody User user2) throws Exception {
        Subject subject= SecurityUtils.getSubject();
        User user= userService.queryByUserEmail ( user2.getEmail());
        if (user != null) {
            if (user.getStatus() == 0) {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户已锁定，请解锁后登录", null);
            } else {
                List<Role> roles = roleService.listByUserName(user.getUserName());

                if (roles.size() > 0) {
                    //2.封装用户数据
                    Boolean rememberMe = false;
                    if (StringUtils.isNotBlank(user2.getRememberMe())) {
                        if ("on".equals(user2.getRememberMe())) {
                            rememberMe = true;
                        }
                    }
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),ShiroUtils.getMD5Str ( user2.getPassword() ), rememberMe);
                    try {
                        subject.login(token);
                        if(subject.isAuthenticated()){
                            Session session = subject.getSession();
                            session.setAttribute("currentUser",user2);
                        }
                    } catch (Exception e ) {
                        return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户名或密码不正确", null);
                    }

                } else {
                    return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户没有权限登录，请先授予权限", null);
                }
            }

           return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "登录成功", null);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户名不存在", null);
        }
    }

    @RequestMapping("/index")
    public String toIndex() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            logger.info(">>会话失效，请重新登录");
            return "redirect:/login";
        } else {
            return "views/index";
        }
    }

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("/yonghu")
    public String user(){
        return "views/fzw/yonghu";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Object getAllUser(@RequestParam(required = false) String info,
                             @RequestParam(value="page", defaultValue="0") Integer page,
                             @RequestParam(value="limit", defaultValue="10") Integer limit){
        if (StringUtils.isEmpty(info))
            info="";
            Map map = new HashMap();
            map.put("info",info);
            map.put("page",(page-1)*limit);
            map.put("limit",limit);
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户查询成功", userService.queryInfoByInfo(map),
                userService.queryInfoNumByInfo(map));
    }

    /**
     * 用户新增/编辑页面
     * @return
     */
    @RequestMapping("/user/edit")
    public String edit(@RequestParam(value="userId", required=false,defaultValue = "0") Integer userId, HttpServletRequest request){
        if (userId!=0){
            request.setAttribute("user",userService.queryInfoById(userId));
        }else {
            request.setAttribute("user",new User());
        }
        return "views/user/administrators/adminform";
    }

    /**
     * 用户删除
     * @return
     */
    @MyLog(detail = "删除用户",operationType = OperationType.DELETE,operationUnit = OperationUnit.USER)
    @RequestMapping("/user/del")
    @ResponseBody
    public String delUser(@RequestParam(value="userId[]")List<Integer> userId){
        if (userId != null && userId.size()>0){
            if (userService.delUserByIds(userId)){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户删除成功！", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户删除失败", false);
            }
        }else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户删除失败", false);
        }
    }

    /**
     * 用户新增/编辑页面
     * @return
     */
    @MyLog(detail = "用户新增/编辑",operationType = OperationType.UPDATE,operationUnit = OperationUnit.USER)
    @RequestMapping("/user/save")
    @ResponseBody
    public Object save(@ModelAttribute User user){
        if (user.getId() == null){
            if(userService.registerData(user)){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户保存成功", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户保存失败", true);
            }
        }else {
            if(userService.updateUser(user)){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户保存成功", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "用户保存失败", true);
            }
        }
    }

    /**
     * 用户删除
     * @return
     */
    @RequestMapping("/user/status")
    @ResponseBody
    public String updateUserStatus(@ModelAttribute User user){
        if (user != null && user.getId()>0){
            if (userService.updateUserStatus(user)){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功！", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败", false);
            }
        }else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败", false);
        }
    }

    /**
     * 重置密码
     * @return
     */
    @MyLog(detail = "重置密码",operationType = OperationType.UPDATE,operationUnit = OperationUnit.USER)
    @RequestMapping("/user/resetPassword")
    @ResponseBody
    public String resetPassword(@RequestParam(value="userIds[]")List<Integer> userId){
        if (userId != null && userId.size()>0){
            Map map = new HashMap();
            map.put("password",ShiroUtils.getStrByMD5(password));
            map.put("userIds",userId);
            if (userService.resetPassword(map)){
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功！", true);
            }else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败", false);
            }
        }else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败", false);
        }
    }


    /******************************Role*****************************/

    /**
     * 角色选择
     * @return
     */
    @RequestMapping("/role/list")
    public String roleList(HttpServletRequest request){
        List<Role> roleList = userService.roleList(1);      //可用角色列表
        request.setAttribute("userId",request.getParameter("userId"));
        request.setAttribute("roleList",JSON.toJSONString(roleList));
        List<Role> roles = userService.listByUserId(Integer.parseInt(request.getParameter("userId")));
        StringBuffer stringBuffer = new StringBuffer();
        roles.forEach(role -> {
            stringBuffer.append(role.getRoleId()+",");
        });
        request.setAttribute("checked",stringBuffer);
        return "views/user/administrators/roleList";
        }


    /**
     * 角色保存
     * @return
     */
    @RequestMapping("/role/save")
    @ResponseBody
    public Object saveRoleList(HttpServletRequest request){
        String userid = request.getParameter("userId");
        String roleIds = request.getParameter("roleIds");
        if (StringUtils.isNotBlank(userid) && StringUtils.isNotBlank(roleIds)){
            List<Integer> roleIdList = new ArrayList();
            Arrays.stream(roleIds.split(",")).forEach(roleId->{
                roleIdList.add(Integer.parseInt(roleId));
            });
            userService.delRole(Integer.parseInt(userid));
            userService.saveRole(Integer.parseInt(userid),roleIdList);
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功", true);
        }else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败", false);
        }
    }

    @MyLog(detail = "用户登出",operationType = OperationType.UPDATE,operationUnit = OperationUnit.USER)
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "views/user/login";
    }

}
