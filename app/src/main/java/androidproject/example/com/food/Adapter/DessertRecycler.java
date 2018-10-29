package androidproject.example.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


import androidproject.example.com.food.POJO.Dessert;
import androidproject.example.com.food.R;

public class DessertRecycler extends RecyclerView.Adapter<DessertRecycler.BeneficiaryViewHolder>  {

    private ArrayList<Dessert> listDessert;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Dessert> mFilteredList;


    public DessertRecycler(ArrayList<Dessert> listDessert, Context mContext) {
        this.listDessert = listDessert;
        this.mContext = mContext;
        this.mFilteredList = listDessert;


    }

    public class BeneficiaryViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView date_dessert;
        public AppCompatTextView location_dessert;
        public AppCompatTextView item1_dessert;
        public AppCompatTextView item2_dessert;
        public AppCompatTextView item3_dessert;


        public BeneficiaryViewHolder(View view) {
            super(view);
            date_dessert=(AppCompatTextView)view.findViewById(R.id.date_dessert);
            location_dessert = (AppCompatTextView) view.findViewById(R.id.location_dessert);
            item1_dessert = (AppCompatTextView) view.findViewById(R.id.item1_dessert);
            item2_dessert = (AppCompatTextView) view.findViewById(R.id.item2_dessert);
            item3_dessert = (AppCompatTextView) view.findViewById(R.id.item3_dessert);

        }


    }




    @Override
    public BeneficiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dessert_recycler, parent, false);

        return new BeneficiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BeneficiaryViewHolder holder, int position) {
        holder.date_dessert.setText(listDessert.get(position).getDate());
        holder.location_dessert.setText(listDessert.get(position).getLocation());
        holder.item1_dessert.setText(listDessert.get(position).getItem_1());
        holder.item2_dessert.setText(listDessert.get(position).getItem_2());
        holder.item3_dessert.setText(listDessert.get(position).getItem_3());


    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



}
