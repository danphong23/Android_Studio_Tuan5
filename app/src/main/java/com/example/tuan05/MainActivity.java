package com.example.tuan05;

import static com.example.tuan05.info_Fragment.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements MainCallbacks{
    FragmentTransaction ft;
    info_Fragment infoFragment;
    item_list_Fragment listFragment;

    String in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        listFragment = item_list_Fragment.newInstance("list","hello");
        ft.replace(R.id.main_holder_list, listFragment);
        ft.commit();
// create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        infoFragment = info_Fragment.newInstance("info", "hello");
        ft.replace(R.id.main_holder_infor, infoFragment);
        ft.commit();
    }   // MainCallback implementation (receiving messages coming from Fragments)

    @Override
    public void onMsgFromFragIToMain(String sender, String in) {
        if (sender.equals("INFOR-FRAG")) {
            try { // forward blue-data to redFragment using its callback method
                listFragment.onMsgFromMainToFragmentL(in);
            }
            catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

    }

    @Override
    public void onMsgFromFragLToMain(String sender, String[] data) {
        if (sender.equals("LIST-ITEM-FRAG")) {

            try { // forward blue-data to redFragment using its callback method
                infoFragment.onMsgFromMainToFragmentI(data);
            }
            catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

    }
}