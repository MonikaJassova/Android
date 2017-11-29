package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Dao
public interface AddOnCategoryDAO {
    @Query("SELECT * FROM addoncategories")
    List<AddOnCategory> getAll();

    @Query("SELECT * FROM addoncategories WHERE id LIKE :id")
    List<AddOnCategory> findByMealId(String id);

    @Query("SELECT * FROM addoncategories " +
            "INNER JOIN meals ON Loan.book_id == Book.id " +
            "WHERE Loan.user_id == :userId ")
    List<AddOnCategory> findByMealId2(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(AddOnCategory... addOnCategories);
}
