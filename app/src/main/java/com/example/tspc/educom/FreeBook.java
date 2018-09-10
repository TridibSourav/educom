package com.example.tspc.educom;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tspc.educom.Model.BookModel;
import com.example.tspc.educom.adapters.BookViewPagerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FreeBook extends Fragment {
    FirebaseDatabase database;
    DatabaseReference myRef;


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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("books").child("free");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot != null) {
                    for (DataSnapshot childDataSnapshot:dataSnapshot.getChildren()){
                        viewUpdate(childDataSnapshot);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    private void viewUpdate(DataSnapshot dataSnapshot) {

        String name= dataSnapshot.child("name").getValue(String.class);
        String writer= dataSnapshot.child("writer").getValue(String.class);
        String rating= String.valueOf(dataSnapshot.child("rating").getValue());
        String image= dataSnapshot.child("image").getValue(String.class);
        String link= dataSnapshot.child("link").getValue(String.class);
        String price= dataSnapshot.child("price").getValue(String.class);

        BookModel bookModel= new BookModel(image,name,writer,price,link,rating);
        bookList.add(bookModel);
        adapter= new BookViewPagerAdapter(getContext(),bookList);
        viewPager.setAdapter(adapter);
    }

}
