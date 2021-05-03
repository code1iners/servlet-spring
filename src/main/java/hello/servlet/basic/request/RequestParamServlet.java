package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. Featured Parameter Transfer.
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("RequestParamServlet.service");

        // note. Look up all parameters.
        System.out.println("--- All parameters look up START ---");
        request
                .getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));

        System.out.println("--- All parameters look up END ---");
        System.out.println();

        System.out.println("--- Single Parameter look up START ---");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("--- Single Parameter look up END ---");
        System.out.println();

        System.out.println("--- Same parameter look up START ---");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        System.out.println("--- Same parameter look up END ---");
        System.out.println();

        response.getWriter().write("OK");
    }
}
