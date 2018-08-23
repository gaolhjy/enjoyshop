package com.enjoyshop;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import com.enjoyshop.bean.User;
import com.enjoyshop.data.dao.DaoMaster;
import com.enjoyshop.data.dao.DaoSession;
import com.enjoyshop.service.LocationService;
import com.enjoyshop.utils.UserLocalData;
import com.enjoyshop.utils.Utils;
import com.mob.MobApplication;
import com.mob.MobSDK;


/**
 * <pre>
 *     author : 高磊华
 *     e-mail : 984992087@qq.com
 *     time   : 2017/08/05
 *     desc   : 整个app的管理
 *     version: 1.0
 * </pre>
 */


public class EnjoyshopApplication extends MobApplication {

    //    mob信息    app key:201f8a7a91c30      App Secret:  c63ec5c1eeac1f873ec78c1365120431
    //百度地图的 ak   zbqExff1uz8XyUVn5GbyylomCa0rOkmP

    private User user;
    public LocationService locationService;
    public  Vibrator mVibrator;

    private        DaoMaster.DevOpenHelper mHelper;
    private        SQLiteDatabase          db;
    private        DaoMaster               mDaoMaster;
    private static DaoSession              mDaoSession;

    //整个app的上下文
    public static Context sContext;

    private static EnjoyshopApplication mInstance;

    public static EnjoyshopApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, "201f8a7a91c30", "c63ec5c1eeac1f873ec78c1365120431");

        sContext = getApplicationContext();
        initUser();
        Utils.init(this);

        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        setDatabase();

    }


    private void initUser() {
        this.user = UserLocalData.getUser(this);
    }


    public User getUser() {
        return user;
    }

    public void putUser(User user, String token) {
        this.user = user;
        UserLocalData.putUser(this, user);
        UserLocalData.putToken(this, token);
    }

    public void clearUser() {
        this.user = null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);
    }


    public String getToken() {
        return UserLocalData.getToken(this);
    }

    private Intent intent;

    public void putIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void jumpToTargetActivity(Context context) {
        context.startActivity(intent);
        this.intent = null;
    }


    public static EnjoyshopApplication getApplication() {
        return mInstance;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "shop-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
