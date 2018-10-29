package androidproject.example.com.food.EDiary.TabLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import androidproject.example.com.food.Adapter.LunchRecycler;
import androidproject.example.com.food.Database.Tables.LunchHelper;
import androidproject.example.com.food.POJO.Lunch;
import androidproject.example.com.food.R;

public class Tab2 extends Fragment {
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private LunchRecycler lunchRecycler;
    private LunchHelper lunchHelper;
    private RecyclerView recyclerView;
    private ArrayList<Lunch> listLunch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab2, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewLunch);
        initObjects();

        return view;
    }
    private void initObjects() {
        listLunch = new ArrayList<>();
        lunchRecycler=new LunchRecycler(listLunch,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(lunchRecycler);
        lunchHelper = new LunchHelper(getActivity());

        getDataLunchSQLite();

    }


    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataLunchSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listLunch.clear();
                listLunch.addAll(lunchHelper. getAllLunch());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                lunchRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
