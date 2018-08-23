package com.enjoyshop.data.daodo;

import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.data.dao.User;
import com.enjoyshop.data.dao.UserDao;

import java.util.List;


/**
 * <p>
 *
 * @author :高磊华
 * @date : 2018/08/23
 * desc    : 用户信息 数据库操作
 * </pre>
 */


public class UserDo {

    /**
     * 添加数据
     *
     * @param user
     */
    public static void insertUser(User user) {
        EnjoyshopApplication.getDaoSession().getUserDao().insert(user);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteUser(Long id) {
        EnjoyshopApplication.getDaoSession().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public static void updateUser(User user) {
        EnjoyshopApplication.getDaoSession().getUserDao().update(user);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> queryUser(String phone) {
        return EnjoyshopApplication.getDaoSession().getUserDao().queryBuilder().where
                (UserDao.Properties.Phone.eq(phone)).list();
    }
}
