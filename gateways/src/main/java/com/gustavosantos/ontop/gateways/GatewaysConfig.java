package com.gustavosantos.ontop.gateways;

import com.gustavosantos.ontop.gateways.payment.PaymentsClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewaysConfig {

    @Bean
    public PaymentsClient paymentsClient(@Value("${api.payments.url}") String url) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(PaymentsClient.class, url);
    }

}
