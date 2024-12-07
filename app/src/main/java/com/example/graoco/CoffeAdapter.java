package com.example.graoco;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoffeAdapter extends RecyclerView.Adapter<CoffeAdapter.ViewHolder> {
    ArrayList<Coffe> coffes;

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
        Coffe coffe = coffes.get(position);
        holder.tvCoffeName.setText(coffe.name);
        holder.tvCoffeValue.setText(coffe.value);
        holder.tvCoffeQuantity.setText(coffe.quantity);

        // Teste para as informações do fragmento irem para a activity...

        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        PokemonDetail pokemonDetail = PokemonDetail.newInstance(pokemon.getId(), holder.cvPokemon.getCardBackgroundColor().getDefaultColor());
        fragmentTransaction.replace(R.id.fragmentContainerView, pokemonDetail);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        return coffes.size();
    }
}
