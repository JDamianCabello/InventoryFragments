package es.jdamiancabello.inventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {
    private ArrayList<Dependency> dependencyArrayList;
    private onManageDependencyListener viewOnManageDependencyListener;

    //Obtenemos los datos desde el repositorio
    public DependencyAdapter() {
        dependencyArrayList = new ArrayList<>();
    }

    public void setViewOnManageDependencyListener(onManageDependencyListener listener){
        this.viewOnManageDependencyListener = listener;
    }

    public void addAll(ArrayList<Dependency> dependencies){
        this.dependencyArrayList.addAll(dependencies);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.icon.setLetter(dependencyArrayList.get(position).getName().substring(0, 1));
        holder.tvName.setText(dependencyArrayList.get(position).getName());
        holder.tvShortName.setText(dependencyArrayList.get(position).getShortName());
        holder.tvDescription.setText(dependencyArrayList.get(position).getDescription());

        holder.bind(dependencyArrayList.get(position), viewOnManageDependencyListener);
    }

    public void clear() {
        this.dependencyArrayList.clear();
    }

    public interface onManageDependencyListener {
        void onEditDependencyListener(Dependency dependency);
        void onDeleteDependencyListener(Dependency dependency);
    }


    @Override
    public int getItemCount() {
        return dependencyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialLetterIcon icon;
        TextView tvName, tvShortName, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.tvDependencyItemIcon);
            tvName = itemView.findViewById(R.id.tvDependencyItemName);
            tvShortName = itemView.findViewById(R.id.tvDependencyItemShortName);
            tvDescription = itemView.findViewById(R.id.tvDependencyItemDescription);
        }

        public void bind(final Dependency dependency, final onManageDependencyListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditDependencyListener(dependency);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onDeleteDependencyListener(dependency);
                    return true;
                }
            });
        }
    }
}

