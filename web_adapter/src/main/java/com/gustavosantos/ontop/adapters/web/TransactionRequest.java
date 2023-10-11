package com.gustavosantos.ontop.adapters.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionRequest(@JsonProperty("user_id") Long userId,
                                 BigDecimal amount) {
}
