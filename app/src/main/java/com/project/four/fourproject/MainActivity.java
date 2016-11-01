package com.project.four.fourproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment mHome;
    private Fragment mLocation;
    private Fragment mFriends;
    private Fragment mMe;

    private RadioGroup mRadioGroup;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mFragmentManager = getSupportFragmentManager();

        onCheckedChanged(mRadioGroup,R.id.main_home);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (checkedId){
            case R.id.main_home:
                mHome = new HomeFragment();
                mFragmentTransaction.replace(R.id.main_fragment,mHome);
                mFragmentTransaction.commit();
                break;
            case R.id.main_location:
                mLocation = new LocationFragment();
                mFragmentTransaction.replace(R.id.main_fragment,mLocation);
                mFragmentTransaction.commit();
                break;
            case R.id.main_friends:
                mFriends = new FriendsFragment();
                mFragmentTransaction.replace(R.id.main_fragment,mFriends);
                mFragmentTransaction.commit();
                break;
            case R.id.main_me:
                mMe = new MeFragment();
                mFragmentTransaction.replace(R.id.main_fragment,mMe);
                mFragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
