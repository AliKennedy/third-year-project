package ca326.petwatch.petwatch.ui.panic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca326.petwatch.petwatch.R;

public class PanicFragment extends Fragment {

    private PanicViewModel panicViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        panicViewModel =
                ViewModelProviders.of(this).get(PanicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_panic, container, false);
        final TextView textView = root.findViewById(R.id.text_panic);
        panicViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}