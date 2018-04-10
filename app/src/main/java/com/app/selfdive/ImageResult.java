package com.app.selfdive;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stephen on 4/9/2018.
 */

public class ImageResult implements Serializable {
    private ArrayList<Image> images;

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
