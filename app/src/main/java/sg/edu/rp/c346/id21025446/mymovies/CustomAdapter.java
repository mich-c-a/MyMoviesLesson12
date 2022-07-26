package sg.edu.rp.c346.id21025446.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvcTitle);
        TextView tvYear = rowView.findViewById(R.id.tvcYear);
        TextView tvGenre = rowView.findViewById(R.id.tvcGenre);
        ImageView imgRate = rowView.findViewById(R.id.imgRating);

        // Obtain the Android Version information based on the position
        Movie currentVersion = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        tvGenre.setText(currentVersion.getGenre());

        if (currentVersion.getRating().equalsIgnoreCase("G")){
            imgRate.setImageResource(R.drawable.rating_g);
        }else if (currentVersion.getRating().equalsIgnoreCase("PG")){
            imgRate.setImageResource(R.drawable.rating_pg);
        }else if (currentVersion.getRating().equalsIgnoreCase("PG13")){
            imgRate.setImageResource(R.drawable.rating_pg13);
        }else if (currentVersion.getRating().equalsIgnoreCase("NC16")){
            imgRate.setImageResource(R.drawable.rating_nc16);
        }else if (currentVersion.getRating().equalsIgnoreCase("M18")){
            imgRate.setImageResource(R.drawable.rating_m18);
        }else{
            imgRate.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
