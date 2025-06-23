package provided.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;

public abstract class DaoBase {
    protected void startTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    protected void commitTransaction(Connection conn) throws SQLException {
        conn.commit();
        conn.setAutoCommit(true);
    }

    protected void rollbackTransaction(Connection conn) throws SQLException {
        conn.rollback();
        conn.setAutoCommit(true);
    }

    protected void setParameter(PreparedStatement stmt, int parameterIndex, Object value, Class<?> type) throws SQLException {
        if (value == null) {
            stmt.setNull(parameterIndex, java.sql.Types.NULL);
        } else if (type == String.class) {
            stmt.setString(parameterIndex, (String) value);
        } else if (type == Integer.class) {
            stmt.setInt(parameterIndex, (Integer) value);
        } else if (type == java.math.BigDecimal.class) {
            stmt.setBigDecimal(parameterIndex, (java.math.BigDecimal) value);
        } else {
            throw new DbException("Unsupported parameter type: " + type.getName());
        }
    }

    protected Integer getLastInsertId(Connection conn, String tableName) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new DbException("Unable to retrieve last insert ID.");
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> T extract(ResultSet rs, Class<T> clazz) throws SQLException {
        if (clazz == Project.class) {
            Project project = new Project();
            project.setProjectId(rs.getInt("project_id"));
            project.setProjectName(rs.getString("project_name"));
            project.setEstimatedHours(rs.getBigDecimal("estimated_hours"));
            project.setActualHours(rs.getBigDecimal("actual_hours"));
            project.setDifficulty(rs.getObject("difficulty", Integer.class));
            project.setNotes(rs.getString("notes"));
            return (T) project;
        } else if (clazz == Material.class) {
            Material material = new Material();
            material.setMaterialId(rs.getInt("material_id"));
            material.setProjectId(rs.getInt("project_id"));
            material.setMaterialName(rs.getString("material_name"));
            material.setNumRequired(rs.getObject("num_required", Integer.class));
            material.setCost(rs.getBigDecimal("cost"));
            return (T) material;
        } else if (clazz == Step.class) {
            Step step = new Step();
            step.setStepId(rs.getInt("step_id"));
            step.setProjectId(rs.getInt("project_id"));
            step.setStepText(rs.getString("step_text"));
            step.setStepOrder(rs.getInt("step_order"));
            return (T) step;
        } else if (clazz == Category.class) {
            Category category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            return (T) category;
        }
        throw new DbException("Unsupported class for extraction: " + clazz.getName());
    }
}