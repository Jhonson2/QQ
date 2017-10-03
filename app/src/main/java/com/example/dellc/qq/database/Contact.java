package com.example.dellc.qq.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;


/**联系人的数据库
 * Created by dellc on 2017/10/1.
 */
@Entity
public class Contact {

    public Long Id;

    public String userName;

    @Generated(hash = 1926081658)
    public Contact(Long Id, String userName) {
        this.Id = Id;
        this.userName = userName;
    }

    @Generated(hash = 672515148)
    public Contact() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
