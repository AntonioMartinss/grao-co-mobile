package com.example.graoco;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCoffeDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCoffeDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";

    // TODO: Rename and change types of parameters
    private int id;
    private View fragmentView;
    Button deleteBtn;
    Button editBtn;
    Coffe coffe;
    public FragmentCoffeDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.

     * @return A new instance of fragment FragmentCoffeDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCoffeDetail newInstance(int id) {
        FragmentCoffeDetail fragment = new FragmentCoffeDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);

        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentView = inflater.inflate(R.layout.fragment_coffe_detail, container, false);
        deleteBtn = fragmentView.findViewById(R.id.deleteBtn);
        editBtn = fragmentView.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCoffe();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCoffe();
            }
        });

        return fragmentView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderCoffe();
    }

    private void renderCoffe() {
         Call<List<Coffe>> call = RetrofitClient.getInstance().getMyApi().getCoffeDescription(id);
        call.enqueue(new Callback<List<Coffe>>() {
            @Override
            public void onResponse(Call<List<Coffe>> call, Response<List<Coffe>> response) {
                List<Coffe> coffes = response.body();
                Coffe coffe = coffes.get(0);

                TextView tvName = fragmentView.findViewById(R.id.tvCoffe);
                TextView tvValue = fragmentView.findViewById(R.id.tvValue);
                TextView tvDescription = fragmentView.findViewById(R.id.tvDescription);
                ImageView ivCoffe = fragmentView.findViewById(R.id.ivCoffe);

                tvName.setText(coffe.getName());
                tvValue.setText("R$ "+coffe.getValue());
                tvDescription.setText(coffe.getDescription().toString());


                String urlImage = "http://10.0.2.2/grao-co/" + coffe.getPath();
                Picasso.get().load(urlImage).into(ivCoffe);


            }

            @Override
            public void onFailure(Call<List<Coffe>> call, Throwable t) {
                Log.d("TESTE", t.toString());
            }
        });
    }

    private void deleteCoffe() {
        Call <Coffe> call = RetrofitClient.getInstance().getMyApi().deleteCoffe(id);
        call.enqueue(new Callback<Coffe>() {
            @Override
            public void onResponse(Call<Coffe> call, Response<Coffe> response) {
                if(response.isSuccessful()){
                    coffe = response.body();
                    Toast.makeText(requireContext(), "Sucesso ao deletar. Redirecionando...", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    InitialFragment initialFragment = InitialFragment.newInstance();
                    fragmentTransaction.replace(R.id.fragmentContainerView, initialFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(requireContext(), "Erro deletar...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Coffe> call, Throwable t) {

            }
        });
    }

    private void updateCoffe(){
        TextView etName = fragmentView.findViewById(R.id.etName);
        TextView etDescription = fragmentView.findViewById(R.id.etDescription);
        TextView etValue = fragmentView.findViewById(R.id.etValue);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", etName.getText().toString())
                .addFormDataPart("value", etValue.getText().toString())
                .addFormDataPart("description", etDescription.getText().toString())
                .build();

        Call<Coffe> call = RetrofitClient.getInstance().getMyApi().updateCoffe(id, requestBody);

       call.enqueue(new Callback<Coffe>() {
           @Override
           public void onResponse(Call<Coffe> call, Response<Coffe> response) {
               if(response.isSuccessful()){
                   Toast.makeText(requireContext(), "Café Atualizado", Toast.LENGTH_SHORT).show();


               }else{
                   Toast.makeText(requireContext(), "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<Coffe> call, Throwable t) {
               Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show();
           }
       });

    }

    }