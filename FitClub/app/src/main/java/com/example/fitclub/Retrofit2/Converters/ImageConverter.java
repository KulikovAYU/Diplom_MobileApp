package com.example.fitclub.Retrofit2.Converters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;

public  class ImageConverter {

    //Convert Bitmap to ByteArray:
    public Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        return bmp;
//        ImageView image = (ImageView) findViewById(R.id.imageView1);
//
//        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(),
//                image.getHeight(), false));
    }

    //Convert ByteArray to Bitmap:
    public byte[] BitmapToByteArray(Bitmap bmp)
    {
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);ic_launcher
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}
