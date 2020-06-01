package xmu.middleware.web.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class Alumni {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer grade;
    private String college;
    private String discipline;
    private String phone;
    private String email;

    public Alumni(Integer id, String name, Integer gender, Integer grade, String college, String discipline, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.college = college;
        this.discipline = discipline;
        this.phone = phone;
        this.email = email;
    }

    public Alumni(String id, String name, String gender, String grade, String college, String discipline, String phone, String email) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.gender = Integer.parseInt(gender);
        this.grade = Integer.parseInt(grade);
        this.college = college;
        this.discipline = discipline;
        this.phone = phone;
        this.email = email;
    }
}
