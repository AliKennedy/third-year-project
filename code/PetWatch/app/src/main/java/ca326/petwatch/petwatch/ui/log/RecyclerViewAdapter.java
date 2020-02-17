package ca326.petwatch.petwatch.ui.log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ca326.petwatch.petwatch.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static String [] items = new String []
            {
            "Ethan",
            "Ali"
    };

    private static int [] images = new int []
            {
            R.drawable.beach,
            R.drawable.forest,
            R.drawable.park
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.list_item_recycler_view, parent, false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ((Item)holder).getTextView().setText(items[position]);
        ((Item)holder).getImageView().setImageResource(images[position]);
    }

    @Override
    public int getItemCount()
    {
        return items.length;
    }

    public static String [] getItems()
    {
        return items;
    }

    public static int[] getImages()
    {
        return images;
    }
}
