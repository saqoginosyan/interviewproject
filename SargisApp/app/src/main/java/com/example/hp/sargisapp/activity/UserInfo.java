package com.example.hp.sargisapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.hp.sargisapp.R;
import com.example.hp.sargisapp.api.Result;
import com.squareup.picasso.Picasso;

public class UserInfo extends AppCompatActivity {

    private static final String POSITION_KEY = "key";
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageView userInfoImage = findViewById(R.id.user_info_img);
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        result = (Result) getIntent().getSerializableExtra(POSITION_KEY);
        collapsingToolbarLayout.setTitle(result.getName().getFirst());
        Picasso.get().load(result.getPicture().getLarge()).fit().into(userInfoImage);
    }

    public void call(View v) {
        final Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + result.getPhone()));
        startActivity(callIntent);
    }

    public void sendEmail(View v) {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + result.getEmail()));
        startActivity(Intent.createChooser(emailIntent, "Send Mail"));
    }
}
