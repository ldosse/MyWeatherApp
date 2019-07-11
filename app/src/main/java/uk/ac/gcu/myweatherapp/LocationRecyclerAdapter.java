package uk.ac.gcu.myweatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ViewHolder> {
    private final Context context;
    public final List<Location> locationList;
    private final LayoutInflater layoutInflater;

    public LocationRecyclerAdapter(@NonNull Context context, List<Location> locations) {
        this.context = context;
        locationList = locations;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.item_location_list, viewGroup, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        List<Day> days;
        Location location = locationList.get(position);
        viewHolder.locationName.setText(location.name);
        viewHolder.locationTemp.setText(location.id);
//        viewHolder.brief.setText(location.days.get(0).getBrief());
        viewHolder.position=position;
        RetrieveXMLData getXML = new RetrieveXMLData(position,location.id);
        getXML.execute();
        days = getXML.getDays();
//        getXML.onPostExecute(days);
//        System.out.println("after post exec "+days.size());
//        String res;
//        System.out.println("\n\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\n\n" + descriptions.size());
//        if (descriptions.size() != 0){
//            res = String.valueOf(descriptions.size());
//        }

    }


    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
        TextView locationTemp;
        TextView brief;
        int currentPosition;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = (TextView) itemView.findViewById(R.id.locationName);
            locationTemp = (TextView) itemView.findViewById(R.id.locationTemp);
            brief = (TextView) itemView.findViewById(R.id.brief);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ActivityLocation.class);
                    intent.putExtra("index", position);
                    context.startActivity(intent);
                }
            });
        }
    }
}