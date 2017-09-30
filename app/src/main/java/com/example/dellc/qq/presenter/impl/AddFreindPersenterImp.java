package com.example.dellc.qq.presenter.impl;

import com.example.dellc.qq.model.SearchResultItem;
import com.example.dellc.qq.model.User;
import com.example.dellc.qq.presenter.AddFriendPersenter;
import com.example.dellc.qq.view.AddFreindView;
import com.hyphenate.chat.EMClient;

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
        mSearchResultItems=new ArrayList<SearchResultItem>();
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
                        for(int i=0;i<list.size();i++){
                            //将user转换SearchResultItem
                            SearchResultItem item=new SearchResultItem();
                            item.userName=list.get(i).getUsername();
                            item.timestamp=list.get(i).getCreatedAt();//用户创建时间
                            item.added=false;

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
}
