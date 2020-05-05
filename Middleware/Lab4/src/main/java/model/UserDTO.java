package model;

public class UserDTO {
    private Integer id;
    private String username;
    private String password;

    /**
     * 用户权限等级，其中
     * 1为Read权限
     * 2为Update权限
     * 3为Aggregate权限
     * 比1大则代表拥有Read权限
     */
    private Integer privilege;

    public UserDTO(Integer id, String username, String password, Integer privilege) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
