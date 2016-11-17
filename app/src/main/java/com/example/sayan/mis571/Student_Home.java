package com.example.sayan.mis571;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sayan.mis571.fragments.acc_details;
import com.example.sayan.mis571.fragments.book_conf_room;
import com.example.sayan.mis571.fragments.search_class;
import com.example.sayan.mis571.fragments.student_home_fragment;

import java.util.ArrayList;

public class Student_Home extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerholder);
        navList=(ListView)findViewById(R.id.navlist);
        ArrayList<String> navArray= new ArrayList<String>();
        navArray.add("Home");
        navArray.add("Search Class");
        navArray.add("Book Conference Room");
        navArray.add("Account Details");
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentManager = getSupportFragmentManager();

        loadSelection(0);


    }

    private void loadSelection(int i){
        navList.setItemChecked(i,true);

        switch (i){
            case 0:
                student_home_fragment st_home = new student_home_fragment();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, st_home);
                fragmentTransaction.commit();
                break;
            case 1:
                search_class search_cls = new search_class();
                search_cls.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, search_cls);
                fragmentTransaction.commit();
                break;
            case 2:
                book_conf_room book_conf = new book_conf_room();
                book_conf.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, book_conf);
                fragmentTransaction.commit();
                break;
            case 3:
                acc_details acc_det = new acc_details();
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, acc_det);
                fragmentTransaction.commit();
                break;
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== android.R.id.home){
            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }else{
                drawerLayout.openDrawer(navList);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(navList);
    }
}
