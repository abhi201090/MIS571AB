package com.example.sayan.mis571;

import android.content.Intent;
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
import com.example.sayan.mis571.fragments.acc_details_prof;
import com.example.sayan.mis571.fragments.book_class;
import com.example.sayan.mis571.fragments.book_conf_room;
import com.example.sayan.mis571.fragments.prof_home_fragment;

public class Prof_Home extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private acc_details acc_det;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_home);
        //
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerholder);
        navList=(ListView)findViewById(R.id.navlist);
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        String [] navtitle = getResources().getStringArray(R.array.Prof_home_side);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navtitle);
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
                prof_home_fragment prof_home = new prof_home_fragment();
                prof_home.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, prof_home);
                fragmentTransaction.commit();
                break;
            case 1:
                book_class book_cls = new book_class();
                book_cls.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, book_cls);
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
                acc_details_prof acc_details_prof = new acc_details_prof();
                acc_details_prof.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, acc_details_prof);
                fragmentTransaction.commit();
                break;
            case 4:
                acc_det = new acc_details();
                acc_det.SetUserID(getIntent().getExtras().getInt("UserID"));
                fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, acc_det);
                fragmentTransaction.commit();
                break;
            case 5:
                //SignOut
                if(acc_det!=null){
                    acc_det.ClearData();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
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