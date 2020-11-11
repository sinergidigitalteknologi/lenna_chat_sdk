package ai.lenna.lennachatmodul.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ai.lenna.lennachatmodul.room.dao.ChatResponseDao;
import ai.lenna.lennachatmodul.room.entity.ChatResponseEntity;

@Keep
@Database(entities = {ChatResponseEntity.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "notification";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract ChatResponseDao chatResponseDao();
}
