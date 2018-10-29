package androidproject.example.com.food.Database.Tables;

import android.provider.BaseColumns;

public class BreakContract {

    public static final class BeneficiaryEntry implements BaseColumns {

        public static final String TABLE_NAME = "beneficiary";
        public static final String COLUMN_DATE = "break_date";
        public static final String COLUMN_BENEFICIARY_NAME = "break_location";
        public static final String COLUMN_BENEFICIARY_EMAIL = "break_item1";
        public static final String COLUMN_BENEFICIARY_ADDRESS = "break_item2";
        public static final String COLUMN_BENEFICIARY_COUNTRY = "break_item3";
    }
}