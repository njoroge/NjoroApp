package com.example.njoroapp.ViewPackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.njoroapp.Interface.ItemClickListner;
import com.example.njoroapp.R;

public class CourseViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtCourseName, txtCourseDescription;
    public ImageView imageView;
    public  ItemClickListner listner;


    public CourseViewHolder(View itemView)
    {
        super(itemView);


//        imageView = (ImageView) itemView.findViewById(R.id.course_image);
//        txtCourseName = (TextView) itemView.findViewById(R.id.course_name);
//        txtCourseDescription = (TextView) itemView.findViewById(R.id.course_description);

    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}


