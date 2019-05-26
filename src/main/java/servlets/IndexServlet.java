package servlets;

import entity.News;
import repository.NewsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "IndexServlet",
        urlPatterns = "/"
)
public class IndexServlet extends HttpServlet {
        private NewsRepository newsRepository;

    public IndexServlet() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("OrmExample");
        newsRepository = new NewsRepository(factory.createEntityManager());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        List<News> allnews = newsRepository.getAllNews();
        req.setAttribute("allnews", allnews);
        view.forward(req,resp);

    }
}
