package com.example.nix.scheduleapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nix on 24.08.2016.
 */
public class SubjectsOfDayFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    public static int int_items = 2;
    public final static String DAY  = "DAY";
    public int i;
    private Bundle mBundle;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            i = bundle.getInt(MainActivity.DATA, 0);
        }
        mBundle = new Bundle();
        mBundle.putInt(DAY, i);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.tab_layout, container, false);
        mTabLayout = (TabLayout) v.findViewById(R.id.tabs);
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });
        return v;
    }
    private class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position){
            Fragment Even = new EvenSubjects();
            Fragment Odd = new OddSubjects();
            Even.setArguments(mBundle);
            Odd.setArguments(mBundle);
            switch (position){
                case 0 : return Odd;
                case 1 : return Even;
                default : return Odd;
            }
        }
        @Override
        public int getCount(){
            return int_items;
        }
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0 :
                    return "Нечётная неделя";
                case 1 :
                    return "Чётная неделя";
            }
            return null;
        }
    }
}
