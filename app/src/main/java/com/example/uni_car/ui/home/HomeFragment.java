package com.example.uni_car.ui.home;

import static com.example.uni_car.AccesoDatos.getPosts;
import static com.example.uni_car.AccesoDatos.rec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uni_car.R;
import com.example.uni_car.databinding.FragmentHomeBinding;
import com.example.uni_car.recycler.MiAdaptador;
import com.example.uni_car.recycler.Post;

import java.util.ArrayList;

public class HomeFragment extends androidx.fragment.app.Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                rec = root.findViewById(R.id.recyclerHome);
                rec.setLayoutManager(new LinearLayoutManager(root.getContext()));
                ArrayList<Post>posts = new ArrayList<Post>();
                posts.add(new Post("Fuenlabrada", "Utad", "Lunes a viernes a las 7:00"));
                posts.add(new Post("Boadilla", "Utad", "Lunes a martes a las 9:00"));
                posts.add(new Post("Madrid", "Europea", "Lunes 15:00"));
                posts.add(new Post("Fuenlabrada", "Utad", "Lunes a viernes a las 7:00"));

                getPosts();
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