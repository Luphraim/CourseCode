package controller;

import mapper.AlumniMapper;
import mapper.UserMapper;
import model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@ResponseBody
@RequestMapping(produces = "application/json;charset=UTF-8")
public class InfoController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AlumniMapper alumniMapper;

    @RequestMapping("/login")
    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        //如果已登录则自动退出登录
        if (session.getAttribute("userDetail") != null) {
            httpServletResponse.sendRedirect("/logout");
        }
        //验证用户是否成功登录
        else {
            String username = httpServletRequest.getParameter("username");
            String password = httpServletRequest.getParameter("password");
            UserDTO user = userMapper.getUser(username, password);
            if (user != null) {
                System.out.println(user.getId() + ":" + "login");
                session = httpServletRequest.getSession();
                session.setAttribute("userDetail", user);
                //登录成功，将response中的status设置为200，以便aop获得此信息
                httpServletResponse.setStatus(200);
            } else {
                //登录成功，将response中的status设置为403，以便aop获得此信息
                httpServletResponse.setStatus(403);
            }
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        UserDTO user = (UserDTO) session.getAttribute("userDetail");
        //验证用户是否还未登录就登出
        if (user != null) {
            System.out.println(user.getId() + ":" + "logout");
            //登出成功，将response中的status设置为200，以便aop获得此信息
            httpServletResponse.setStatus(200);
        } else {
            //登录失败，将response中的status设置为403，以便aop获得此信息
            httpServletResponse.setStatus(403);
        }
    }

    @GetMapping("/alumni")
    public Object search() throws Exception {
        return alumniMapper.search();
    }

    @PutMapping("/alumni/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestParam String info) {
        return alumniMapper.update(id, info);
    }

    @GetMapping("/alumni/aggregate")
    public Object aggregate() {
        return alumniMapper.aggregate();
    }
}
