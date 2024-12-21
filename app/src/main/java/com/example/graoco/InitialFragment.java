package com.example.graoco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InitialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitialFragment extends Fragment {

    ArrayList<Coffe> coffes = new ArrayList<Coffe>();
    private RecyclerView rvCoffe;
    private CoffeAdapter coffeAdapter;

    public InitialFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static InitialFragment newInstance() {
        InitialFragment fragment = new InitialFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_initial, container, false); // View inflada

    }

    @Override
    public void onResume() {
        super.onResume();

        rvCoffe = getActivity().findViewById(R.id.rvCoffe);
        coffeAdapter = new CoffeAdapter(coffes);
        RecyclerView.LayoutManager layout =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCoffe.setLayoutManager(layout);
        rvCoffe.setAdapter(coffeAdapter);

        getCoffe();
    }


    private void getCoffe() {
        Call<List<Coffe>> call = RetrofitClient.getInstance().getMyApi().getCoffe();
        call.enqueue(new Callback<List<Coffe>>() {
            @Override
            public void onResponse(Call<List<Coffe>> call, Response<List<Coffe>> response) {
                    coffes.clear();
                    coffes.addAll(response.body());
                    coffeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Coffe>> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na comunicação com o servidor: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("test", "onFailure: "+ t.toString());
            }
        });
    }



}