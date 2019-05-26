package com.example.healthtracker;

import android.content.Context;
import android.util.AttributeSet;



import com.synnapps.carouselview.CarouselView;


public class CustomCarouselView extends CarouselView {

    private static int[] images;
    private static int[] captions;
    private static int image;
    private static int caption;

    public CustomCarouselView(Context context) {
        super(context);
    }

    public CustomCarouselView(Context context, AttributeSet set){
        super(context, set);
    }

    public int[] getImages() {
        return images;
    }

    public static void setImages(int[] images) {
        CustomCarouselView.images = images;
    }

    public static void setImage(int image){
        CustomCarouselView.image = image;
    }

    public int[] getCaptions() {
        return captions;
    }

    public static void setCaptions(int[] captions) {
        CustomCarouselView.captions = captions;
    }

    public static void setCaption(int Caption){
        CustomCarouselView.caption = caption;
    }
}
