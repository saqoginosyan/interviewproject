package com.example.hp.sargisapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.sargisapp.R;
import com.example.hp.sargisapp.activity.UserInfo;
import com.example.hp.sargisapp.api.Result;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private static final String POSITION_KEY = "key";
    private Context context;
    private List<Result> list = Collections.emptyList();

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserInfo.class);
                intent.putExtra(POSITION_KEY, list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImage;
        private TextView userName;
        private TextView number;
        private TextView email;
        private ImageButton callButton;
        private ImageButton emailButton;

        UserViewHolder(View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_img);
            userName = itemView.findViewById(R.id.user_name);
            number = itemView.findViewById(R.id.user_number);
            email = itemView.findViewById(R.id.user_email);
            callButton = itemView.findViewById(R.id.call_button);
            emailButton = itemView.findViewById(R.id.email_button);
        }

        void bind(final Result result) {
            userName.setText(result.getName().getFirst());
            number.setText(result.getPhone());
            email.setText(result.getEmail());
            Picasso.get().load(result.getPicture().getMedium()).fit().into(userImage);

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + result.getPhone()));
                    context.startActivity(callIntent);
                }
            });

            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + result.getEmail()));
                    context.startActivity(Intent.createChooser(emailIntent, "Send Mail"));
                }
            });
        }
    }
}
