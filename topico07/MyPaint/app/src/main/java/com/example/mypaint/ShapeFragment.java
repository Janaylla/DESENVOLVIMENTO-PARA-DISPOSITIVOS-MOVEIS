package com.example.mypaint;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class ShapeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    ImageButton shape;
    public ShapeFragment() {
    }

    public static ShapeFragment newInstance() {
        ShapeFragment fragment = new ShapeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shape, container, false);

        shape = view.findViewById(R.id.shape);
        shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        return view;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Shape shape;

        if (item.getItemId() == R.id.menu_shape_brush) {
             shape = Shape.BRUSH;
        } else if (item.getItemId() == R.id.menu_shape_circle) {
             shape = Shape.CIRCLE;
        } else if (item.getItemId() == R.id.menu_shape_triangle) {
             shape = Shape.TRIANGLE;
        } else if (item.getItemId() == R.id.menu_shape_square) {
             shape = Shape.SQUARE;
        } else if (item.getItemId() == R.id.menu_shape_line) {
             shape = Shape.LINE;
        } else {
            return false;
        }

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setLayoutShape(shape);
        }
        Drawable icon = item.getIcon();
        this.shape.setImageDrawable(icon);
        return true;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_shape, popup.getMenu());
        popup.show();
    }
}