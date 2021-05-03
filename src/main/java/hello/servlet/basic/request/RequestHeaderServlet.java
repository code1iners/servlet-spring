package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {

        System.out.println("--- REQUEST LINE START ---");
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("--- REQUEST LINE END ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {

        System.out.println("--- Headers START ---");

        // note. old style.
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + headerName);
//        }

        // note. new style.
        request
                .getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName));

        String host = request.getHeader("host");    // note. Could catch each values.

        System.out.println("--- Headers END ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {

        System.out.println("--- Header convenience look up START ---");
        System.out.println("[Host convenience look up]");
        System.out.println("request.getServerName() = " + request.getServerName()); // note. host header name
        System.out.println("request.getServerPort() = " + request.getServerPort()); // note. host header port
        System.out.println();

        System.out.println("[Accept-Language convenience look up]");
        request
                .getLocales()
                .asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[Cookie convenience look up]");
        if (request.getCookies() != null) {
            for (Cookie cookie: request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content convenience look up]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("--- Header utils END ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {

        System.out.println("--- Etc look up START ---");
        System.out.println("[Remote information]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local information]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println("--- Etc look up END ---");
        System.out.println();
    }

}
