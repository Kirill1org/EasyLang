package com.easylang;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionaryDAO {

    @Query("SELECT * FROM Dictionary")
    LiveData<List<Dictionary>> getAll();

    @Query("SELECT * FROM Dictionary WHERE langFrom=:langFrom and langTo=:langTo")
    LiveData<List<Dictionary>> getTranslateByLang(String langFrom, String langTo);

    @Query("SELECT COUNT(textFrom)  FROM Dictionary WHERE textFrom = :textFrom and textTo=:textTo")
    int getByText(String textFrom, String textTo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dictionary dictionary);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Dictionary> dictionaries);

    @Delete
    void delete(Dictionary dictionary);

    @Query("DELETE FROM Dictionary")
    void deleteAll();

//    @Query("SELECT * FROM Dictionary WHERE ")
//    Dictionary getDictionary()

}
