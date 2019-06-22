package com.easylang;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionaryDAO {
    @Query("SELECT * FROM Dictionary")
    List<Dictionary> getAll();

    @Query("SELECT * FROM Dictionary WHERE langFrom=:langFrom and langTo=:langTo")
    List<Dictionary> getTranslateByLang(String langFrom, String langTo);

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

    @Query("SELECT COUNT(id) FROM Dictionary WHERE langFrom=:langFrom and langTo=:langTo")
    int getNotesCountByParams(String langFrom, String langTo);
}
