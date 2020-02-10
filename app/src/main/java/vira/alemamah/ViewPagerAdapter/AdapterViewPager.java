package vira.alemamah.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vira.alemamah.TabFragments.FragEmam;
import vira.alemamah.TabFragments.FragHome;
import vira.alemamah.TabFragments.FragStory;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private  static int COUNT;
    private Fragment fragment;

    public AdapterViewPager(FragmentManager fm,int COUNT) {
        super(fm);
        this.COUNT = COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                fragment = new FragStory();
                break;
            case 1:
                fragment = new FragEmam();
                break;
            case 2:
                fragment = new FragHome();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
