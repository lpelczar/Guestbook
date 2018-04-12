package com.codecool.guestbook;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Guestbook implements HttpHandler {

    private List<Note> notes = new ArrayList<>();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/template.twig");
            JtwigModel model = JtwigModel.newModel();

            notes.add(new Note("blafgdbla", "bladfblablabla"));
            notes.add(new Note("blabdfla", "blabdflablabla"));

            model.with("notes", notes);

            // render a template to a string
            String response = template.render(model);

            // send the results to a the client
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}
