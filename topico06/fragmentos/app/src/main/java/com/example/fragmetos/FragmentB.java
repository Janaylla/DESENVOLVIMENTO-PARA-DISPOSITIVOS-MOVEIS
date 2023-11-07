package com.example.fragmetos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentB extends Fragment {

    private static final String ARG_MESSAGE = "message";

    private String message;

    private Button voltarButton;
    public FragmentB() {
        // Required empty public constructor
    }

    public static FragmentB newInstance(String message) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_b, container, false);
        TextView messageTextView = rootView.findViewById(R.id.texto);
        messageTextView.setText(message);

        voltarButton = rootView.findViewById(R.id.voltar);

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = new FragmentA();

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragmentA)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return rootView;
    }
}
