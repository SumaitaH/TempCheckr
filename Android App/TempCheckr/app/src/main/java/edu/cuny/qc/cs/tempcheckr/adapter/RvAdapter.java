package edu.cuny.qc.cs.tempcheckr.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import edu.cuny.qc.cs.tempcheckr.R;
import edu.cuny.qc.cs.tempcheckr.model.Weather;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    RequestOptions options ;
    private Context mContext ;
    private List<Weather> mData ;


    public RvAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.weather_row_item,parent,false);

         //click listener here
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String degree = mData.get(position).getHighTemp() + "° C";
        String windSpeed = "Wind speed:" + mData.get(position).getWindSpeed();
                holder.weekday.setText(mData.get(position).getWeekday());
        holder.temp.setText(degree);
        holder.descript.setText(mData.get(position).getDescription());
        holder.windspeed.setText(windSpeed);

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getIconLink()).apply(options).into(holder.WeatherThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //TextView tvname,tv_rate,tvstudio,tvcat;
        TextView weekday, temp, descript, windspeed;
        ImageView WeatherThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            weekday = itemView.findViewById(R.id.dayOfWeek);
            temp= itemView.findViewById(R.id.temperature);
            descript = itemView.findViewById(R.id.descript);
            windspeed = itemView.findViewById(R.id.windspeed);
            WeatherThumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }


}
