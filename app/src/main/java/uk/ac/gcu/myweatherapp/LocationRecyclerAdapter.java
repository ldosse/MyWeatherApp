package uk.ac.gcu.myweatherapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uk.ac.gcu.myweatherapp.Models.Location;
import uk.ac.gcu.myweatherapp.ui.main.PlaceholderFragment;


public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ViewHolder> {
    private final Context context;
    public final List<Location> locationList;
    private final LayoutInflater layoutInflater;

    public LocationRecyclerAdapter(@NonNull Context context) {
        this.context = context;
        locationList = DataManager.getInstance().locations;
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
        Location location;
        if(!DataManager.updated) {
            RetrieveXMLData getXML = new RetrieveXMLData(position, viewHolder, this);
            getXML.execute();
            location = getXML.location;
        }
        else{
            location=DataManager.getInstance().locations.get(position);
        }
        viewHolder.img.setImageResource(location.getCountryImg());
//        Location location = locationList.get(position);
        viewHolder.locationName.setText(location.getName());
        viewHolder.countryCode.setText(location.getCountryCode());
        if(location.getDays().size()!=0){
            viewHolder.brief.setText(location.getDays().get(0).getBrief());
        }
//        viewHolder.brief.setText(location.getIcon());
        viewHolder.position = position;
//        viewHolder.
//        RetrieveXMLData getXML = new RetrieveXMLData(position,location.id);
//        getXML.execute();
//        days = getXML.getDays();
    }


    public void callBackData(ViewHolder viewHolder,Location location){
        viewHolder.locationName.setText(location.getName());
        viewHolder.brief.setText(location.getDays().get(0).getBrief());
        viewHolder.countryCode.setText(location.getCountryCode());
        viewHolder.minTemperature.setText(location.getDays().get(0).getDescription().get("Minimum Temperature"));
        new DownloadIcon(viewHolder,this)
                .execute(location.getIcon());
    }
    public void callBackImage(ViewHolder viewHolder, Bitmap result) {
//        viewHolder.locationName.setText(location.name);
//        viewHolder.brief.setText(location.days.get(0).getBrief());
        if (result == null){
            System.out.println("ERROR IMAGE");
            viewHolder.imageView.setImageDrawable(
                    PlaceholderFragment.getImage(context, viewHolder.brief.getText().toString()));
        }
        else{
            viewHolder.imageView.setImageBitmap(result);
        }
//        viewHolder.minTemperature.setText(location.days.get(0).description.get("Minimum Temperature"));
//        new DownloadIcon(viewHolder.imageView)
//                .execute(location.icon);
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
        ImageView imageView;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationName = (TextView) itemView.findViewById(R.id.locationName);
            minTemperature = (TextView) itemView.findViewById(R.id.minTemperature);
//            locationDescription = (TextView) itemView.findViewById(R.id.description);
            img = (ImageView) itemView.findViewById(R.id.country_image);
//            countryImg.setImageResource(R.drawable.london);
//            countryImg.setImageResource();
            brief = (TextView) itemView.findViewById(R.id.brief);
            countryCode = (TextView) itemView.findViewById(R.id.countryCode);
            imageView = (ImageView) itemView.findViewById(R.id.iconImg);
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