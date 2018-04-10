package com.app.selfdive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;

public class ImageDisplay extends AppCompatActivity {

    private ImageView imageView;
    private ArrayList<String> imageUrls;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(ImageRetrievalService.TOKEN_IMAGE_RESULT_INTENT_KEY);
        ImageResult imageResult = (ImageResult) bundle.getSerializable(ImageRetrievalService.TOKEN_IMAGE_RESULT_BUNDLE_KEY);

        String url = imageResult.getImages().get(0).getDisplay_sizes().get(0).getUri();

        imageUrls = new ArrayList<>();
        for(Image image : imageResult.getImages()) {
            for(DisplaySize displaySize : image.getDisplay_sizes()){
                imageUrls.add(displaySize.getUri());
            }
        }

        imageView = findViewById(R.id.gettyImage);
        Picasso.with(this).load(imageUrls.get(0)).into(imageView);
        imageView.setOnTouchListener(new OnSwipeTouchListener(ImageDisplay.this) {
            int index = 0;
            public void onSwipeRight() {
                if(index != 0) {
                    index--;
                    Picasso.with(ImageDisplay.this).load(imageUrls.get(index)).into(imageView);
                } else {
                    index = imageUrls.size()-1;
                    Picasso.with(ImageDisplay.this).load(imageUrls.get(index)).into(imageView);
                }
            }

            public void onSwipeLeft() {
                if(index != imageUrls.size()-1){
                    index++;
                    Picasso.with(ImageDisplay.this).load(imageUrls.get(index)).into(imageView);
                } else {
                    index = 0;
                    Picasso.with(ImageDisplay.this).load(imageUrls.get(index)).into(imageView);
                }

            }

        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUrls = null;
                Intent intent = new Intent(ImageDisplay.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ImageDisplay.this.startActivity(intent);

            }
        });
    }
}
