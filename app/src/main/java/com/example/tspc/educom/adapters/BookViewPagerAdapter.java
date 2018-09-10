package com.example.tspc.educom.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tspc.educom.Model.BookModel;
import com.example.tspc.educom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cr4ck3r on 8/30/18.
 * Copyright (c) 2018
 */
public class BookViewPagerAdapter extends PagerAdapter {

    Context mContext;
    //LayoutInflater layoutInflater;
    List<BookModel> bookList;


    public BookViewPagerAdapter(Context mContext, List<BookModel> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.book_item_layout,container,false);

        ImageView img =(ImageView)v.findViewById(R.id.book_pic);
        TextView name= v.findViewById(R.id.book_name);
        TextView writer= v.findViewById(R.id.book_writer);
        TextView price= v.findViewById(R.id.book_price);
        RatingBar ratingBar= v.findViewById(R.id.rating);
        ratingBar.setVisibility(View.GONE);


        if(bookList!=null){
            BookModel book= bookList.get(position);
            Picasso.get().load(book.getImage()).into(img);
            name.setText(book.getBName());
            writer.setText(book.getWName());
            price.setText(book.getPrice());
            /*Double rating=Double.parseDouble(book.getRating());
            int star=(int) Math.round(rating);
            ratingBar.setNumStars(star);*/

        }else{
            img.setImageResource(R.drawable.images);

        }

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        container.refreshDrawableState();
    }
}
