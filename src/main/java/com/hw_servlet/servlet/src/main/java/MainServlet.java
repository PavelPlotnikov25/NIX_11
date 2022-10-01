import model.Request;
import repository.RequestRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/servlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestRepository requestRepository = RequestRepository.getInstance();
        Request request = new Request(req.getRemoteAddr(), req.getHeader("user-agent"), LocalDateTime.now());
        requestRepository.save(request);
        req.setAttribute("repository", requestRepository.getAll());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
