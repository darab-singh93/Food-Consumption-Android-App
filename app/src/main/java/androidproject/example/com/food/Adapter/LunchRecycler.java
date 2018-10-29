package androidproject.example.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


import androidproject.example.com.food.POJO.Lunch;
import androidproject.example.com.food.R;

public class LunchRecycler extends RecyclerView.Adapter<LunchRecycler.LunchViewHolder>  {

    private ArrayList<Lunch> listLunch;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Lunch> mLunchList;


    public LunchRecycler(ArrayList<Lunch> listLunch, Context mContext) {
        this.listLunch = listLunch;
        this.mContext = mContext;
        this.mLunchList = listLunch;


    }

    public class LunchViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView date_lunch;
        public AppCompatTextView location_lunch;
        public AppCompatTextView item1_lunch;
        public AppCompatTextView item2_lunch;
        public AppCompatTextView item3_lunch;

        public LunchViewHolder(View view) {
            super(view);
            date_lunch=(AppCompatTextView)view.findViewById(R.id.date_lunch);
            location_lunch = (AppCompatTextView) view.findViewById(R.id.location_lunch);
            item1_lunch = (AppCompatTextView) view.findViewById(R.id.item1_lunch);
            item2_lunch = (AppCompatTextView) view.findViewById(R.id.item2_lunch);
            item3_lunch = (AppCompatTextView) view.findViewById(R.id.item3_lunch);

        }


    }




    @Override
    public LunchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lunch_recycler, parent, false);

        return new LunchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LunchViewHolder holder, int position) {
        holder.date_lunch.setText(listLunch.get(position).getDate());
        holder.location_lunch.setText(listLunch.get(position).getLocation());
        holder.item1_lunch.setText(listLunch.get(position).getItem_1());
        holder.item2_lunch.setText(listLunch.get(position).getItem_2());
        holder.item3_lunch.setText(listLunch.get(position).getItem_3());


    }


    @Override
    public int getItemCount() {
        return mLunchList.size();
    }



}

