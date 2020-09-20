package Utilities;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCUtilsTest {
    public static void main(String[] args) throws SQLException {
        JDBCUtils.establishConnection();
        List<Map<String, Object>> data = JDBCUtils.runQuery("SELECT FIRST_NAME, LAST_NAME, SALARY FROM EMPLOYEES WHERE SALARY > 1000");

        System.out.println(data.get(0).get("FIRST_NAME"));

        for (int i = 0; i<data.size(); i++){
            System.out.println("index: " + i +" "+ data.get(i).get("SALARY"));
        }
    }
}
