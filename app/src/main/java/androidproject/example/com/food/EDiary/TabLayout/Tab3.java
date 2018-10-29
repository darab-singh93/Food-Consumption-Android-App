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

import androidproject.example.com.food.Adapter.DinnerRecycler;
import androidproject.example.com.food.Database.Tables.DinnerHelper;
import androidproject.example.com.food.POJO.Dinner;
import androidproject.example.com.food.R;

public class Tab3 extends Fragment {

    private RecyclerView recyclerView;
    private DinnerRecycler dinnerRecycler;
    private DinnerHelper dinnerHelper;
    private ArrayList<Dinner> listDinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab3, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewDinner);
        initObjects();

        return view;
    }
    private void initObjects() {
        listDinner = new ArrayList<>();
        dinnerRecycler=new DinnerRecycler(listDinner,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dinnerRecycler);
        dinnerHelper = new DinnerHelper(getActivity());

        getDataFromSQLite();

    }


    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listDinner.clear();
                listDinner.addAll(dinnerHelper. getAllDinner());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dinnerRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
