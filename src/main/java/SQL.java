import javax.xml.transform.Result;
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
    private static final String URL = "DESKTOP-OCSELUV-OCSELUV\\SQLEPRESS;database=AirDataBase";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "lzb52967357";
    private Connection conn = null;
    //具体城市查询
    public CityInformation searchResult(String cityName) {
        CityInformation cityInfo = new CityInformation();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            //查询
            String selectSQL = "select * from AirDataBase.dbo.DayRecord where city = " + "'cityName'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);
            //输出结果
            cityInfo.setName(rs.getString("city"));
            cityInfo.setAQI(rs.getString("AQI"));
            cityInfo.setPollutant(rs.getString("pollutant"));
            cityInfo.setRecordDate(rs.getString("recordDate"));
            cityInfo.setAirQuality(rs.getString("airQuality"));
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
