package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.model.ContactItem;
import com.example.dellc.qq.presenter.ContactPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.ContactView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dellc on 2017/9/16.
 */

public class ContactPersenterImpl  implements ContactPersenter{
    private ContactView mContactView;

    private List<ContactItem> mContactItem;



    public ContactPersenterImpl(ContactView contactView){
        mContactView=contactView;
        mContactItem=new ArrayList<ContactItem>();
    }
    @Override
    public void loadContacts() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //没有异常，加载成功
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    //将用户列表转换为List<ContactItem> mContactItem
                    for(int i=0;i<usernames.size();i++){
                        ContactItem item=new ContactItem();//创建ContactItem
                        item.setUserName(usernames.get(i));

                        //判断和上一个item的首字符是否一致，一致：隐藏首字符
                        if(i>0 && item.getFristLetter().equals(mContactItem.get(i-1).getFristLetter())){
                                item.showFirstLetter=false;
                        }else {
                            item.showFirstLetter=true;
                        }

                        mContactItem.add(item);

                    }
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        //加载成功，通知View层、
                        public void run() {
                            mContactView.onLoadContactsSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //加载失败，通知View层
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactsFailed();
                        }
                    });
                }
            }
        });

    }

    @Override
    public List<ContactItem> getContacts() {
        return mContactItem;
    }

    @Override
    public void refreshContacts() {
        //清空原来数据
        mContactItem.clear();
        // 重新再次加载
        loadContacts();

    }
}
