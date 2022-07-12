package com.example.test1;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.place.PostingData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyreviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyreviewFragment newInstance(String param1, String param2) {
        MyreviewFragment fragment = new MyreviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    AppCompatActivity mypageActivity;
    private Retrofit retrofit;
    private RetrofitAPIInterface service;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mypageActivity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_myreview,container,false);

        String token = getArguments().getString("token");
        String Tag = "MyReviewFragment";
        //Log.d("token~~:", token);

        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;

        ArrayList<PostingData> review_list = new ArrayList<>();

        RecyclerView recyclerView = rootview.findViewById(R.id.myreviewrecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mypageActivity);
        recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정

        MyreviewAdapter customAdapter = new MyreviewAdapter(review_list);
        recyclerView.setAdapter(customAdapter); // 어댑터 설정

        HashMap<String, Object> param = new HashMap<>();
        param.put("token", token);
        Call<ArrayList<RankRes>> call_post = service.RankReqFunc("myreview",param);
        call_post.enqueue(new Callback<ArrayList<RankRes>>() {
            @Override
            public void onResponse(Call<ArrayList<RankRes>> call, Response<ArrayList<RankRes>> response) {
                if(response.isSuccessful()){
                    ArrayList<RankRes> result = response.body();
                    Log.v(Tag, "search result : " + result.toString());
                    for(int i = 0; i < result.size(); i++){
                        review_list.add(new PostingData(result.get(i).posting, result.get(i).Posting_date,Integer.parseInt(result.get(i).star),result.get(i).PlaceName) );
                        customAdapter.notifyDataSetChanged();
                    }

                }
                else{
                    Log.v(Tag,"error = " + String.valueOf(response.code()));
                    Toast.makeText(mypageActivity.getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RankRes>> call, Throwable t) {
                Log.v(Tag,"Fail in search");
                Toast.makeText(mypageActivity.getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

            }
        });



        return rootview;
    }
}