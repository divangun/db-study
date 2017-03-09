package test.vcnc.co.kr.greendao.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Moment")
public class Moment extends BaseDaoEnabled{

    @DatabaseField(id = true, canBeNull = false)
    private String id;

    @DatabaseField
    private Date momentDate;

    @DatabaseField
    private String author;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ImageInfo imageInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMomentDate() {
        return momentDate;
    }

    public void setMomentDate(Date momentDate) {
        this.momentDate = momentDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }
}
