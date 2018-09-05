package ua.training.model.dao.implementation;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    private Properties properties = new Properties();
    static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("com.mysql.jdbc.Driver");
                    ds.setUrl("jdbc:mysql://localhost:3306/campaign");
                    ds.setUsername("root");
                    ds.setPassword("nonstop$2018");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
