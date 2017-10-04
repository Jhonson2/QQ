package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.database.DatabaseManager;
import com.example.dellc.qq.event.AddFriendEvent;
import com.example.dellc.qq.model.SearchResultItem;
import com.example.dellc.qq.model.User;
import com.example.dellc.qq.presenter.AddFriendPersenter;
import com.example.dellc.qq.utils.ThreadUtils;
import com.example.dellc.qq.view.AddFreindView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dellc on 2017/9/30.
 */

public class AddFreindPersenterImp implements AddFriendPersenter {

    public AddFreindView mAddFreindView;


    private List<SearchResultItem> mSearchResultItems; //定义一个搜索好友的数据集合

    public AddFreindPersenterImp(AddFreindView addFreindView) {
        mAddFreindView = addFreindView;
        mSearchResultItems = new ArrayList<SearchResultItem>();
        EventBus.getDefault().register(this);
    }

    @Override
    public void searchFreind(String keyword) {
        //使用Bmob查询
        BmobQuery<User> query = new BmobQuery<>();
        //添加查询条件（需要付费）
        //模糊查询：除了自己只外
        query.addWhereContains("username", keyword).
                addWhereNotEqualTo("username", EMClient.getInstance().getCurrentUser());

        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {

                if (e == null) {
                    //异常为空，搜索成功
                    if (list.size() == 0) {
                        mAddFreindView.onSearchEmpty();
                    } else {
                        //1.先查询一下数据库中所有联系人
                        List<String> contacts = DatabaseManager.getInstance().queryContact();

                        for (int i = 0; i < list.size(); i++) {
                            //将user转换SearchResultItem
                            SearchResultItem item = new SearchResultItem();
                            item.userName = list.get(i).getUsername();
                            item.timestamp = list.get(i).getCreatedAt();//用户创建时间

                            //2.到搜索界面，查询数据库的联系人是否已经存在联系人列表
                            item.added = contacts.contains(item.userName);
                            mSearchResultItems.add(item);
                        }

                        mAddFreindView.onSearchSuccess();
                    }
                } else {
                    // //异常存在，搜索失败
                    mAddFreindView.onSearchFailed();
                }
            }
        });
    }

    @Override
    public List<SearchResultItem> getSearchResult() {
        return mSearchResultItems;
    }

    //运用EventBus插件:添加好友操作
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void handleAddFriend(AddFriendEvent event) {

        try {
            //参数为要添加的好友的username和添加理由
            EMClient.getInstance().contactManager().addContact(event.userName, event.reason);
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAddFreindView.onAddFreindSuccess();
                }
            });
        } catch (HyphenateException e) {
            e.printStackTrace();
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAddFreindView.onAddFreindFailed();
                }
            });
        }
    }

    @Override
    public void destroy(){
        EventBus.getDefault().unregister(this);
    }
}
