package testutil;

import br.com.spherams.database.DaoException;

public class AnyValues {

    public static String string() {
        return "string";
    }

    public static short anyShort() {
        return 4;
    }

    public static DaoException daoException() {
        return new DaoException(string(), new RuntimeException());
    }
}
