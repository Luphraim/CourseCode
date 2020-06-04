package xmu.middleware.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Ringoer
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private String nickname;
    private String avatar;
    private String signature;
    private String mobile;
    private Integer gender;
    private LocalDateTime birthday;
    private LocalDateTime gmtModified;
    private LocalDateTime gmtCreate;
    private boolean beDeleted;

    public User() {
    }

    public User(String username, String password, String mobile) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.roleId = 1;
        this.nickname = username;
        this.avatar = "default.png";
        this.signature = "这个人很懒，什么都没写...";
        this.gender = 0;
        this.birthday = LocalDateTime.of(1970, 1, 1, 0, 0);
    }

}
