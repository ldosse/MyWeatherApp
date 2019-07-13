package uk.ac.gcu.myweatherapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
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
        RetrieveXMLData getXML = new RetrieveXMLData(this.locationList.get(position), viewHolder,this);
        getXML.execute();
        Location location = getXML.location;
//        Location location = locationList.get(position);
        viewHolder.locationName.setText(location.name);
        viewHolder.countryCode.setText(location.countryCode);
        if(location.days.size()!=0){
            viewHolder.brief.setText(location.days.get(0).getBrief());
        }
//        viewHolder.brief.setText(location.getIcon());
        viewHolder.position = position;
//        viewHolder.
//        RetrieveXMLData getXML = new RetrieveXMLData(position,location.id);
//        getXML.execute();
//        days = getXML.getDays();
    }


    public void callBackData(ViewHolder viewHolder,Location location){
        viewHolder.locationName.setText(location.name);
        viewHolder.brief.setText(location.days.get(0).getBrief());
        viewHolder.countryCode.setText(location.countryCode);
        viewHolder.minTemperature.setText(location.days.get(0).description.get("Minimum Temperature"));

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationName;
//        TextView locationDescription;
        TextView brief;
        int position;
        TextView countryCode;
        TextView minTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = (TextView) itemView.findViewById(R.id.locationName);
            minTemperature = (TextView) itemView.findViewById(R.id.minTemperature);
//            locationDescription = (TextView) itemView.findViewById(R.id.description);
            brief = (TextView) itemView.findViewById(R.id.brief);
            countryCode = (TextView) itemView.findViewById(R.id.countryCode);

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