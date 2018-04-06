package nl.joelchrist.spotitube.dao.util;

import org.jongo.MongoCursor;

import java.util.ArrayList;
import java.util.List;

public class MongoUtil {
    public static <T> List<T> cursorToList(MongoCursor<T> cursor) {
        List<T> list = new ArrayList<>();
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }
}
