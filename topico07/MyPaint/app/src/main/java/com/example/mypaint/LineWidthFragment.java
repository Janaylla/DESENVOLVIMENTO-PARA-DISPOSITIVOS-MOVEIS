package com.example.mypaint;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class LineWidthFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    ImageButton formPicker;

    public LineWidthFragment() {
    }

    public static LineWidthFragment newInstance() {
        return new LineWidthFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_width, container, false);

        formPicker = view.findViewById(R.id.formPicker);
        formPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int size;

        if (item.getItemId() == R.id.stroke_2x) {
            size = 2;
        } else if (item.getItemId() == R.id.stroke_4x) {
            size = 4;
        } else if (item.getItemId() == R.id.stroke_8x) {
            size = 8;
        } else if (item.getItemId() == R.id.stroke_16x) {
            size = 16;
        } else if (item.getItemId() == R.id.stroke_24x) {
            size = 24;
        } else if (item.getItemId() == R.id.stroke_32x) {
            size = 32;
        } else {
            return false;
        }

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setSimplePaintSize(size);
        }
        return true;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_line_width, popup.getMenu());
        popup.show();
    }
}
