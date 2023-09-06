package task.beautifulcode.servlet;

import task.beautifulcode.JsonRequest;
import task.beautifulcode.JsonResponse;
import task.beautifulcode.model.Data;
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
        new JsonResponse(
            new CheckBracketsData(
                new JsonRequest<Data>(req).get(Data.class))
        ).send(resp);
    }
}
