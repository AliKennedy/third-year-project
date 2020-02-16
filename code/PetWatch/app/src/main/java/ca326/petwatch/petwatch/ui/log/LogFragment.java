package ca326.petwatch.petwatch.ui.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca326.petwatch.petwatch.Main2Activity;
import ca326.petwatch.petwatch.R;

public class LogFragment extends Fragment
{
    RecyclerView recyclerView;
    String [] items = {"Ali", "Ethan"};

    //private LogViewModel logViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_log, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.dataLogs);

        RecyclerViewAdapter listAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        return root;
    }

}