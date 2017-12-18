package sk.akademiasovy.monikajassova.jedalnylistok.ui;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import sk.akademiasovy.monikajassova.jedalnylistok.R;

/**
 * Created by monika.jassova on 12/1/2017.
 */

public class MealViewHolder extends ChildViewHolder {
    private TextView mealName;

    public MealViewHolder(View itemView) {
        super(itemView);
        mealName = itemView.findViewById(R.id.list_item_meal);
    }

    public void setMealName(String name) {
        mealName.setText(name);
    }
}
