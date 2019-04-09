package chen.itheima.controller;

import chen.itheima.domain.Permission;
import chen.itheima.domain.Role;
import chen.itheima.service.impl.IPermissionServiceimpl;
import chen.itheima.service.impl.IRoleServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleServiceimpl iRoleServiceimpl;
    @Autowired
    private IPermissionServiceimpl iPermissionServiceimpl;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> list = iRoleServiceimpl.findAll();
        ModelAndView mv = new ModelAndView();

        mv.addObject("roleList", list);
        mv.setViewName("role-list");
        return mv;

    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        Role role = iRoleServiceimpl.findById(roleId);
        List<Permission> permission = iPermissionServiceimpl.findOtherPermission(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role", role);
        mv.addObject("permissionList", permission);

        mv.setViewName("role-permission-add");
        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        iRoleServiceimpl.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role byRoleId = iRoleServiceimpl.findByRoleId(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",byRoleId );
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addRoleToUser(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds) throws Exception {
        iRoleServiceimpl.addRoleToUser(roleId,permissionIds);
        return "redirect:findAll.do";
    }


}
