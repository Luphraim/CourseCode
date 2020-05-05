package model;

public class AlumniDTO {

    private Integer id;
    private String testColumn;

    public AlumniDTO(Integer id, String testColumn) {
        this.id = id;
        this.testColumn = testColumn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestColumn() {
        return testColumn;
    }

    public void setTestColumn(String testColumn) {
        this.testColumn = testColumn;
    }

    @Override
    public String toString() {
        return "AlumniDTO{" +
                "id=" + id +
                ", testColumn='" + testColumn + '\'' +
                '}';
    }
}