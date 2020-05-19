package org.techtown.exper_beob;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    ArrayList<ListData> foodList;
    ListView listView;

    long now;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        // listview 설정
        listView = rootView.findViewById(R.id.listViewHome);
        this.InitializeNameData();
        final MyAdapter myAdapter = new MyAdapter(getActivity(),foodList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),"Item "+position+" Clicked",Toast.LENGTH_SHORT).show();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.ItemSelected(foodList.get(position).getName());
            }
        });

        // title 설정
        now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleData = new SimpleDateFormat("HH");
        String currTime = simpleData.format(mDate);
        TextView timetitle = rootView.findViewById(R.id.timeTitle);
        this.SetTimeTitle(currTime,timetitle);


        return rootView;
    }

    // 임시 foodlist 초기화 , data base로 바꾸어야함
    private void InitializeNameData(){
        foodList = new ArrayList<ListData>();
        for(int i = 0;i<40;++i){
            foodList.add(new ListData("ITEM"+i));
        }
    }


    // 시간대별 텍스트 뷰 설정
    private void SetTimeTitle(String strtime,TextView view){
        int time = Integer.parseInt(strtime);
        String title=null;
        if(4 <= time && time <= 10){
            title = "아침 추천 식단";
        }
        else if(11 <= time && time <= 17){
            title = "점심 추천 식단";
        }
        else if(18 <= time && time <= 23){
            title = "저녁 추천 식단";
        }

        if(title != null){
            view.setText(title);
        }
    }


}
