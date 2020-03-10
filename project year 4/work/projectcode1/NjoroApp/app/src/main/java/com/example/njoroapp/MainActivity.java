package com.example.njoroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener  authStateListener;
    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent(getApplicationContext(), Sign.class));
                    finish();
                }

            }
        };

//        if( firebaseAuth.getInstance().getCurrentUser()!=null){
//            setContentView(R.layout.activity_main);
//        }else{
//            startActivity(new Intent(this,Sign.class));
//        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Units");

        title = new String[]{ "Artificial Intelligence", "Networking", "Cloud Computing", "BlockChain","Animation","Mobile Computing","IoT(Internet of things)"};
        description = new String[]{ "AI is the new Breakthrough in technology", "It is the practice of transporting and exchanging data between nodes over a shared medium of an information system"
                ,"It is on-demand availability of computer system resources, especially data storage and computing power, without direct active management by the user",
        "It is a growing list of records, that are linked using cryptography. Each block contains a cryptographic has of the previous block, a timestamp, and transaction data",
                "Animation is a method in which pictures are manipulated to appear as moving images","Mobile computing is human–computer interaction in which a computer is expected to be transported during normal usage, which allows for transmission of data, voice and video",
        "The internet of things, or IoT, is a system of interrelated computing devices, mechanical and digital machines, objects, animals or people that are provided with unique identifiers (UIDs) and the ability to transfer data over a network without requiring human-to-human or human-to-computer interaction"};
        icon = new int[]{R.drawable.ai, R.drawable.net, R.drawable.cld, R.drawable.bc, R.drawable.anim, R.drawable.mbil, R.drawable.iot};

        listView = findViewById(R.id.listView);

        for (int i = 0; i < title.length; i++) {
            Model model = new Model(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }
        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);
        //bind the adapter to the listview
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.log_out:
                icon = new int[]{R.drawable.logout};
                option_logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void option_logout() {
        icon = new int[]{R.drawable.logout};
        firebaseAuth.getInstance().signOut();
        finish();
        startActivity( new Intent(getApplicationContext(),Sign.class)) ;


    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
