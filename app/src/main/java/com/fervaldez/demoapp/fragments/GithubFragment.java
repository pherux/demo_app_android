package com.fervaldez.demoapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fervaldez.demoapp.R;
import com.fervaldez.demoapp.adapters.RepositoriesAdapter;
import com.fervaldez.demoapp.models.RepositoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GithubFragment extends Fragment {
    private static final String ARG_URL = "args_url";
    List<RepositoryModel> repositories = new ArrayList<>();
    private InteractionListener mListener;
    private ListView list;
    private ProgressBar progressBar;
    private String mUrl;


    public GithubFragment() {
        // Required empty public constructor
    }

    public static GithubFragment newInstance(String URL) {
        GithubFragment fragment = new GithubFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, URL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_github, container, false);
        list = v.findViewById(R.id.projects_list_view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RepositoryModel repository = (RepositoryModel) parent.getItemAtPosition(position);
                sendToURL(repository.URL);

            }
        });
        progressBar = v.findViewById(R.id.progress_bar);
        return v;
    }


    private void sendToURL(String url) {
        mListener.onOpenWebSite(url);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mUrl != null) {

            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }
            requestGithubData();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            //init the listener
            mListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void requestGithubData() {

        StringRequest request = new StringRequest(Request.Method.GET, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);

                    JSONArray items = object.getJSONArray("items");

                    if (items != null && items.length() > 10) {
                        for (int i = 0; i < 10; i++) {

                            RepositoryModel repository = new RepositoryModel();
                            JSONObject item = (JSONObject) items.get(i);

                            repository.name = item.getString("full_name");
                            repository.description = item.getString("description");
                            repository.URL = item.getString("html_url");
                            repositories.add(repository);
                        }
                    }

                    if (repositories.size() > 0) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }

                        RepositoriesAdapter adapter = new RepositoriesAdapter(getContext(), repositories);
                        list.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("test", "" + response.length());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test", "" + error.getMessage());
            }
        });

        if (getContext() != null) {
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request);
        }

    }

    public interface InteractionListener {
        void onOpenWebSite(String string);
    }

}
