package com.devsenior.colmena.pruebas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

// La matriz de ataques de la Clase 4, convertida en pruebas que corren solas.
// Cada @Test es un ataque: lo que intentaria alguien que no deberia entrar.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SeguridadTokensTest {

    @Value("${local.server.port}")
    private int puerto;

    private final HttpClient http = HttpClient.newHttpClient();
    private final JsonMapper json = JsonMapper.builder().build();

    // ---------- Ayudas: iniciar sesion y pedir una ruta con un token ----------

    private String token(String email) throws Exception {
        String cuerpo = "{\"email\":\"" + email + "\",\"password\":\"colmena123\"}";
        HttpRequest peticion = HttpRequest.newBuilder(URI.create("http://localhost:" + puerto + "/api/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(cuerpo))
                .build();
        return json.readTree(http.send(peticion, HttpResponse.BodyHandlers.ofString()).body()).get("token").asString();
    }

    private int estadoDe(String ruta, String token) throws Exception {
        HttpRequest peticion = HttpRequest.newBuilder(URI.create("http://localhost:" + puerto + ruta))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();
        return http.send(peticion, HttpResponse.BodyHandlers.discarding()).statusCode();
    }

    // ---------- Los ataques ----------

    @Test
    void elTokenVerdaderoDeLauraEntra() throws Exception {
        assertEquals(200, estadoDe("/api/espacios", token("laura@correo.com")),
                "Control: con su token verdadero, Laura deberia ver los espacios");
    }

    @Test
    void lauraEscribeLaRutaDelPanelAMano() throws Exception {
        assertEquals(403, estadoDe("/api/reportes/resumen", token("laura@correo.com")),
                "Laura no tiene REPORTES_VER: aunque escriba la ruta, deberia recibir 403");
    }

    @Test
    void unTokenConElPayloadCambiadoResponde401() throws Exception {
        String[] partes = token("laura@correo.com").split("\\.");
        String payloadFalso = base64("{\"sub\":\"admin@colmena.co\",\"permisos\":[\"REPORTES_VER\"]}");
        String adulterado = partes[0] + "." + payloadFalso + "." + partes[2];
        assertEquals(401, estadoDe("/api/reportes/resumen", adulterado),
                "Cambiamos el payload sin volver a firmar: la firma ya no coincide y deberia responder 401");
    }

    @Test
    void unTokenSinFirmaResponde401() throws Exception {
        String sinFirma = base64("{\"alg\":\"none\"}") + "." + base64("{\"sub\":\"admin@colmena.co\"}") + ".";
        assertEquals(401, estadoDe("/api/reportes/resumen", sinFirma),
                "Un token con alg none no trae firma: deberia responder 401");
    }

    private String base64(String texto) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(texto.getBytes(StandardCharsets.UTF_8));
    }
}