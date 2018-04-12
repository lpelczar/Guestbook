package com.codecool.guestbook;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.*;

public class Guestbook implements HttpHandler {

    private List<Note> notes = new ArrayList<Note>() {{
        add(new Note("John Doe", "Hello there", LocalDateTime.now().toString()));
        add(new Note("John Wick",
                "It is a long established fact that a reader will be " +
                "distracted by the readable content of a page when looking at its layout.",
                LocalDateTime.now().toString()));
    }};

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            getResponse(httpExchange);
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            System.out.println(formData);
            notes.add(parseFormData(formData));
            getResponse(httpExchange);
        }


    }

    private void getResponse(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/template.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("notes", notes);
        String response = template.render(model);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Note parseFormData(String formData) throws UnsupportedEncodingException {

        final int NAME_INDEX = 0;
        final int MESSAGE_INDEX = 1;
        final int VALUE_INDEX = 1;

        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[VALUE_INDEX], "UTF-8"));
        }
        return new Note(values.get(NAME_INDEX), values.get(MESSAGE_INDEX), LocalDateTime.now().toString());
    }
}
