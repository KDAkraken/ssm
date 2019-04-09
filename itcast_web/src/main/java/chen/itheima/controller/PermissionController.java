package chen.itheima.controller;

import chen.itheima.domain.Permission;
import chen.itheima.service.impl.IPermissionServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionServiceimpl iPermissionServiceimpl;
    @RequestMapping("/findAll.do")
    @RolesAllowed("admin")
    public ModelAndView findAll()throws Exception{
        List<Permission> list = iPermissionServiceimpl.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;

    }
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        iPermissionServiceimpl.save(permission);
        return "redirect:findAll.do";
    }

}
