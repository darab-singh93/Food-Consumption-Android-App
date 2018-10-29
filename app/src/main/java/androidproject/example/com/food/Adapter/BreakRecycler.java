package androidproject.example.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


import androidproject.example.com.food.POJO.Break;
import androidproject.example.com.food.R;
public class BreakRecycler extends RecyclerView.Adapter<BreakRecycler.BeneficiaryViewHolder>  {

    private ArrayList<Break> listBeneficiary;
    private Context mContext;
    private ArrayList<Break> mFilteredList;


    public BreakRecycler(ArrayList<Break> listBeneficiary, Context mContext) {
        this.listBeneficiary = listBeneficiary;
        this.mContext = mContext;
        this.mFilteredList = listBeneficiary;


    }

    public class BeneficiaryViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView location_break;
        public AppCompatTextView item1_break;
        public AppCompatTextView item2_break;
        public AppCompatTextView item3_break;
        public AppCompatTextView date_break;

        public BeneficiaryViewHolder(View view) {
            super(view);
            date_break=(AppCompatTextView)view.findViewById(R.id.date_break);
            location_break = (AppCompatTextView) view.findViewById(R.id.location_break);
            item1_break = (AppCompatTextView) view.findViewById(R.id.item1_break);
            item2_break = (AppCompatTextView) view.findViewById(R.id.item2_break);
            item3_break = (AppCompatTextView) view.findViewById(R.id.item3_break);

        }
    }

    @Override
    public BeneficiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.break_recycler, parent, false);

        return new BeneficiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BeneficiaryViewHolder holder, int position) {
        holder.date_break.setText(listBeneficiary.get(position).getDate());
        holder.location_break.setText(listBeneficiary.get(position).getLocation());
        holder.item1_break.setText(listBeneficiary.get(position).getItem_1());
        holder.item2_break.setText(listBeneficiary.get(position).getItem_2());
        holder.item3_break.setText(listBeneficiary.get(position).getItem_3());


    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



}

