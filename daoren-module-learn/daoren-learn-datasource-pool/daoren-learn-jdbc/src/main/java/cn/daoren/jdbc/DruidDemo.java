package cn.daoren.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author peng_da
 * @since 2023/7/12 17:28
 */
public class DruidDemo {
    @SneakyThrows
    public static void main(String[] args) {
        final DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://daoren:30336/datasource_pool_dataset?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        final DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection: " + connection);
        String sql = "select * from sys_user ;";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String username = resultSet.getString("username");
            final String password = resultSet.getString("password");
            System.out.println("id=" + id + "&username=" + username + "&password=" + password);
        }
        connection.close();
    }
}
