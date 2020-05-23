package com.example.myproject.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myproject.Fragment.FragmentFlim;
import com.example.myproject.Fragment.FragmentListFlim;

public class TabLayoutAdapter extends FragmentStatePagerAdapter{

    private String[] lisTab={"PHIM ĐANG CHIẾU","ĐẶT VÉ"};
    private FragmentFlim fFragemnt;
    private FragmentListFlim lFragment;

    public TabLayoutAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fFragemnt=new FragmentFlim();
        lFragment=new FragmentListFlim();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return fFragemnt;
        }else if(position==1){
            return lFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return lisTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lisTab[position];
    }
}
