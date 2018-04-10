package com.app.selfdive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageResult imageResult;
    private Button buttonGo;
    private EditText editText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.searchText);

        buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editText.getText().toString();

                if(isSearchFieldEmpty(search)){
                    Toast.makeText(MainActivity.this, "Please enter a search keyword!", Toast.LENGTH_SHORT).show();
                } else {
                    ImageRetrievalService imageRetrievalService = new ImageRetrievalService(MainActivity.this);
                    imageRetrievalService.getImages(search);
                }
            }
        });
        
    }

    protected boolean isSearchFieldEmpty(String search) {
        return search == "" || search == null;
    }

    public ImageResult getImageResult() {
        return imageResult;
    }

    public void setImageResult(ImageResult imageResult) {
        this.imageResult = imageResult;
    }
}
