package chen.itheima.controller;

import chen.itheima.domain.Role;
import chen.itheima.domain.UserInfo;


import chen.itheima.service.IRoleService;
import chen.itheima.service.impl.IRoleServiceimpl;
import chen.itheima.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceimpl userServiceimpl;
    @Autowired
    private IRoleServiceimpl iRoleServiceimpl;

    @RequestMapping("/findAll.do")
    @PreAuthorize("authentication.principal.username==('tom')")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> userInfos = userServiceimpl.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userServiceimpl.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")

    public ModelAndView findById(String id) throws Exception {
        UserInfo userInfo = userServiceimpl.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String id) throws Exception {
        UserInfo user = userServiceimpl.findById(id);
        List<Role> list = iRoleServiceimpl.findOtherRole(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.addObject("roleList", list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userServiceimpl.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
