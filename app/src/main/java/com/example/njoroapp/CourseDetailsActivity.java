package com.example.njoroapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.njoroapp.Models.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CourseDetailsActivity extends AppCompatActivity {

//    private TextView courseID, CourseDescription, courseName;
//    private String productID = "", state = "Normal";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_course_details);
//
//        private void getCourseDetails (String courseID)
//        {
//            DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("courses");
//
//            productsRef.child(courseID).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        Courses courses = dataSnapshot.getValue(Courses.class);
//
//                        courseName.setText(courses.getCname());
//
//                        courseDescription.setText(courses.getDescription());
////                        Picasso.get().load(course.getImage()).into(courseImage);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//
//    }
}