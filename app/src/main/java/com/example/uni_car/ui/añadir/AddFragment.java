package com.example.uni_car.ui.añadir;

import static com.example.uni_car.AccesoDatos.getPosts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.uni_car.AccesoDatos;
import com.example.uni_car.R;
import com.example.uni_car.databinding.FragmentAddBinding;


public class AddFragment extends Fragment {

    private AddViewModel addViewModel;
    private FragmentAddBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textAdd;

        addViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                EditText origen;
                EditText destino;
                EditText fecha;
                Button send;
                origen = root.findViewById(R.id.editOrigen);
                destino = root.findViewById(R.id.editDestino);
                fecha = root.findViewById(R.id.editFecha);
                send = root.findViewById(R.id.send);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String origenS, destinoS, fechaS;
                        origenS = origen.getText().toString();
                        destinoS = destino.getText().toString();
                        fechaS = fecha.getText().toString();
                        AccesoDatos.uploadData(origenS, destinoS, fechaS);
                        getPosts();
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}