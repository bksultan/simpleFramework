package Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@bkoracledatabase.ccfnfnikgzwz.us-east-2.rds.amazonaws.com:1521/ORCL",
                "bksultan",
                "#Widescreen5277"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

        resultSet.first();
        System.out.println(resultSet.getString("FIRST_NAME"));

        resultSet.next();
        System.out.println(resultSet.getString("FIRST_NAME"));

        resultSet.last();
        System.out.println(resultSet.getString("FIRST_NAME"));

        ResultSetMetaData rMetaData = resultSet.getMetaData();
        System.out.println(rMetaData.getColumnName(1));

        resultSet.beforeFirst();
        List<Map<String, Object>> data = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= rMetaData.getColumnCount(); i++) {
                map.put(rMetaData.getColumnName(i), resultSet.getObject(rMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        System.out.println(data.get(10).get("FIRST_NAME"));
        System.out.println(data.get(45).get("EMAIL"));

        for (int i = 0; i<data.size(); i++){
            //System.out.println("index: " + i +" "+ data.get(i).get("SALARY"));
        }

        for (int i = 0; i<data.size(); i++){;
            if (data.get(i).get("FIRST_NAME").toString().startsWith("A")){
                System.out.println("index: " + i +" "+ data.get(i).get("FIRST_NAME"));
            }
        }

        int sum = 0;
        for (int i = 0; i<data.size(); i++){
            String str = data.get(i).get("SALARY").toString();
            int price = Integer.parseInt(str);
            sum = sum + price / data.size();
        }
        System.out.println(sum);
    }
}
