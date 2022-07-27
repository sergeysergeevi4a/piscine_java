package app;

import models.OrmColumn;
import models.OrmColumnId;
import models.OrmEntity;
import org.reflections.Reflections;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrmManager {
    private Connection connection;
    public OrmManager(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            if (connection == null) {
                System.err.println("Error: can't connection to DB");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.err.println("Error: can't connection to DB");
            System.exit(1);
        }
        init();
    }

    private void init() {
        Reflections reflections = new Reflections("models");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(OrmEntity.class);
        List<String> classes = set.stream()
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
        for (String aClass : classes) {
            try {
                Class<?> clazz = Class.forName(aClass);
                OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
                String dropper = "DROP TABLE IF EXISTS " + ormEntity.table() + " CASCADE";
                System.out.println(dropper);
                connection.createStatement().execute(dropper);
                Field[] fields = clazz.getDeclaredFields();
                StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
                sb.append(ormEntity.table()).append(" (");
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    if (field.isAnnotationPresent(OrmColumn.class)) {
                        OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                        String type = field.getType().getSimpleName().toUpperCase();
                        sb.append(ormColumn.name());
                        switch (type) {
                            case "STRING":
                                sb.append(" varchar(")
                                        .append(ormColumn.length())
                                        .append(")");
                                break;
                            case "INTEGER":
                                sb.append(" ").append(type);
                                break;
                            case "LONG":
                                sb.append(" BIGINT");
                                break;
                            case "DOUBLE":
                                sb.append(" DOUBLE PRECISION");
                                break;
                            case "BOOLEAN":
                                sb.append(" BOOLEAN");
                                break;
                        }
                    }
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        sb.append(field.getName()).append(" SERIAL PRIMARY KEY");
                    }
                    if (i != fields.length - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(")");
                System.out.println(sb);
                connection.createStatement().execute(sb.toString());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Object entity) {
        Class<?> clazz = entity.getClass();
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder sb = new StringBuilder("INSERT INTO ");
            sb.append(ormEntity.table()).append(" (");
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(OrmColumn.class)) {
                    OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                    sb.append(ormColumn.name());
                }
                if (i != fields.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(") VALUES (");
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                extracted(entity, fields, sb, i, field);
            }
            sb.append(")");
            System.out.println(sb);
            try {
                connection.createStatement().execute(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Object entity) {
        Class<?> clazz = entity.getClass();
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = clazz.getAnnotation(OrmEntity.class);
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(ormEntity.table()).append(" SET ");
            Object id = null;

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(OrmColumn.class)) {
                    OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);
                    sb.append(ormColumn.name());
                    sb.append("=");
                    extracted(entity, fields, sb, i, field);
                }

                if (field.isAnnotationPresent(OrmColumnId.class)) {
                    field.setAccessible(true);
                    try {
                        id = field.get(entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    field.setAccessible(false);
                }
            }
            sb.append(" WHERE id=").append(id);
            System.out.println(sb);
            try {
                connection.createStatement().executeUpdate(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void extracted(Object entity, Field[] fields, StringBuilder sb, int i, Field field) {
        if (field.getType().getSimpleName().equalsIgnoreCase("String")) {
            sb.append("'");
        }
        field.setAccessible(true);
        try {
            sb.append(field.get(entity));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(false);
        if (field.getType().getSimpleName().equalsIgnoreCase("String")) {
            sb.append("'");
        }
        if (i != fields.length - 1) {
            sb.append(", ");
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = aClass.getAnnotation(OrmEntity.class);
            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            sb.append(ormEntity.table())
                    .append(" WHERE id=")
                            .append(id);
            System.out.println(sb);

            try {
                ResultSet set = connection.createStatement().executeQuery(sb.toString());
                if (set.next()) {
                    T t = aClass.newInstance();
                    Field[] fields = aClass.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(OrmColumnId.class)) {
                            field.set(t, set.getLong("id"));
                        }
                        if (field.isAnnotationPresent(OrmColumn.class)) {
                            OrmColumn ormColumn = field.getAnnotation(OrmColumn.class);

                            field.set(t, set.getObject(ormColumn.name()));
                        }
                        field.setAccessible(false);
                    }
                    return t;
                }
            } catch (SQLException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
