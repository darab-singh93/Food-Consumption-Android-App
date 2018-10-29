package androidproject.example.com.food.Database.Tables;

import android.provider.BaseColumns;

public class DinnerContract {

    public static final class DinnerEntry implements BaseColumns {

        public static final String TABLE_NAME = "dinner";
        public static final String COLUMN_DATE = "dinner_date";
        public static final String COLUMN_LOCATION = "dinner_name";
        public static final String COLUMN_ITEM_1 = "dinner_item1";
        public static final String COLUMN_ITEM_2 = "dinner_item2";
        public static final String COLUMN_ITEM_3 = "dinner_item3";
    }
}