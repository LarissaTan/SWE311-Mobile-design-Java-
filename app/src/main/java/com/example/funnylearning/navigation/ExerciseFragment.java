package com.example.funnylearning.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.recycle.chatbox.adapter_chatbox;
import com.example.funnylearning.recycle.exercise.adapter_exercise;
import com.example.funnylearning.recycle.exercise.model_exercise;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ExerciseFragment extends Fragment {

    private RecyclerView execList;
    private SearchView search;
    private TextView searchHint;
    private final ArrayList<model_exercise> exerciseList = new ArrayList<model_exercise>();

    public ExerciseFragment() {
        // Required empty public constructor
    }

    public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        CartoonsDao dao=new CartoonsDao(view.getContext());
        //打开数据库
        dao.open();

        ArrayList<Cartoons> cartoonData=dao.getAllCartoons();

        execList = (RecyclerView) view.findViewById(R.id.recyclerview_exercise);
        search = view.findViewById(R.id.cartoon_search_bar);
        searchHint = view.findViewById(R.id.cartoon_search_hint);

        //设置SearchView自动缩小为图标
        search.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        //设置该SearchView显示搜索按钮
        search.setSubmitButtonEnabled(true);
        //设置默认提示文字
        search.setQueryHint("Enter words here..");

        //配置监听器
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                //此处添加查询开始后的具体时间和方法
                ArrayList<Cartoons> cartoonData = dao.getKeyCartoons(query);
                exerciseList.clear();

                if(cartoonData == null){
                    cartoonData = dao.getAllCartoons();
                    searchHint.setText("Sorry, we don`t have '" + query + "'");
                    for (int i = 0; i < cartoonData.size(); i++)
                        exerciseList.add(new model_exercise(cartoonData.get(i).Name,cartoonData.get(i).image,cartoonData.get(i).Level));

                    execList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    adapter_exercise customAdapter = new adapter_exercise(exerciseList);
                    execList.setAdapter(customAdapter);
                }else {
                    searchHint.setText(cartoonData.size() + " results for '"  + query + "'");
                    for (int i = 0; i < cartoonData.size(); i++)
                        exerciseList.add(new model_exercise(cartoonData.get(i).Name, cartoonData.get(i).image, cartoonData.get(i).Level));

                    execList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapter_exercise customAdapter = new adapter_exercise(exerciseList);
                    execList.setAdapter(customAdapter);
                }

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        exerciseList.clear();
        System.out.println("message is working");
        for (int i = 0; i < cartoonData.size(); i++)
            exerciseList.add(new model_exercise(cartoonData.get(i).Name,cartoonData.get(i).image,cartoonData.get(i).Level));

        execList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter_exercise customAdapter = new adapter_exercise(exerciseList);
        execList.setAdapter(customAdapter);


        return view;
    }


}