package persistence.utils;


import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class SqlGenerator<T, E extends Entity<T>> {

    private Class<E> clazz;

    public void setClazz(Class<E> clazz) {
        this.clazz = clazz;
    }

    public String generateInsertSql() {
        String tableName = extractTableName(clazz);
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }

            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name()).append(", ");
                values.append("?, ");
            }
        }

        removeTrailingComma(columns);
        removeTrailingComma(values);

        return String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);
    }

    public String generateUpdateSql() {
        String tableName = extractTableName(clazz);
        StringBuilder columns = new StringBuilder();
        String idColumn = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                Column column = field.getAnnotation(Column.class);
                idColumn = column.name();
                continue;
            }

            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name()).append(" = ?, ");
            }
        }

        removeTrailingComma(columns);
        return String.format("UPDATE %s SET %s WHERE %s = ?", tableName, columns, idColumn);
    }

    public String generateDeleteSql() {
        String tableName = extractTableName(clazz);
        String idColumn = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                Column column = field.getAnnotation(Column.class);
                idColumn = column.name();
                break;
            }
        }

        return String.format("DELETE FROM %s WHERE %s = ?", tableName, idColumn);
    }

    public String generateSelectSql() {
        String tableName = extractTableName(clazz);
        StringBuilder columns = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columns.append(column.name()).append(", ");
            }
        }

        removeTrailingComma(columns);
        return String.format("SELECT %s FROM %s", columns, tableName);
    }

    public void populateStatementWithValues(E entity, PreparedStatement stmt) throws SQLException, IllegalAccessException {
        int parameterIndex = 1;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }

            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                stmt.setObject(parameterIndex++, getValueByFieldType(entity, field));
            }
        }
    }

    private String extractTableName(Class<?> clazz) {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null || tableAnnotation.name().isEmpty()) {
            throw new IllegalArgumentException("Entity must have a @Table annotation with a name.");
        }
        return String.format("%s.%s", tableAnnotation.schema(), tableAnnotation.name());
    }

    private void removeTrailingComma(StringBuilder sb) {
        if (!sb.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
    }

    private Object getValueByFieldType(E entity, Field field) throws IllegalAccessException {
        Object value = field.get(entity);

        if (value instanceof Enum<?>) {
            return ((Enum<?>) value).name();
        }

        if (value instanceof Entity<?>) {
            Field idField = Arrays.stream(value.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Entity must have an @Id field."));

            idField.setAccessible(true);
            return idField.get(value);
        }

        return value;
    }


    public String generateSelectByIdSql() {
        String tableName = extractTableName(clazz);
        String idColumn = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                Column column = field.getAnnotation(Column.class);
                idColumn = column.name();
                break;
            }
        }

        return String.format("SELECT * FROM %s WHERE %s = ?", tableName, idColumn);
    }
}
