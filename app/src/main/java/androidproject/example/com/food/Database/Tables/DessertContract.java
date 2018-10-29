package androidproject.example.com.food.Database.Tables;

import android.provider.BaseColumns;

public class DessertContract {

    public static final class DessertEntry implements BaseColumns {

        public static final String TABLE_NAME = "dessert";
        public static final String COLUMN_DATE = "beneficiary_date";
        public static final String COLUMN_BENEFICIARY_NAME = "beneficiary_name";
        public static final String COLUMN_BENEFICIARY_EMAIL = "beneficiary_email";
        public static final String COLUMN_BENEFICIARY_ADDRESS = "beneficiary_address";
        public static final String COLUMN_BENEFICIARY_COUNTRY = "beneficiary_country";
    }
}
