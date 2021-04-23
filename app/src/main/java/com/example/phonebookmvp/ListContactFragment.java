package com.example.phonebookmvp;

import android.content.ContentResolver;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebookmvp.databinding.FragmentListContactBinding;

import java.util.ArrayList;
import java.util.List;

public class ListContactFragment extends Fragment implements ListContactInterface {
    FragmentListContactBinding binding;
    private MainActivity mMainActivity;
    private ListContactPresenter listContactPresenter;
    List<Contact> list = new ArrayList<>();


    public ListContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_contact, container, false);
        mMainActivity = (MainActivity) getActivity();
        listContactPresenter = new ListContactPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        binding.rcvContact.setLayoutManager(linearLayoutManager);
        if (list.isEmpty()) {
            getDataBook();
        }
        ContactAdapter contactAdapter = new ContactAdapter(list, new ContactAdapter.IClickItemListener() {
            @Override
            public void onClickItemContact(Contact contact) {
                mMainActivity.goToDetailFragment(contact);
            }
        });
        binding.rcvContact.setAdapter(contactAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mMainActivity, DividerItemDecoration.VERTICAL);
        binding.rcvContact.addItemDecoration(itemDecoration);

        return binding.getRoot();
    }

    private void getDataBook() {
        ContentResolver contentResolver = mMainActivity.getContentResolver();
        listContactPresenter.laydanhba(list,contentResolver);
    }


    @Override
    public void listContactBook(List<Contact> contacts) {
        if (list.isEmpty()) {
            list.addAll(contacts);
        }
    }
}