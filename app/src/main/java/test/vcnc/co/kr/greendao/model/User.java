package test.vcnc.co.kr.greendao.model;


import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

@ObjectMappable
public class User {


    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("count")
    private int tempUsageCount;

    public User(Long id, String name, int tempUsageCount) {
        this.id = id;
        this.name = name;
        this.tempUsageCount = tempUsageCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }
}
