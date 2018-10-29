package androidproject.example.com.food.Database.Tables;

import android.provider.BaseColumns;

public class SnackContract {

    public static final class SnackEntry implements BaseColumns {

        public static final String TABLE_NAME = "snack";
        public static final String COLUMN_DATE = "snack_date";
        public static final String COLUMN_BENEFICIARY_NAME = "snack_location";
        public static final String COLUMN_BENEFICIARY_EMAIL = "snack_item1";
        public static final String COLUMN_BENEFICIARY_ADDRESS = "snack_item2";
        public static final String COLUMN_BENEFICIARY_COUNTRY = "snack_item3";
    }
}