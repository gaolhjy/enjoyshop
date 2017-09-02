package com.cniao;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;

import com.cniao.bean.User;
import com.cniao.greendao.DaoMaster;
import com.cniao.greendao.DaoSession;
import com.cniao.service.LocationService;
import com.cniao.utils.UserLocalData;
import com.cniao.utils.Utils;
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


public class CNiaoApplication extends MobApplication {

    //    mob信息    app key:201f8a7a91c30      App Secret:  c63ec5c1eeac1f873ec78c1365120431

    // ping ++    Ping++ 系统中标识你的应用标识     app_mjTmHS94izPOSWLK

    //百度地图的 ak   zbqExff1uz8XyUVn5GbyylomCa0rOkmP

    private User user;

    public         LocationService locationService;
    public         Vibrator        mVibrator;
    private static DaoSession      daoSession;

    //整个app的上下文
    public static Context sContext;

    private static CNiaoApplication mInstance;


    public static CNiaoApplication getInstance() {
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
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        //配置数据库
        setupDatabase();

    }

    private void setupDatabase() {

        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "search.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
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


    public static CNiaoApplication getApplication() {
        return mInstance;
    }

}
