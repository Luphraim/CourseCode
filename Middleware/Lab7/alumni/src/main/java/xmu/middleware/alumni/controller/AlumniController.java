package xmu.middleware.alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xmu.middleware.alumni.domain.Alumni;
import xmu.middleware.alumni.service.AlumniService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jason
 */
@RestController
@RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    /**
     * 根据年级统计人数
     *
     * @return object
     */
    @GetMapping("/cbg")
    public Object countByGrade() {
        System.out.println(alumniService.countByGrade());

        return alumniService.countByGrade();
    }

    /**
     * 根据专业统计人数
     *
     * @return object
     */
    @GetMapping("/cbd")
    public Object countByDiscipline() {
        return alumniService.countByDiscipline();
    }

    /**
     * 查询
     *
     * @param searchName 搜索字段
     * @param searchText 字段值
     * @return object
     */
    @GetMapping("/")
    public Object getAlumni(@RequestParam String searchName, @RequestParam String searchText) {
        Map<String, String> data = new HashMap<>(4);
        data.put("name", searchName);
        data.put("text", searchText);
        return alumniService.getAlumni(data);
    }

    /**
     * 根据ID获得具体信息
     *
     * @param id id
     * @return object
     */
    @GetMapping("/info")
    public Object getAlumniById(@RequestParam Integer id) {
        return alumniService.getAlumniById(id);
    }

    /**
     * 添加成员
     *
     * @param alumni alumni
     * @return object
     */
    @PostMapping("/")
    public Object addAlumni(@RequestBody Alumni alumni) {
        return alumniService.addAlumni(alumni);
    }

    /**
     * 更新信息
     *
     * @param alumni alumni
     * @return object
     */
    @PutMapping("/")
    public Object updateAlumni(@RequestBody Alumni alumni) {
        return alumniService.updateAlumni(alumni);
    }

    /**
     * 删除成员
     *
     * @param id id
     * @return object
     */
    @DeleteMapping("/")
    public Object deleteAlumni(@RequestParam Integer id) {
        return alumniService.delete(id);
    }

}
