package com.example.dellc.qq.presenter;

import com.example.dellc.qq.model.SearchResultItem;

import java.util.List;

/**
 * Created by dellc on 2017/9/30.
 */

public interface AddFriendPersenter {

    void searchFreind(String keyword);

    List<SearchResultItem> getSearchResult();
}
