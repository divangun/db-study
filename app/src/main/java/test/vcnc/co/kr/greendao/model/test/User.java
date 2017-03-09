package test.vcnc.co.kr.greendao.model.test;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User {

    @DatabaseField
    private String id;

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Company company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
