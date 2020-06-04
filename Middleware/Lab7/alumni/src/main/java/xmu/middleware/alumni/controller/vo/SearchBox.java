package xmu.middleware.alumni.controller.vo;

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
public class SearchBox {
    private String searchName;
    private String searchText;

    public SearchBox(String searchName, String searchText) {
        this.searchName = searchName;
        this.searchText = searchText;
    }
}
