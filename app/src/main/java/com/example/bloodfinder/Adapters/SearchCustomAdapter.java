package com.example.bloodfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodfinder.ModelClasses.UserModel;
import com.example.bloodfinder.R;

import java.util.ArrayList;

public class SearchCustomAdapter extends RecyclerView.Adapter<SearchCustomAdapter.Holder> {

    private Context context;
    private ArrayList<UserModel> userModelsList;
    private Holder.onNoteListener mOnNoteListener;

    public SearchCustomAdapter(Context context, ArrayList<UserModel> userModelsList, Holder.onNoteListener onNoteListener) {
        this.context = context;
        this.userModelsList = userModelsList;
        this.mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.user_item_row, parent, false), mOnNoteListener);
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

    public static class Holder extends RecyclerView.ViewHolder {
        TextView name,bloodGroup,phone,address;
        Button call_btn;
        onNoteListener onNoteListener;
        public Holder(@NonNull View itemView, final onNoteListener onNoteListener) {
            super(itemView);
            this.onNoteListener=onNoteListener;
            name=itemView.findViewById(R.id.username_db);
            bloodGroup=itemView.findViewById(R.id.bloodGroup_userItem);
            phone=itemView.findViewById(R.id.phone_userItem);
            address=itemView.findViewById(R.id.address_userItem);
            call_btn=itemView.findViewById(R.id.call_btn);
            call_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNoteListener.onNoteClick(getAdapterPosition());
                }
            });
        }
        public interface onNoteListener{
            void onNoteClick(int position);
        }
    }
}
