package com.example.tspc.educom;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tspc.educom.Model.BookModel;
import com.example.tspc.educom.adapters.BookViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FreeBook extends Fragment {

    ViewPager viewPager;
    BookViewPagerAdapter adapter;

    List<BookModel> bookList;
    BookModel bookModel;


    public FreeBook() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.activity_free_book, container, false);

        viewPager=v.findViewById(R.id.pager2);
        bookList= new ArrayList<>();

        for (int i=0;i<5;i++){
           bookModel=new BookModel("Book "+i,"Book Name "+i,"Writer Name"+i,"Price "+20+i);
            bookList.add(bookModel);
        }
        adapter= new BookViewPagerAdapter(getContext(),bookList);
        viewPager.setAdapter(adapter);
        return v;
    }

}
