package com.example.bloodfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;

import java.util.ArrayList;

public class SearchCustomAdapter extends RecyclerView.Adapter<SearchCustomAdapter.Holder> {

    private Context context;
    private ArrayList<UserModel> userModelsList;

    public SearchCustomAdapter(Context context, ArrayList<UserModel> userModelsList) {
        this.context = context;
        this.userModelsList = userModelsList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.user_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(userModelsList.get(position).getUsername());
        holder.bloodGroup.setText(userModelsList.get(position).getBloodGroup());
        holder.phone.setText(userModelsList.get(position).getPhone());
        holder.address.setText(userModelsList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userModelsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,bloodGroup,phone,address;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.username_db);
            bloodGroup=itemView.findViewById(R.id.bloodGroup_userItem);
            phone=itemView.findViewById(R.id.phone_userItem);
            address=itemView.findViewById(R.id.address_userItem);
        }
    }
}
