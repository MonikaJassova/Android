package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import sk.akademiasovy.monikajassova.jedalnylistok.R;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealCategoryViewHolder extends GroupViewHolder {
    private TextView mealCategoryTitle;

    public MealCategoryViewHolder(View itemView) {
        super(itemView);
        mealCategoryTitle = itemView.findViewById(R.id.list_item_mealcategory);
    }

    public void setMealCategoryTitle(ExpandableGroup mealCategory) {
        if (mealCategory instanceof MealCategory){
            mealCategoryTitle.setText(mealCategory.getTitle());
        }
    }
}
