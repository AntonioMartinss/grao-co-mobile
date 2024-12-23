package com.example.graoco;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoffeAdapter extends RecyclerView.Adapter<CoffeAdapter.ViewHolder> {
    ArrayList<Coffe> coffes;
    Context context;
    public CoffeAdapter(ArrayList<Coffe> coffes) {
        this.coffes = coffes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvCoffeName;
        final TextView tvCoffeValue;
        final TextView tvCoffeQuantity;
        final ImageView coffeImage;

        public ViewHolder(View view) {
            super(view);
            tvCoffeName = (TextView) view.findViewById(R.id.tvCoffeName);
            tvCoffeValue = (TextView) view.findViewById(R.id.tvCoffeValue);
            tvCoffeQuantity = (TextView) view.findViewById(R.id.tvCoffeQuantity);
            coffeImage = (ImageView) view.findViewById(R.id.coffeImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.tvCoffeName.getContext();
        Coffe coffe = coffes.get(position);
        holder.tvCoffeName.setText(coffe.name);
        holder.tvCoffeValue.setText("R$ " + coffe.value);
        holder.tvCoffeQuantity.setText( coffe.quantity + "- und");
        String urlImage = "http://10.0.2.2/grao-co/"+coffe.getPath();
        Picasso.get().load(urlImage).into(holder.coffeImage);

        holder.coffeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    FragmentCoffeDetail fragmentCoffeDetail = FragmentCoffeDetail.newInstance(coffe.getId());
                    fragmentTransaction.replace(R.id.fragmentContainerView, fragmentCoffeDetail);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
        });

    }



    @Override
    public int getItemCount() {
        return coffes.size();
    }


}
