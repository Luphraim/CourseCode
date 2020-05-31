package xmu.middleware.web.controller;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xmu.middleware.web.dao.AlumniDao;
import xmu.middleware.web.entities.Alumni;

/**
 * @author Jason
 */
@RestController
public class AlumniController {

    @Autowired
    private AlumniDao alumniDao;

    @GetMapping("/")
    public String index(ModelMap map) {
        return "/index";
    }

    @RequestMapping("/add")
    public Object addAlumni(@RequestBody Alumni alumni) {
        if (alumni.getId() == null) {
            return "/error";
        }

        try {

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequestMapping("/{id}")
    public Object searchById(@PathVariable Integer id) {
        if (id == null) {

        }


    }

}
