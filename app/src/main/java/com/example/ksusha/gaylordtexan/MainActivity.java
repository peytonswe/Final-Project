package com.example.ksusha.gaylordtexan;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image1;
    private int[] imageArray;
    private int currentIndex;
    private int startIndex;
    private int endIndex;

    private String phone_num = "tel:18177781000";
    private String site_name = "http://www.marriott.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.gt_logo_bw);

        image1 = (ImageView) findViewById(R.id.hotel_img);
        imageArray = new int[6];
        imageArray[0] = R.drawable.grapevine_hotel;
        imageArray[1] = R.drawable.one;
        imageArray[2] = R.drawable.two;
        imageArray[3] = R.drawable.three;
        imageArray[4] = R.drawable.four;
        imageArray[5] = R.drawable.five;

        startIndex = 0;
        endIndex = 5;
        nextImage();

        TextView textAddress = (TextView) findViewById(R.id.text_address);
        TextView textPhoneNumber = (TextView) findViewById(R.id.text_phone_number);
        TextView textSite = (TextView) findViewById(R.id.text_site);

        textAddress.setOnClickListener(this);
        textPhoneNumber.setOnClickListener(this);
        textSite.setOnClickListener(this);
    }

    public void nextImage() {
        image1.setImageResource(imageArray[currentIndex]);;
        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.anim_main);
        image1.startAnimation(rotateImage);
        currentIndex++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentIndex > endIndex){
                    currentIndex--;
                    previousImage();
                }else{
                    nextImage();
                }

            }
        },1000);
    }

    public void previousImage() {
        image1.setImageResource(imageArray[currentIndex]);
        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.anim_main);
        image1.startAnimation(rotateImage);
        currentIndex--;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentIndex < startIndex) {
                    currentIndex++;
                    nextImage();
                } else {
                    previousImage();
                }
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_address:
                String strUri = "http://maps.google.com/maps?q=loc:" + 32.955574 + "," + (-97.064209) + "," + " (" + "Gaylord Texan Resort &amp; Convention Center" + ")";
                Intent locIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                locIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(locIntent);
                break;
            case R.id.text_phone_number:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse(phone_num));
                startActivity(phoneIntent);
                break;
            case R.id.text_site:
                Intent siteIntent = new Intent();
                siteIntent.setAction(Intent.ACTION_VIEW);
                siteIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                siteIntent.setData(Uri.parse(site_name));
                startActivity(siteIntent);
                break;
        }

    }

}
