package com.example.phonebookmvp;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phonebookmvp.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment implements DetailInterface{
    FragmentDetailBinding binding;
    private DetailPresenter detailPresenter;
    public static final String TAG = DetailFragment.class.getName();

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false);
        detailPresenter = new DetailPresenter(this);
        Bundle bundleReceive = getArguments();
        if (bundleReceive != null){
            Contact contact = (Contact) bundleReceive.get("object_contact");
            if (contact != null){
                binding.tvName.setText(contact.getName());
                binding.tvNumber.setText(contact.getNumber());
            }
        }


        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.getFragment(getFragmentManager());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void getFmSuccess() {
        getFragmentManager().popBackStack();
    }
}