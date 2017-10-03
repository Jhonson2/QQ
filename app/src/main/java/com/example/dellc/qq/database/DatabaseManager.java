package com.example.dellc.qq.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dellc.qq.app.Constant;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建数据库的管理类：初始化 增加 删除 查找等等
 * Created by dellc on 2017/10/3.
 */

public class DatabaseManager {


    public static DatabaseManager sDatabaseManager;
    private DaoSession mDaoSession;

    private DatabaseManager() {
    }

    //创建单例模式
    public static DatabaseManager getInstance() {
        if (sDatabaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (sDatabaseManager == null) {
                    sDatabaseManager = new DatabaseManager();
                }
            }
        }
        return sDatabaseManager;
    }

    //自动生成DaoMaster
    public void initDatabase(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, Constant.Database.DATABASE_NAME, null);
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();
    }

    //save保存联系人
    public void saveContact(Contact contact) {
        ContactDao contactDao = mDaoSession.getContactDao();
        contactDao.save(contact);
    }

    //查询数据库，将所有的联系人查询出来，并且转换成ArrayList的数据集合
    public List<String> queryContact() {
        List<String> contacts = new ArrayList<String>();
        ContactDao contactDao = mDaoSession.getContactDao();
        QueryBuilder<Contact> contactQueryBuilder = contactDao.queryBuilder();
        List<Contact> list = contactQueryBuilder.list();
        for (int i = 0; i < list.size(); i++) {
            contacts.add(list.get(i).getUserName());
        }
        return contacts;
    }
}
