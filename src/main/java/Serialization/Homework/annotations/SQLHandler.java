package Serialization.Homework.annotations;

import java.lang.reflect.Field;

public class SQLHandler {

    public <T> String createSQLTable(Class<T> type, boolean withParameter) {
        return type.getAnnotation(Table.class).table() +
                "(" +
                findID(type.getDeclaredFields(), withParameter) +
                findColumns(type.getDeclaredFields(), withParameter) +
                ")";
    }

    private String findColumns(Field[] fields, boolean withParameter) {
        StringBuilder sb = new StringBuilder();
        for (Field f : fields) {
            var annotation = f.getAnnotation(Column.class);
            if (annotation != null) {
                sb.append(", ");
                if (f.getType().equals(long.class) || f.getType().equals(int.class)) {
                    sb.append(annotation.column()).append(withParameter ? " BIGINT" : "");
                } else if (f.getType().equals(double.class) || f.getType().equals(float.class)) {
                    sb.append(annotation.column()).append(withParameter ? " DOUBLE PRECISION" : "");
                } else {
                    sb.append(annotation.column()).append(withParameter ? " VARCHAR(256)" : "");
                }
            }
        }
        return sb.toString();
    }

    private String findID(Field[] fields, boolean withParameter) {
        for (Field f : fields) {
            if (f.getAnnotation(Id.class) != null) {
                if (f.getAnnotation(Id.class).autoIncrement()) {
                    return "id " + (withParameter ? "BIGINT AUTO_INCREMENT" : "");
                } else {
                    return "id " + (withParameter ? "BIGINT" : "");
                }
            }
        }
        throw new RuntimeException("id field not found");
    }
}