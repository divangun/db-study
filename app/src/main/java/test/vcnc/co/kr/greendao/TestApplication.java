package test.vcnc.co.kr.greendao;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import test.vcnc.co.kr.greendao.model.DaoMaster;
import test.vcnc.co.kr.greendao.model.DaoSession;

public class TestApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
