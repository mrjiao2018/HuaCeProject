import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset = utf-8");

        //从前端接受字符串
        String input = req.getParameter("input");
        System.out.println(input);

        //查询
        SQL sql = new SQL();
        CityInformation city = sql.searchResult(input);

        //TEST
        System.out.println(city);
    }
}
