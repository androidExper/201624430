package org.techtown.exper_beob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    AnalysisFragment analysisFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 하단 탭 Fragment
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        analysisFragment = new AnalysisFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.tabhome:
                                Toast.makeText(getApplicationContext(),"home 탭",Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                                return true;

                            case R.id.tabsearch:
                                Toast.makeText(getApplicationContext(),"search 탭",Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                                return true;

                            case R.id.tabanlaysis:
                                Toast.makeText(getApplicationContext(),"anlaysis 탭",Toast.LENGTH_LONG).show();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,analysisFragment).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

    // ListItem Touch 되었을 경우 , 액티비티 전환
    public void ItemSelected(String itemName){
        Toast.makeText(this,itemName+" Clicked",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),ListItemInfo.class);
        intent.putExtra("itemName",itemName);
        startActivityForResult(intent,101);
    }

}
