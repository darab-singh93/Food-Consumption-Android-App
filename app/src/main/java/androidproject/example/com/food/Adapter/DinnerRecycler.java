package androidproject.example.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


import androidproject.example.com.food.POJO.Dinner;
import androidproject.example.com.food.R;

public class DinnerRecycler extends RecyclerView.Adapter<DinnerRecycler.DinnerViewHolder>  {

    private ArrayList<Dinner> listDinner;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Dinner> mDinnerList;


    public DinnerRecycler(ArrayList<Dinner> listDinner, Context mContext) {
        this.listDinner = listDinner;
        this.mContext = mContext;
        this.mDinnerList = listDinner;


    }

    public class DinnerViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView location_dinner;
        public AppCompatTextView item1_dinner;
        public AppCompatTextView item2_dinner;
        public AppCompatTextView item3_dinner;
        public AppCompatTextView date_dinner;

        public DinnerViewHolder(View view) {
            super(view);
            date_dinner=(AppCompatTextView)view.findViewById(R.id.date_dinner);
            location_dinner = (AppCompatTextView) view.findViewById(R.id.location_dinner);
            item1_dinner = (AppCompatTextView) view.findViewById(R.id.item1_dinner);
            item2_dinner = (AppCompatTextView) view.findViewById(R.id.item2_dinner);
            item3_dinner = (AppCompatTextView) view.findViewById(R.id.item3_dinner);

        }


    }




    @Override
    public DinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dinner_recycler, parent, false);

        return new DinnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DinnerViewHolder holder, int position) {
        holder.date_dinner.setText(listDinner.get(position).getDate());
        holder.location_dinner.setText(listDinner.get(position).getLocation());
        holder.item1_dinner.setText(listDinner.get(position).getItem_1());
        holder.item2_dinner.setText(listDinner.get(position).getItem_2());
        holder.item3_dinner.setText(listDinner.get(position).getItem_3());


    }


    @Override
    public int getItemCount() {
        return mDinnerList.size();
    }



}

