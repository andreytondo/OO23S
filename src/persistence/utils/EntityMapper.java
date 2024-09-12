package persistence.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EntityMapper<T, E extends Entity<T>> {

    private static final Logger LOGGER = Logger.getLogger(EntityMapper.class.getName());

    public void mapResultSetToEntity(ResultSet rs, E entity) throws SQLException, IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                String columnName = field.getAnnotation(Column.class).name();
                Object value = rs.getObject(columnName);

                if (value != null) {
                    setValue(value, field, entity);
                }
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    void setValue(Object value, Field field, E entity) throws IllegalAccessException {
        if (field.getType().isEnum()) {
            Class<? extends Enum> enumType = (Class<? extends Enum>) field.getType();
            field.set(entity, Enum.valueOf(enumType, value.toString().toUpperCase()));
            return;
        }

        if (Entity.class.isAssignableFrom(field.getType())) {
            Entity entityValue = getEntityValue(field, (T) value);
            field.set(entity, entityValue);
            return;
        }

        field.set(entity, value);
    }

    @SuppressWarnings("unchecked")
    Entity<T> getEntityValue(Field field, T value) {
        try {
            Entity<T> entity = (Entity<T>) field.getType().getDeclaredConstructor().newInstance();
            entity.setId(value);
            return entity;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.severe("Failed to instantiate entity: " + field.getType().getSimpleName());
            return null;
        }
    }

}