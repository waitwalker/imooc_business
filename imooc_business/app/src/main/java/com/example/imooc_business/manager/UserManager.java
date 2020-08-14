package com.example.imooc_business.manager;

import com.example.imooc_business.model.user.User;

///
/// @name UserManager
/// @description 单例管理用户信息
/// @author liuca
/// @date 2020/8/14
///
public class UserManager {
    // 单例的创建方式2
    private static UserManager mInstance;
    private User mUser;
    public static UserManager getInstance() {
        if (mInstance == null) {
            // 确保单例唯一,class的字节码锁是唯一,每个类对应的字节码是唯一的
            synchronized (UserManager.class) {
                if (mInstance == null) mInstance = new UserManager();
            }
        }
        return mInstance;
    }

    ///
    /// @name saveUser
    /// @description 保存User数据到内存
    /// @author liuca
    /// @date 2020/8/14
    ///
    public void saveUser(User user) {
        mUser = user;
        saveLocal(user);
    }

    ///
    /// @name saveLocal
    /// @description 保存用户数据到本地持久化
    /// @author liuca
    /// @date 2020/8/14
    ///
    private void saveLocal(User user) {

    }

    ///
    /// @name getUser
    /// @description 获取User
    /// @author liuca
    /// @date 2020/8/14
    ///
    public User getUser() {
//        if (mUser == null) {
//            mUser
//        }
        return mUser;
    }

    ///
    /// @name getLocal
    /// @description 从本地获取user
    /// @author liuca
    /// @date 2020/8/14
    ///
    private User getLocal() {
        return null;
    }

    ///
    /// @name hasLogin
    /// @description 用户是否登录过
    /// @author liuca
    /// @date 2020/8/14
    ///
    public boolean hasLogin() {
        return getUser() != null;
    }

    public void removeUser() {
        mUser = null;
        removeLocal();
    }

    private void removeLocal() {

    }
}
