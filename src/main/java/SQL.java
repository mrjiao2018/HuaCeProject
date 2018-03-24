import vo.CityInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jinglin Chen
 * @Description:
 * @Date: 16:04 2018/3/23
 * @param:  * @param null
 */
public class SQL {
    private static final String URL = "jdbc:sqlserver://LAPTOP-DO412DJ1\\SQLEXPRESS:1433;database=AirDataBase";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "lzb529673457";
    private Connection conn = null;
    //具体城市查询
    public List<CityInformation>  searchResult(String cityName) {
        List<CityInformation> cityInfo = new ArrayList<CityInformation>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            //查询
            String selectSQL = "select * from DayRecord where city = '" + cityName+"'";
            System.out.println(selectSQL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);
            //输出结果
            while(rs.next()) {
                CityInformation tempCity = new CityInformation();
                tempCity.setAQI(rs.getString("AQI"));
                tempCity.setPollutant(rs.getString("pollutant"));
                tempCity.setRecordDate(rs.getString("recordDate"));
                tempCity.setAirQuality(rs.getString("airQuality"));
                cityInfo.add(tempCity);
            }
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

    //AQI排序
    public List<CityInformation> orderedAQI ()
    {
        List<CityInformation> AQICity = new ArrayList<CityInformation>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            //排序
            String AQIOrderSQL = "select top 20 * from AirDataBase by AQI desc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(AQIOrderSQL);
            while(rs.next())
            {
                CityInformation cityInfo = new CityInformation();
                cityInfo.setName(rs.getString("city"));
                cityInfo.setAQI(rs.getString("AQI"));
                cityInfo.setPollutant(rs.getString("pollutant"));
                cityInfo.setRecordDate(rs.getString("recordDate"));
                cityInfo.setAirQuality(rs.getString("airQuality"));
                AQICity.add(cityInfo);
            }
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

        return AQICity;
    }
}