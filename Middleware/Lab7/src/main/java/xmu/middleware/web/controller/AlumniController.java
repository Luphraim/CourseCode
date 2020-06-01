package xmu.middleware.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xmu.middleware.web.dao.AlumniDao;
import xmu.middleware.web.entities.Alumni;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
@RestController
@RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
public class AlumniController {

    @Autowired
    private AlumniDao alumniDao;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if ("lab7".equals(account) && "middleware".equals(password)) {
            session.setAttribute("loginUser", "admin");
            session.setAttribute("account", account);
            return "redirect:/sysInfo";
        } else {
            return "login";
        }
    }

    @GetMapping("/signout")
    public String sighOut(HttpSession session) {
        session.removeAttribute("loginUser");
        session.removeAttribute("id");
        session.removeAttribute("title");
        return "redirect:/";
    }

    @RequestMapping("/add")
    public Object addAlumni(HttpServletRequest request, Model model) {
        try {
            Alumni alumni = new Alumni(request.getParameter("id"), request.getParameter("name"),
                    request.getParameter("gender"), request.getParameter("grade"),
                    request.getParameter("college"), request.getParameter("discipline"),
                    request.getParameter("phone"), request.getParameter("email"));
            if (alumniDao.addAlumni(alumni) == 1) {
                model.addAttribute("msg", "添加信息成功！");
            } else {
                model.addAttribute("msg", "添加信息失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/add";
    }

    @RequestMapping("/info/{id}")
    public String searchById(@PathVariable Integer id, Model model) {

        if (id == null) {
            return "redirect:/sysInfo";
        }

        try {
            Alumni alumni = alumniDao.searchById(id);
            model.addAttribute("alumni", alumni);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "info";
    }

    @GetMapping("/search")
    public Object getAll(Model model) {
        Map<String, String> data = new HashMap<>(4);
        data.put("name", null);
        data.put("text", null);
        try {
            List<Alumni> alumniList = alumniDao.searchBy(data);
            Map<String, Alumni> alumniMap = new HashMap<>();
            for (Alumni alumni : alumniList) {
                alumniMap.put(alumni.getId().toString(), alumni);
            }
            model.addAttribute("alumniList", alumniMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

    @GetMapping("/search_{name}_{text}")
    public String searchBy(@PathVariable String name, @PathVariable String text, Model model) {
        Map<String, String> data = new HashMap<>(4);
        data.put("name", name);
        data.put("text", text);
        try {
            List<Alumni> alumniList = alumniDao.searchBy(data);
            Map<String, Alumni> alumniMap = new HashMap<>();
            for (Alumni alumni : alumniList) {
                alumniMap.put(alumni.getId().toString(), alumni);
            }
            model.addAttribute("alumniList", alumniMap.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

    @PutMapping("/chInfo")
    public String alterAlumni(HttpServletRequest request, Model model) {
        try {
            Alumni alumni = new Alumni(request.getParameter("id"), request.getParameter("name"),
                    request.getParameter("gender"), request.getParameter("grade"),
                    request.getParameter("college"), request.getParameter("discipline"),
                    request.getParameter("phone"), request.getParameter("email"));
            if (alumniDao.alterAlumni(alumni) == 1) {
                model.addAttribute("msg", "修改信息成功！");
            } else {
                model.addAttribute("msg", "修改信息失败!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/sysInfo";
    }

    @DeleteMapping("/del/{id}")
    public String delete(@PathVariable Integer id, Model model) {

        if (id == null) {
            return "search";
        }

        try {
            if (alumniDao.delete(id) == 1) {
                model.addAttribute("msg", "删除成功");
            } else {
                model.addAttribute("msg", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }
}
