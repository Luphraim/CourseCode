package xmu.middleware.c3p0.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Ad对象
 *
 * @author Jason
 */
public class Ad {

    private Integer id;
    private String name;
    private String link;
    private String url;
    private boolean position;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean enabled;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private boolean deleted;

    public Ad(Integer id, String name, String link, String url, boolean position, String content, LocalDateTime startTime, LocalDateTime endTime, boolean enabled, LocalDateTime addTime, LocalDateTime updateTime, boolean deleted) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.url = url;
        this.position = position;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enabled = enabled;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public Ad(ResultSet resultSet) throws SQLException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        id = resultSet.getInt(1);
        name = resultSet.getString(2);
        link = resultSet.getString(3);
        url = resultSet.getString(4);
        position = resultSet.getBoolean(5);
        content = resultSet.getString(6);
        startTime = LocalDateTime.parse(resultSet.getString(7),df) ;
        endTime = LocalDateTime.parse(resultSet.getString(8),df);
        enabled = resultSet.getBoolean(9);
        addTime = LocalDateTime.parse(resultSet.getString(10),df);
        updateTime = LocalDateTime.parse(resultSet.getString(11),df);
        deleted = resultSet.getBoolean(12);
    }

    public Ad() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPosition() {
        return position;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", url='" + url + '\'' +
                ", position=" + position +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", enabled=" + enabled +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
