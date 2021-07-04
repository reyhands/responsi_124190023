package com.example.DATASTATISTIKCOVID;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.DATASTATISTIKCOVID.model.*;
import com.example.DATASTATISTIKCOVID.api.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    LinearLayout listdata;
    TextView Judul;
    List<getData.RawCovid> mCovid =  new ArrayList<>();
    List<getData.RawCovid> listcovid ;
    apiInterface mapiInterface;
    Gson gson;
    String displayResponse = "";
   // Type type = new TypeToken<List<GetRepository>>(){}.getType();







    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mapiInterface = apiClient.getClient().create(apiInterface.class);
        refresh();


      return view;
    }

        public void refresh()
        {
            mapiInterface = apiClient.getClient().create(apiInterface.class);

            Call<getData> call = mapiInterface.getResources();

            call.enqueue(new Callback<getData>() {
                @Override
                public void onResponse(Call<getData> call, Response<getData> response) {
                    getData dataCovid= response.body();
                    getData.RawCovid rawcovid = dataCovid.dataraw;
                    System.out.println(response.body());
                    recyclerViewAdapter = new AdapterRecyclerView(getContext(), rawcovid);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

                @Override
                public void onFailure(Call<getData> call, Throwable t) {
                    System.out.println("gagal"); System.out.println(t.getMessage());
                }
            });

        }





}