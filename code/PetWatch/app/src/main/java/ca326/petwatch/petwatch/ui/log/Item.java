package ca326.petwatch.petwatch.ui.log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca326.petwatch.petwatch.R;

public class Item extends RecyclerView.ViewHolder
{

    private TextView textView;
    private ImageView imageView;

    public Item(@NonNull View itemView)
    {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.date);
        imageView = (ImageView) itemView.findViewById(R.id.itemImage);

    }

    public void bindView(int position)
    {
        textView.setText(RecyclerViewAdapter.getItems()[position]);
        imageView.setImageResource(RecyclerViewAdapter.getImages()[position]);
    }

    public ImageView getImageView()
    {
        return imageView;
    }

    public TextView getTextView()
    {
        return textView;
    }
}
