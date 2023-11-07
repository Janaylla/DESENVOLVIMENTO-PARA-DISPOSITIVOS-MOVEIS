package com.example.fragmetos;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentA extends Fragment {

    private EditText messageEditText;
    private Button sendButton;

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_a, container, false);

        messageEditText = rootView.findViewById(R.id.mensagem);
        sendButton = rootView.findViewById(R.id.enviar);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                FragmentB fragmentB = FragmentB.newInstance(message);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragmentB)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }
}
