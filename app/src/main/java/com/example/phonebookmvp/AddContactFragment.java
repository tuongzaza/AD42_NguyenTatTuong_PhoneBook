package com.example.phonebookmvp;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phonebookmvp.databinding.FragmentAddContactBinding;


public class AddContactFragment extends Fragment implements AddInterface {
    FragmentAddContactBinding binding;
    private MainActivity mMainActivity;
    private AddPresenter addPresenter;

    public AddContactFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_contact, container, false);
        mMainActivity = (MainActivity) getActivity();
        addPresenter = new AddPresenter(this);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addname = binding.etName.getText().toString();
                String addnumber = binding.etNumber.getText().toString();

                addPresenter.saveContact(addname,addnumber,mMainActivity);

            }
        });

        return binding.getRoot();
    }

    @Override
    public void getSuccess() {
        Toast.makeText(mMainActivity.getApplicationContext(),"New contact has been added, go back to previous page to see it in contacts list." , Toast.LENGTH_LONG).show();

        FragmentTransaction fragmentTransaction = mMainActivity.getSupportFragmentManager().beginTransaction();
        ListContactFragment listContactFragment = new ListContactFragment();
        fragmentTransaction.replace(R.id.content_frame, listContactFragment);
        fragmentTransaction.commit();
    }
}