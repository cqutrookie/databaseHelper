package com.databaseHelper.config;

/**
 * @author Lei X Y
 * {@code @date} 2025/5/20 17:23
 */
public interface DatabaseConfig {
    String getConnectionUrl();
    String getUsername();
    String getPassword();
    String getDatabaseType();
    boolean testConnection();
    void saveConfig();
    void connect();
}
