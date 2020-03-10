package com.example.njoroapp.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.njoroapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddCourse extends AppCompatActivity {
    private String  Description,  cname, saveCurrentDate, saveCurrentTime;
    private Button addNewcourseButton ;
    private ImageView InputcourseImage;
    private EditText InputcourseName, InputcourseDescription;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String courseRandomKey, downloadImageUrl;
    private StorageReference courseImagesRef;
    private DatabaseReference coursesRef;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        //CategoryName = getIntent().getExtras().get("category").toString();
        StorageReference courseImagesRef = FirebaseStorage.getInstance().getReference().child("course Images");
        coursesRef = FirebaseDatabase.getInstance().getReference().child("Courses");
        addNewcourseButton = (Button) findViewById(R.id.add_new_course);
        InputcourseImage = (ImageView) findViewById(R.id.select_course_image);
        InputcourseName = (EditText) findViewById(R.id.course_name);
        InputcourseDescription = (EditText) findViewById(R.id.course_description);

        loadingBar = new ProgressDialog(this);


        InputcourseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });


        addNewcourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateProductData();
            }
        });
    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputcourseImage.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData()
    {
        Description = InputcourseDescription.getText().toString();

        cname = InputcourseName.getText().toString();


        if (ImageUri == null)
        {
            Toast.makeText(this, "Course image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please write course description...", Toast.LENGTH_SHORT).show();
        }


        else if (TextUtils.isEmpty(cname))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }



    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New ");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new ");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        courseRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = courseImagesRef.child(ImageUri.getLastPathSegment() + courseRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddCourse.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAddCourse.this, "course Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AdminAddCourse.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });
        
    }
    private void SaveProductInfoToDatabase()
    { //this code gets the following attributes of products as fed by admin and updates database
        HashMap<String, Object> courseMap = new HashMap<>();
        courseMap.put("cid", courseRandomKey);
        courseMap.put("date", saveCurrentDate);
        courseMap.put("time", saveCurrentTime);
        courseMap.put("description", Description);
        courseMap.put("image", downloadImageUrl);
//        courseMap.put("category", CategoryName);
//        courseMap.put("price", Price);
        courseMap.put("cname", cname);

        coursesRef.child(courseRandomKey).updateChildren(courseMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AdminAddCourse.this,
                                    AdminActivity.class); startActivity(intent);

                            loadingBar.dismiss(); // below is comfirmation message for successful add of course
                            Toast.makeText(AdminAddCourse.this, "the course was added successfully..",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss(); // below code displays error message if course is not added
                            String message = task.getException().toString();
                            Toast.makeText(AdminAddCourse.this, "course was NOT added: " + message,
                                    Toast.LENGTH_SHORT).show();
                         }
                    }
                });

    }
}




