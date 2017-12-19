package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.R;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory;

/**
 * Created by monika.jassova on 12/19/2017.
 */

public class MealCategoryPersAdapter extends RecyclerView.Adapter<MealCategoryPersAdapter.MealCategoryPersAdapterViewHolder> {

    // The context we use to utility methods, app resources and layout inflaters
    private final Context mContext;

    private final MealCategoryPersAdapterOnItemClickHandler mClickHandler;
    private List<sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory> mMealCategories;


    MealCategoryPersAdapter(@NonNull Context context, MealCategoryPersAdapterOnItemClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    @Override
    public MealCategoryPersAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_layout, viewGroup, false);
        view.setFocusable(true);
        return new MealCategoryPersAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealCategoryPersAdapter.MealCategoryPersAdapterViewHolder holder, int position) {
        sk.akademiasovy.monikajassova.jedalnylistok.data.model.MealCategory mc = mMealCategories.get(position);

        /* Set the text and content description (for accessibility purposes) */
        holder.titleView.setText(mc.getName());
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mMealCategories) return 0;
        return mMealCategories.size();
    }

    void swapMealCategories(final List<MealCategory> newMealCategories) {
        // If there was no forecast data, then recreate all of the list
        if (mMealCategories == null) {
            mMealCategories = newMealCategories;
            notifyDataSetChanged();
        } else {
                        /*
+            * Otherwise we use DiffUtil to calculate the changes and update accordingly. This
+            * shows the four methods you need to override to return a DiffUtil callback. The
+            * old list is the current list stored in mForecast, where the new list is the new
+            * values passed in from the observing the database.
+            */

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mMealCategories.size();
                }

                @Override
                public int getNewListSize() {
                    return newMealCategories.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mMealCategories.get(oldItemPosition).getId() == newMealCategories.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    MealCategory newMealCategory = newMealCategories.get(newItemPosition);
                    MealCategory oldMealCategory = mMealCategories.get(oldItemPosition);
                    return newMealCategory.getId().equals(oldMealCategory.getId())
                            && newMealCategory.getMeals() == oldMealCategory.getMeals();

                }
            });
            mMealCategories = newMealCategories;
            result.dispatchUpdatesTo(this);
        }
    }

    public interface MealCategoryPersAdapterOnItemClickHandler {
        void onItemClick(String id);
    }

    class MealCategoryPersAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView titleView;

        MealCategoryPersAdapterViewHolder(View view) {
            super(view);

            titleView = view.findViewById(R.id.firstLine);

            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click. We fetch the id that has been
         * selected, and then call the onItemClick handler registered with this adapter, passing that
         * id.
         *
         * @param v the View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String id = mMealCategories.get(adapterPosition).getId();
            mClickHandler.onItemClick(id);
        }
    }
}


