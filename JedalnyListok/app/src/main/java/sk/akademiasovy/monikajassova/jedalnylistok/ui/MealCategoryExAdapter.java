package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.R;
import sk.akademiasovy.monikajassova.jedalnylistok.data.model2.Mealm;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealCategoryExAdapter extends ExpandableRecyclerViewAdapter<MealCategoryViewHolder, MealViewHolder> {
    public MealCategoryExAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public MealCategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mealcategory, parent, false);
        return new MealCategoryViewHolder(view);
    }

    @Override
    public MealViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MealViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Mealm meal = ((MealCategory) group).getItems().get(childIndex);
        holder.setMealName(meal.getName());
    }

    @Override
    public void onBindGroupViewHolder(MealCategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setMealCategoryTitle(group);
    }
}
