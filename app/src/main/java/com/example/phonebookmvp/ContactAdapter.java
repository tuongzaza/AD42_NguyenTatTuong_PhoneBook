package com.example.phonebookmvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private List<Contact> contactList;
    private IClickItemListener iClickItemListener;

    public interface IClickItemListener{
        void onClickItemContact(Contact contact);
    }

    public ContactAdapter(List<Contact> contactList, IClickItemListener iClickItemListener) {
        this.contactList = contactList;
        this.iClickItemListener = iClickItemListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if (contact == null){
            return;
        }
        holder.contactname.setText(contact.getName());
        holder.contactnumber.setText(contact.getNumber());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemContact(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactList != null){
            return contactList.size();
        }
        return 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        private TextView contactname;
        private TextView contactnumber;
        private LinearLayout linearLayout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactname = itemView.findViewById(R.id.contactname);
            contactnumber = itemView.findViewById(R.id.contactnumber);
            linearLayout = itemView.findViewById(R.id.ll1);
        }
    }
}
