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

    @Query("SELECT COUNT(id) FROM Dictionary")
    int getNotesCount();

    @Query("SELECT * FROM Dictionary WHERE id=:id")
    Dictionary getDataById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dictionary dictionary);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Dictionary> dictionaries);

    @Delete
    void delete(Dictionary dictionary);

    @Query("DELETE FROM Dictionary")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM Dictionary WHERE langFrom=:langFrom and langTo=:langTo")
    int getNotesCountByParams(String langFrom, String langTo);

    @Query("SELECT * FROM Dictionary\n" +
            "WHERE langFrom=:langFrom and langTo=:langTo\n" +
            "ORDER BY RANDOM()\n" +
            "LIMIT :count")
    List<Dictionary> getRandomWords(String langFrom, String langTo, int count);
}
