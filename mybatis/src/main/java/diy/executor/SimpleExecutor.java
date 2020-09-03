package diy.executor;

import diy.session.Configuration;
import diy.session.DataSource;
import diy.statement.MapperStatement;
import diy.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 简单执行器 查询数据库实现
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration) {
        super(configuration);
    }

    @Override
    protected <E> List<E> doQuery(MapperStatement ms, Object parameter) {
        System.out.println(">>>>>查询数据库<<<<<<" + ms.getSqlStatement() + " " + (parameter != null && parameter.getClass().isArray() ? StringUtil.getStringByArray((Object[])parameter,"") : parameter));
        DataSource dataSource = configuration.getDataSource();
        List<E> list = new ArrayList<>();
        try {
            // 1.获取连接
            Connection connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            // 2.获取预编译
            PreparedStatement preparedStatement = connection.prepareStatement(ms.getSqlStatement());
            // 3.设置预编译参数
            boolean array = parameter != null && parameter.getClass().isArray();
            if (array) {
                Object[] parameterArray = (Object[]) parameter;
                for (int i = 0; i < parameterArray.length; i++) {
                    preparedStatement.setObject(i + 1, parameterArray[i]);
                }
            } else if (parameter != null){
                preparedStatement.setObject(1, parameter);
            }
            // 4.执行sql
            preparedStatement.execute();

            // 5.封装结果
            ResultSet resultSet = preparedStatement.getResultSet();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Object result = ms.getResultType().getDeclaredConstructor().newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取列名
                    String columnName = metaData.getColumnName(i + 1);
                    // 获取列值
                    Object columnValue = resultSet.getObject(columnName);
                    Field declaredField = ms.getResultType().getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(result, columnValue);
                }
                list.add((E)result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }
}
