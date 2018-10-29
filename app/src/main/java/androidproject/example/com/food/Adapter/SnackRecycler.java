package androidproject.example.com.food.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidproject.example.com.food.R;
import androidproject.example.com.food.POJO.Snack;


public class SnackRecycler extends RecyclerView.Adapter<SnackRecycler.SnackViewHolder>  {

    private ArrayList<Snack> listSnack;
    private Context mContext;
    private ArrayList<Snack> mSnackList;


    public SnackRecycler(ArrayList<Snack> listSnack, Context mContext) {
        this.listSnack = listSnack;
        this.mContext = mContext;
        this.mSnackList = listSnack;


    }

    public class SnackViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView date_snack;
        public AppCompatTextView location_snack;
        public AppCompatTextView item1_snack;
        public AppCompatTextView item2_snack;
        public AppCompatTextView item3_snack;

        public SnackViewHolder(View view) {
            super(view);
            date_snack=(AppCompatTextView)view.findViewById(R.id.date_snack);
            location_snack = (AppCompatTextView) view.findViewById(R.id.location_snack);
            item1_snack = (AppCompatTextView) view.findViewById(R.id.item1_snack);
            item2_snack = (AppCompatTextView) view.findViewById(R.id.item2_snack);
            item3_snack = (AppCompatTextView) view.findViewById(R.id.item3_snack);

        }
    }
    @Override
    public SnackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snack_recycler, parent, false);

        return new SnackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SnackViewHolder holder, int position) {
        holder.date_snack.setText(listSnack.get(position).getDate());
        holder.location_snack.setText(listSnack.get(position).getLocation());
        holder.item1_snack.setText(listSnack.get(position).getItem_1());
        holder.item2_snack.setText(listSnack.get(position).getItem_2());
        holder.item3_snack.setText(listSnack.get(position).getItem_3());

    }
    @Override
    public int getItemCount() {
        return mSnackList.size();
    }

}

