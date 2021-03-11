package edu.kpi.testcourse.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserSignupRequest(@JsonProperty("email") String email, @JsonProperty("password") String password) {
}
