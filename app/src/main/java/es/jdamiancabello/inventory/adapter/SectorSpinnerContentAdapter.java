package es.jdamiancabello.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class SectorSpinnerContentAdapter extends ArrayAdapter<Dependency> {
    List<Dependency> dependencies;

    public SectorSpinnerContentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        super.getView(position, convertView, parent);

        View v = convertView;
        ViewHolder viewHolder;

        if(v == null){
            viewHolder = new ViewHolder();

            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemspinner_add_sector_listof_dependencys,parent,false);

            viewHolder.tvName = v.findViewById(R.id.tvDependencyName);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.tvName.setText(dependencies.get(position).toString());

        return v;
    }



    public void addAll(List<Dependency> dependencies) {
        this.dependencies.addAll(dependencies);
    }

    private static class ViewHolder{
        private TextView tvName;
    }
}
