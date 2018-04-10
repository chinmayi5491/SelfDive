package com.app.selfdive;

import java.util.ArrayList;

/**
 * Created by Stephen on 4/9/2018.
 * "images": [
 {
 "id": "75405994",
 "asset_family": "creative",
 "caption": null,
 "collection_code": "DV",
 "collection_id": 13,
 "collection_name": "DigitalVision",
 "display_sizes": [
 {
 "is_watermarked": false,
 "name": "thumb",
 "uri": "https://media.gettyimages.com/photos/person-holding-bunch-of-flowers-picture-id75405994?b=1&k=6&m=75405994&s=170x170&h=dHPmtASyesTwquI1iuG-5jbUTcU1s2Ory_Ppe87yCDY="
 }
 ],
 "license_model": "royaltyfree",
 "max_dimensions": {
 "height": 5700,
 "width": 3802
 },
 "title": "Person holding bunch of flowers"
 },
 */

public class Image {
    private String id;
    private String title;
    private ArrayList<DisplaySize> display_sizes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<DisplaySize> getDisplay_sizes() {
        return display_sizes;
    }

    public void setDisplay_sizes(ArrayList<DisplaySize> display_sizes) {
        this.display_sizes = display_sizes;
    }
}
