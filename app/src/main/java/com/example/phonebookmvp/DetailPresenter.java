package com.example.phonebookmvp;

import androidx.fragment.app.FragmentManager;

public class DetailPresenter {
    private DetailInterface detailInterface;

    public DetailPresenter(DetailInterface detailInterface) {
        this.detailInterface = detailInterface;
    }

    public void getFragment(FragmentManager fragmentManager) {
        if (fragmentManager != null){
            detailInterface.getFmSuccess();
        }
    }
}
