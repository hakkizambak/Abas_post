package org.example;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException{

        try {

            HashMap<String, Object> applicantData = new HashMap<>();
            applicantData.put("GPA", 3.5);
            applicantData.put("Age", 24);
            applicantData.put("Experienced", "Yes");

            Applicant applicant = new Applicant("Hakkı", "Zambak", applicantData);


            Gson gson = new Gson();
            String jsonInputString = gson.toJson(applicant);

            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(jsonInputString);
                dos.flush();
            }


            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = bf.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response Body: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
