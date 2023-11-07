package com.example.launcher;

import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArrayAdapterApplicationInfo extends ArrayAdapter<ApplicationInfo> {
    public List<ApplicationInfo> allAplicationItemlist;
    public ArrayList<ApplicationInfo> arraylist;
    public ArrayAdapterApplicationInfo(MainActivity mainActivity, int item_lista, List<ApplicationInfo> allAplicationItemlist) {
        super(mainActivity, item_lista, allAplicationItemlist);
        this.allAplicationItemlist = allAplicationItemlist;
        this.arraylist = new ArrayList<ApplicationInfo>();
        this.arraylist.addAll(allAplicationItemlist);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflando layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista, parent, false);
        //Recuperando o objeto ApplicationInfo da posição atual
        ApplicationInfo applicationInfo = getItem(position);
        //Recuperando os componentes do layout
        TextView textViewAppName = view.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = view.findViewById(R.id.app_icon);
        //Setando os valores nos componentes
        imageViewAppIcon.setImageDrawable(applicationInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(applicationInfo.loadLabel(getContext().getPackageManager()));
        return view;
    }
    public void filter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        allAplicationItemlist.clear();
        if (searchText.length() == 0) {
            allAplicationItemlist.addAll(arraylist);
        } else {
            for (ApplicationInfo applicationInfo : arraylist) {
                String appName = applicationInfo.loadLabel(getContext().getPackageManager()).toString().toLowerCase();
                if (appName.contains(searchText)) {
                    allAplicationItemlist.add(applicationInfo);
                }
            }
        }
        notifyDataSetChanged();
    }

}
