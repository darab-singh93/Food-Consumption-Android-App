package androidproject.example.com.food.EDiary.TabLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidproject.example.com.food.Adapter.SnackRecycler;
import androidproject.example.com.food.Database.Tables.SnackHelper;
import androidproject.example.com.food.POJO.Snack;
import androidproject.example.com.food.R;
public class Tab4 extends Fragment {

    private RecyclerView recyclerView;
    private SnackRecycler snackRecycler;
    private SnackHelper snackHelper;
    private ArrayList<Snack> listSnack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab4, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewSnack);
        initObjects();

        return view;
    }
    private void initObjects() {
        listSnack = new ArrayList<>();
        snackRecycler=new SnackRecycler(listSnack,getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(snackRecycler);
        snackHelper = new SnackHelper(getActivity());

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
                listSnack.clear();
                listSnack.addAll(snackHelper. getAllSnack());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                snackRecycler.notifyDataSetChanged();
            }
        }.execute();
    }
}
