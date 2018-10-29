package androidproject.example.com.food.Database.Tables;

import android.provider.BaseColumns;

public class LunchContract {

    public static final class LunchEntry implements BaseColumns {

        public static final String TABLE_NAME = "lunch";
        public static final String COLUMN_DATE = "lunch_date";
        public static final String COLUMN_LOCATION = "lunch_location";
        public static final String COLUMN_ITEM_1 = "lunch_item1";
        public static final String COLUMN_ITEM_2 = "lunch_item2";
        public static final String COLUMN_ITEM_3 = "lunch_item3";
    }
}