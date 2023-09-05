package task.beautifulcode.servlets;

import task.beautifulcode.JsonRequest;
import task.beautifulcode.Response;
import task.beautifulcode.models.Data;
import task.beautifulcode.service.CheckBracketsData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/checkBrackets")
public class CheckBrackets extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        new Response(
            new CheckBracketsData(
                new JsonRequest<Data>(req).get(Data.class))
        ).send(resp);
    }
}
