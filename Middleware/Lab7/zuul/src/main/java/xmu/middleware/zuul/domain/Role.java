package xmu.middleware.zuul.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ringoer
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Role {

    private Integer id;

    private Integer roleId;

    private String url;

    private String method;

    private String gmtCreate;
    private String gmtModified;
    private Boolean beDeleted;

    public Role(){}

    public Role(String url, String method) {
        this.url = url;
        this.method = method;
    }
}
