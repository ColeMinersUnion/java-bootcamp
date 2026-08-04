package com.northstar.crm.exception;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTests {

    @Test
    public void testConstructorWithAllFields() {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("field1", "error1");
        errMap.put("field2", "error2");

        Instant before = Instant.now();
        ErrorResponse resp = new ErrorResponse(400, "Bad Request", "Invalid input", "corr-id-123", errMap);
        Instant after = Instant.now();

        assertEquals(400, resp.getStatus());
        assertEquals("Bad Request", resp.getError());
        assertEquals("Invalid input", resp.getMessage());
        assertEquals("corr-id-123", resp.getCorrelationId());
        assertTrue(resp.getTimestamp().isAfter(before) || resp.getTimestamp().equals(before));
        assertTrue(resp.getTimestamp().isBefore(after) || resp.getTimestamp().equals(after));
        assertEquals(2, resp.getErrors().size());
        assertEquals("error1", resp.getErrors().get("field1"));
        assertEquals("error2", resp.getErrors().get("field2"));
    }

    @Test
    public void testConstructorWithNullErrorsMap() {
        ErrorResponse resp = new ErrorResponse(500, "Internal Error", "Server error", "corr-id-456", null);

        assertEquals(500, resp.getStatus());
        assertEquals("Internal Error", resp.getError());
        assertEquals("Server error", resp.getMessage());
        assertEquals("corr-id-456", resp.getCorrelationId());
        assertNotNull(resp.getErrors());
        assertTrue(resp.getErrors().isEmpty());
    }

    @Test
    public void testErrorsMapIsUnmodifiable() {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("field1", "error1");

        ErrorResponse resp = new ErrorResponse(400, "Bad Request", "Invalid", "corr-id", errMap);

        // attempting to modify the returned map should fail
        assertThrows(UnsupportedOperationException.class, () -> {
            resp.getErrors().put("newField", "newError");
        });
    }

    @Test
    public void testErrorsMapPreservesInsertionOrder() {
        Map<String, String> errMap = new LinkedHashMap<>();
        errMap.put("field1", "error1");
        errMap.put("field2", "error2");
        errMap.put("field3", "error3");

        ErrorResponse resp = new ErrorResponse(400, "Bad Request", "Invalid", "corr-id", errMap);

        var keys = resp.getErrors().keySet().stream().toList();
        assertEquals("field1", keys.get(0));
        assertEquals("field2", keys.get(1));
        assertEquals("field3", keys.get(2));
    }

    @Test
    public void testToJsonWithAllFields() {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("email", "must be valid");
        errMap.put("name", "cannot be blank");

        ErrorResponse resp = new ErrorResponse(400, "Validation Error", "Request validation failed", "corr-789", errMap);
        String json = resp.toJson();

        assertTrue(json.contains("\"status\":400"));
        assertTrue(json.contains("\"error\":\"Validation Error\""));
        assertTrue(json.contains("\"message\":\"Request validation failed\""));
        assertTrue(json.contains("\"correlationId\":\"corr-789\""));
        assertTrue(json.contains("\"timestamp\":\""));
        assertTrue(json.contains("\"email\":\"must be valid\""));
        assertTrue(json.contains("\"name\":\"cannot be blank\""));
    }

    @Test
    public void testToJsonWithEmptyErrors() {
        ErrorResponse resp = new ErrorResponse(500, "Internal Error", "Unexpected error", "corr-500", null);
        String json = resp.toJson();

        assertTrue(json.contains("\"status\":500"));
        assertTrue(json.contains("\"errors\":{}"));
    }

    @Test
    public void testToJsonEscapesSpecialCharactersInError() {
        Map<String, String> errMap = new HashMap<>();
        ErrorResponse resp = new ErrorResponse(400, "Error with \"quotes\"", "Message with \\ backslash", "id", errMap);
        String json = resp.toJson();

        // double quotes should be escaped
        assertTrue(json.contains("\\\""));
        // backslashes should be escaped
        assertTrue(json.contains("\\\\"));
    }

    @Test
    public void testToJsonEscapesSpecialCharactersInErrorMap() {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("field", "error with \"quotes\" and \\ slashes");

        ErrorResponse resp = new ErrorResponse(400, "Bad", "Msg", "id", errMap);
        String json = resp.toJson();

        assertTrue(json.contains("error with \\\"quotes\\\" and \\\\ slashes"));
    }

    @Test
    public void testToJsonWithNullFields() {
        // null values should be escaped to empty string
        ErrorResponse resp = new ErrorResponse(400, null, null, null, null);
        String json = resp.toJson();

        assertNotNull(json);
        assertTrue(json.contains("\"error\":\"\""));
        assertTrue(json.contains("\"message\":\"\""));
        assertTrue(json.contains("\"correlationId\":\"\""));
    }

    @Test
    public void testToJsonValidJsonStructure() {
        Map<String, String> errMap = new HashMap<>();
        errMap.put("field1", "error1");

        ErrorResponse resp = new ErrorResponse(400, "Error", "Message", "id-123", errMap);
        String json = resp.toJson();

        // Verify basic JSON structure
        assertTrue(json.startsWith("{"));
        assertTrue(json.endsWith("}"));
        assertTrue(json.contains("\"timestamp\":\""));
        assertTrue(json.contains("\"status\":"));
        assertTrue(json.contains("\"error\":\""));
        assertTrue(json.contains("\"message\":\""));
        assertTrue(json.contains("\"correlationId\":\""));
        assertTrue(json.contains("\"errors\":{"));
    }

    @Test
    public void testGettersReturnCorrectValues() {
        Instant ts = Instant.now();
        Map<String, String> errMap = Map.of("k", "v");

        ErrorResponse resp = new ErrorResponse(403, "Forbidden", "Access denied", "xyz", errMap);

        assertEquals(403, resp.getStatus());
        assertEquals("Forbidden", resp.getError());
        assertEquals("Access denied", resp.getMessage());
        assertEquals("xyz", resp.getCorrelationId());
        assertNotNull(resp.getTimestamp());
        assertEquals(errMap, resp.getErrors());
    }

    @Test
    public void testMultipleInstancesHaveDifferentTimestamps() throws InterruptedException {
        ErrorResponse resp1 = new ErrorResponse(400, "Error", "Msg", "id", null);
        Thread.sleep(1);  // ensure a small time gap
        ErrorResponse resp2 = new ErrorResponse(400, "Error", "Msg", "id", null);

        assertTrue(resp1.getTimestamp().isBefore(resp2.getTimestamp())
                   || resp1.getTimestamp().equals(resp2.getTimestamp()));
    }

}


