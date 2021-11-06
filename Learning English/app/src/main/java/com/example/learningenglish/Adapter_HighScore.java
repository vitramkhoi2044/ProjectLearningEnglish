package com.example.learningenglish;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class Adapter_HighScore extends FragmentStatePagerAdapter {

    private String []listTab = {"English Quiz","Correct Word"};
    private HighScore_EnglishQuiz mHSEQ;
    private HighScore_CorrectWord mHSCW;

    public Adapter_HighScore(@NonNull FragmentManager fm) {
        super(fm);
        mHSEQ = new HighScore_EnglishQuiz();
        mHSCW = new HighScore_CorrectWord();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return mHSEQ;
        else
            return mHSCW;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
