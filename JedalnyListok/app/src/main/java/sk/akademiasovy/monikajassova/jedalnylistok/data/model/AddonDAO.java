package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Dao
public interface AddonDAO {
    @Query("SELECT * FROM addons")
    List<Addon> getAll();

    @Query("SELECT * FROM addons WHERE id LIKE :id")
    Addon findById(String id);

    @Query("SELECT * FROM addons WHERE categoryid LIKE :id")
    List<Addon> findByCategoryId(String id);

//    @Query("SELECT * FROM addons WHERE LIKE :id")
//    List<Addon> findByMealId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Addon... addons);
}
