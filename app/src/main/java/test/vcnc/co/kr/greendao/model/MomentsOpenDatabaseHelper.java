package test.vcnc.co.kr.greendao.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MomentsOpenDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "user";
    private static final int DATABASE_VERSION = 2;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<Moment, String> momentDao;

    private Dao<ImageInfo, String> imageDao;


    public MomentsOpenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ImageInfo.class);
            TableUtils.createTable(connectionSource, Moment.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, ImageInfo.class, false);
            TableUtils.dropTable(connectionSource, Moment.class, false);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Moment, String> getMomentDao() throws SQLException {
        if (momentDao == null) {
            momentDao = getDao(Moment.class);
        }
        return momentDao;
    }

    public Dao<ImageInfo, String> getImageDao() throws SQLException {
        if (imageDao == null) {
            imageDao = getDao(ImageInfo.class);
        }
        return imageDao;
    }
}

