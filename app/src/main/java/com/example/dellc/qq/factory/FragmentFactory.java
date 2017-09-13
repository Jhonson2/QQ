package com.example.dellc.qq.factory;

import android.service.carrier.CarrierService;
import android.support.v4.app.Fragment;

import com.example.dellc.qq.R;
import com.example.dellc.qq.ui.fragment.ContactFragment;
import com.example.dellc.qq.ui.fragment.ConversationFragment;
import com.example.dellc.qq.ui.fragment.DynamicFragment;

/**
 * fragmentFactory单例的实现
 * Created by dellc on 2017/9/13.
 */

public class FragmentFactory {
    public static final String TAG = "FragmentFactory";

    private static FragmentFactory sFragmentFactory;
    private Fragment mConversationFragment;
    private Fragment mContactFragment;
    private Fragment mDynamicFragment;

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    public Fragment getFragment(int tabId){
        switch (tabId){
            case R.id.conversation:
                return getConversationFrament();
            case R.id.contact:
                return getContactFrament();
            case R.id.dynamic:
                return getDynamicFrament();
        }
        return null;
    }

    private Fragment getConversationFrament() {

        if(mConversationFragment==null){
            mConversationFragment=new ConversationFragment();
        }
            return mConversationFragment;
    }

    private Fragment getContactFrament() {
        if(mContactFragment==null){
            mContactFragment=new ContactFragment();
        }
        return mContactFragment;
    }

    private Fragment getDynamicFrament() {
        if(mDynamicFragment==null){
            mDynamicFragment=new DynamicFragment();
        }

        return mDynamicFragment;
    }
}
