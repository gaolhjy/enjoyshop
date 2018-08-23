package com.enjoyshop.data.daodo;

import com.enjoyshop.EnjoyshopApplication;
import com.enjoyshop.data.dao.Address;
import com.enjoyshop.data.dao.AddressDao;

import java.util.List;


/**
 * <p>
 *
 * @author :高磊华
 * @date : 2018/08/23
 * desc    : 用户地址 数据库操作
 * </pre>
 */


public class AddressDo {

    /**
     * 添加数据
     *
     * @param address
     */
    public static void insertAddress(Address address) {
        EnjoyshopApplication.getDaoSession().getAddressDao().insert(address);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteAddress(Long id) {
        EnjoyshopApplication.getDaoSession().getAddressDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param address
     */
    public static void updateAddress(Address address) {
        EnjoyshopApplication.getDaoSession().getAddressDao().update(address);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Address> queryAddress(Long userId) {
        return EnjoyshopApplication.getDaoSession().getAddressDao().queryBuilder().where
                (AddressDao.Properties.UserId.eq(userId)).list();
    }
}
