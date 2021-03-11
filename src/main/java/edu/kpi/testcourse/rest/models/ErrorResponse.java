package edu.kpi.testcourse.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("reason_code") int reasonCode, @JsonProperty("reason_text") String reasonText) {
}
