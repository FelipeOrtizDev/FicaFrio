package com.ficafrio.ficafrio.services.GPT_API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ficafrio.ficafrio.agenda.agendaEntity;
import com.ficafrio.ficafrio.services.agendaServices.agendaRequestDTO;
import com.ficafrio.ficafrio.tarefa.tarefaEntity;

public class chatGPT_API {

    public static String chatGPT(String string) throws Exception {
        try {
            String apiKey = "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";
            String urlString = "https://api.openai.com/v1/chat/completions";
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);

            JSONObject data = new JSONObject();
            data.put("model", "gpt-3.5-turbo-1106");
            data.put("messages", createListaJSON(string));
            data.put("max_tokens", 4000);
            data.put("n", 1);
            data.put("stop", "None");
            data.put("temperature", 0.4);

            con.setDoOutput(true);
            con.getOutputStream().write(data.toString().getBytes());

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                        .reduce((a, b) -> a + b).orElse("");

                String result = replaceEscapes(extrairJson(new JSONObject(output).getJSONArray("choices").getJSONObject(0)
                        .getJSONObject("message").getString("content")));

                // JSONObject resultObject = new JSONObject();
                // resultObject.put("tarefas_fk", result);
                // System.out.println(resultObject);
                return result;
            } else {
                System.out.println("Erro na solicitação. Código de resposta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray createListaJSON(String string) {

        JSONArray arrayListaJSON = new JSONArray() {
        };

        arrayListaJSON.put(new JSONObject().put("role", "system").put("content", "Você é um gestor de agenda."));
        arrayListaJSON.put(new JSONObject().put("role", "user").put("content",
                "Organize automaticamente uma agenda mensal para uma pessoa, equilibrando tarefas consideradas por você como leves e pesadas com foco na saúde mental. A IA deve identificar a prioridade com base no contexto das tarefas. Não é necessário especificar horários, mas é importante incluir uma tarefa leve e uma tarefa pesada no mesmo dia. "));
        arrayListaJSON.put(new JSONObject().put("role", "user").put("content", string));
        arrayListaJSON.put(new JSONObject().put("role", "user").put("content",
                "Fornecer o resultado em um array em Json dentro do seguinte padrão para cada tarefa:\r\n"
                        + //
                        "\r\n" + //
                        "{\r\n" + //
                        "    \"nometarefa\": \"\",\r\n" + //
                        "    \"descricaotarefa\": \"\",\r\n" + //
                        "    \"dataentrega\": \"YYYY-MM-DD\",\r\n" + //
                        "}"));
        arrayListaJSON.put(new JSONObject().put("role", "assistant").put("content",
                "Retorne somente o arquivo JSON, para que eu possa convertelo em meu BACKEND e utilizalo."));

        return arrayListaJSON;
    }

    public static String replaceEscapes(String input) {
        String espacoConverter = input.replaceAll("\n", " ");

        String aspasConverter = espacoConverter.replace("\\\"", ".");

        String inicioConverter = aspasConverter.replaceAll("\r", "");

        return inicioConverter;
    }

    public static String extrairNomesTarefasDeAgenda(agendaRequestDTO data) {
        JSONArray todasTarefas = new JSONArray();

        for (tarefaEntity tarefa : data.tarefas_fk()) {
            JSONObject umaTarefa = new JSONObject();
            umaTarefa.put("nome tarefa", tarefa.getNometarefa());

            if (tarefa.getDataentrega() != null) {
                umaTarefa.put("Data limite entrega", tarefa.getDataentrega());
            }
            todasTarefas.put(umaTarefa);
        }
        return todasTarefas.toString();
    }

    private static String extrairJson(String texto) {
        try {
            int inicioJson = texto.indexOf("```json") + "```json".length();
            int fimJson = texto.lastIndexOf("```");

            String jsonTexto = texto.substring(inicioJson, fimJson).trim();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonTexto);
            String jsonFormatado = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            return jsonFormatado;

        } catch (Exception e) {
            System.out.println("Erro ao extrair JSON: " + e.getMessage());
            return null;
        }
    }

    public static agendaEntity transformarEntidade(JSONObject data) {
        // WIP para converter Json para Tarefa para Agenda 

        agendaEntity agendaTarefasOrganizadaUpdate = new agendaEntity();

        agendaTarefasOrganizadaUpdate.getTarefas_fk();

        return agendaTarefasOrganizadaUpdate;
    }
}
