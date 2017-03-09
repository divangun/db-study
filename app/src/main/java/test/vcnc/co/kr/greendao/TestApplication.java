package test.vcnc.co.kr.greendao;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import test.vcnc.co.kr.greendao.model.MomentsOpenDatabaseHelper;

public class TestApplication extends Application {

    private static TestApplication APPLICATION;

    public MomentsOpenDatabaseHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION = this;

        helper = OpenHelperManager.getHelper(this, MomentsOpenDatabaseHelper.class);
    }

    public MomentsOpenDatabaseHelper getHelper() {
        return helper;
    }

    public static TestApplication getApplication() {
        return APPLICATION;
    }
}
