package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.R;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;

/**
 * Created by monika.jassova on 11/30/2017.
 */

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.MealCategoryViewHolder> {
    private List<MealCategory> mealCategories;
    private int rowLayout;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MealCategoryViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public View layout;

        public MealCategoryViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MealCategoryAdapter(List<MealCategory> mealCategories, int rowLayout, Context context) {
        this.mealCategories = mealCategories;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MealCategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MealCategoryViewHolder vh = new MealCategoryViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MealCategoryViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtHeader.setText(mealCategories.get(position).getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked category id: "+ mealCategories.get(position).getId(), Toast.LENGTH_LONG).show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mealCategories.size();
    }

    public void updateAnswers(List<MealCategory> mealCategories) {
        this.mealCategories = mealCategories;
        notifyDataSetChanged();
    }

    private MealCategory getMealCategory(int adapterPosition) {
        return mealCategories.get(adapterPosition);
    }

}
