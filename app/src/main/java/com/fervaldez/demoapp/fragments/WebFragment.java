package com.fervaldez.demoapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.fervaldez.demoapp.R;


public class WebFragment extends Fragment {
    private static final String ARG_URL = "param1";
    private String url = "";


    public WebFragment() {
        // Required empty public constructor
    }

    public static WebFragment newInstance(String newUrl) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, newUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_web, null);

        if (v != null) {
            WebView webView = v.findViewById(R.id.web_view);
            webView.loadUrl(url);
        }

        return v;
    }

}
