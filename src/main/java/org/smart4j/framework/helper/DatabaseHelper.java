package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库操作助手类
 * @author Ther
 */
public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    public static void beginTransaction() {
        Connection connection = DBUtil.getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.error("begin transaction failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    public static void commitTransaction() {
        Connection connection = DBUtil.getConnection();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeConnection();
            }
        }
    }

    public static void rollbackTransaction() {
        Connection connection = DBUtil.getConnection();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("rollback transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                DBUtil.closeConnection();
            }
        }
    }
}
