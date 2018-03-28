

import org.json.JSONObject;
import vo.CityInformation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyServlet extends HttpServlet {
    private void sendInfo(CityInformation city, HttpServletRequest resp) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", city.getName());
        jsonObject.put("AQI",city.getAQI());
        jsonObject.put("AirQuality", city.getAirQuality());
        jsonObject.put("Pollutant",city.getPollutant());
        jsonObject.put("RecordDate", city.getRecordDate());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset = utf-8");

        //从前端接受字符串
        String input = req.getParameter("input");
        System.out.println(input);

        //查询
//        SQL sql = new SQL();
//        List<CityInformation> cities = sql.searchResult(input);

        //将CityInformation返回到index.jsp
//        req.setAttribute("cities", cities);

        //测试代码
        List<CityInformation> cities = new ArrayList<CityInformation>();
        for (int i = 0; i < 30; ++i) {
            CityInformation city = new CityInformation();
            city.setName("北京市");
            city.setAirQuality("优");
            city.setAQI("" + i);
            city.setPollutant("pm2.5");
            city.setRecordDate("2018-03-" + (i + 1));
            cities.add(city);
        }
        req.setAttribute("cities", cities);
        req.getRequestDispatcher("/showResults.jsp").forward(req, resp);
    }
}
