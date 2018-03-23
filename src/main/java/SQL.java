import java.sql.*;

/**
* @Author: Jinglin Chen
* @Description:
* @Date: 16:04 2018/3/23
* @param:  * @param null
*/
public class SQL {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL = "";
    private static final String USER_NAME = "";
    private static final String PASSWORD = "";
    private Connection conn = null;
    private CityInformation searchResult(String cityName) {
        CityInformation cityInfo = new CityInformation();
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            //查询
            String selectSQL = "select * from AirDataBase.dbo.DayRecord where city = 'cityName'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);
            //输出结果
            cityInfo.setName(rs.getString("city"));
            cityInfo.setName(rs.getString("AQI"));
            cityInfo.setName(rs.getString("pollutant"));
            cityInfo.setName(rs.getString("recordData"));
            cityInfo.setName(rs.getString("airQuality"));
            rs.close();
            stmt.close();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cityInfo;
    }
}
