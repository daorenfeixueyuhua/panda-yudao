package cn.daoren.jdbc;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import lombok.SneakyThrows;

import java.util.Properties;

/**
 * @author peng_da
 * @since 2023/7/12 17:05
 */
public class JDBCDemo {
    @SneakyThrows
    public static void main(String[] args) {
        final Driver driver = new Driver();
        String url = "jdbc:mysql://daoren:30336/datasource_pool_dataset?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        final Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        final ConnectionImpl connect = (ConnectionImpl) driver.connect(url, properties);
        System.out.println(connect);
        String sql = "select * from sys_user ;";
        final ClientPreparedStatement statement = (ClientPreparedStatement) connect.prepareStatement(sql);
        final ResultSetImpl resultSet = (ResultSetImpl) statement.executeQuery();
        while (resultSet.next()) {
            final int id = resultSet.getInt("id");
            final String username = resultSet.getString("username");
            final String password = resultSet.getString("password");
            System.out.println("id=" + id + "&username=" + username + "&password=" + password);
        }
        connect.close();
    }
}
