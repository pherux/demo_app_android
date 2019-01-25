package com.fervaldez.demoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fervaldez.demoapp.R;
import com.fervaldez.demoapp.models.RepositoryModel;

import java.util.List;

public class RepositoriesAdapter extends ArrayAdapter<RepositoryModel> {
    private Context context;

    public RepositoriesAdapter(@NonNull Context context, List<RepositoryModel> repositories) {
        super(context, R.layout.repository_item, repositories);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.repository_item, null);
        }

        if (convertView != null) {
            TextView title = convertView.findViewById(R.id.name_view);
            TextView subtitle = convertView.findViewById(R.id.description_view);

            RepositoryModel item = getItem(position);

            if (item != null) {
                String titleStr = "" + (position + 1) +". "+ item.name;
                title.setText(titleStr);
                subtitle.setText(item.description);
            }
        }

        return convertView;
    }
}
