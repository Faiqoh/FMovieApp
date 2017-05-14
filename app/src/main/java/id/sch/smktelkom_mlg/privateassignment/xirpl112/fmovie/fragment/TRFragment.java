package id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.adapter.TRAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.model.TRResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.model.TopRated;
import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TRFragment extends Fragment {

    TRAdapter mAdapter;
    ArrayList<TopRated> mList = new ArrayList<>();

    public TRFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tr, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewTR);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new TRAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        downloadDataSources();
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=c68f3a9f7fc8c2ddb8734e1b05b5d21a";

        GsonGetRequest<TRResponse> myRequest = new GsonGetRequest<TRResponse>
                (url, TRResponse.class, null, new Response.Listener<TRResponse>() {

                    @Override
                    public void onResponse(TRResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

}
