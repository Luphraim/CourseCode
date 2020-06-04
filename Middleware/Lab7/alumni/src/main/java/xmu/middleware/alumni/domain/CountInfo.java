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
public class CountInfo {
    private Integer count;
    private String col;

    public CountInfo(Integer count, String col) {
        this.count = count;
        this.col = col;
    }
}
