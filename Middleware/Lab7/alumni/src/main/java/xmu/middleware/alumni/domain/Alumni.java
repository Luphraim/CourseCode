package xmu.middleware.alumni.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author jason
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Alumni {

    private Integer id;
    private String name;
    private String gender;
    private Integer grade;
    private String college;
    private String discipline;
    private String phone;
    private String email;

    public Alumni(Integer id, String name, String gender, Integer grade, String college, String discipline, String phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.college = college;
        this.discipline = discipline;
        this.phone = phone;
        this.email = email;
    }

    public boolean check() {
        if (null == name || "".equals(name) || null == gender || "".equals(gender) || null == grade || null == college || "".equals(college) || null == discipline || "".equals(discipline)) {
            return false;
        }
        return true;
    }
}
