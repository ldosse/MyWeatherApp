package uk.ac.gcu.myweatherapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import uk.ac.gcu.myweatherapp.R;

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.MyViewHolder>{
    private String[][] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView textView;
        public TextView value;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.textView = itemView.findViewById(R.id.key);
            this.value = itemView.findViewById(R.id.value);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AttributesAdapter(Map<String, String> myDataset) {
        String[] keyList = myDataset.keySet().toArray(new String[0]);
        String[] valueList = myDataset.values().toArray(new String[0]);
        mDataset = new String[2][keyList.length];
        mDataset[0] = keyList;
        mDataset[1] = valueList;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttributesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attributes, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[0][position]);
        holder.value.setText(mDataset[1][position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset[0].length;
    }
}
