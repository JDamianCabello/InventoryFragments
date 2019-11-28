package es.jdamiancabello.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Sector;

public class SectorAdapter extends RecyclerView.Adapter<SectorAdapter.ViewHolder> {
    private List<Sector> sectorsList;
    private OnManageSectorListener viewOnManageSectorListener;

    public SectorAdapter() {
        sectorsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.sector_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sectorImage.setImageResource(R.drawable.ic_github);
        holder.sectorName.setText(sectorsList.get(position).getName());
        holder.sectorShortName.setText(sectorsList.get(position).getShortName());
        holder.sectorDescription.setText(sectorsList.get(position).getSectorDescription());
        holder.sectorDependencyName.setText(sectorsList.get(position).getDependency().toString());

        holder.bind(sectorsList.get(position),viewOnManageSectorListener);
    }

    public void setOnManageSectorListener(OnManageSectorListener listener){
        this.viewOnManageSectorListener = listener;
    }

    @Override
    public int getItemCount() {
        return sectorsList.size();
    }

    public void clear() {
        this.sectorsList.clear();
    }

    public void addAll(ArrayList<Sector> sectors) {
        this.sectorsList.addAll(sectors);
    }

    public interface OnManageSectorListener{
        void onDeleteSectorListener(Sector sector);
        void onAddorEditSectorListener(Sector sector);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sectorDependencyName;
        private TextView sectorName;
        private TextView sectorShortName;
        private TextView sectorDescription;

        private ImageView sectorImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sectorDependencyName = itemView.findViewById(R.id.sectorItem_tvDependencyName);
            sectorName = itemView.findViewById(R.id.sectorItem_tvSectorName);
            sectorShortName = itemView.findViewById(R.id.sectorItem_tvSectorShortName);
            sectorDescription = itemView.findViewById(R.id.sectorItem_tvSectorDescription);
            sectorImage = itemView.findViewById(R.id.sectorItem_ivSectorImage);
        }

        public void bind(final Sector sector,final OnManageSectorListener onManageSectorListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onManageSectorListener.onAddorEditSectorListener(sector);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onManageSectorListener.onDeleteSectorListener(sector);
                    return false;
                }
            });
        }
    }
}
