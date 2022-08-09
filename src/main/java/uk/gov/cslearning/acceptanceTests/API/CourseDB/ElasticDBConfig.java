package uk.gov.cslearning.acceptanceTests.API.CourseDB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ElasticDBConfig {

    @Value("${api.elasticsearch.baseUrl}")
    private String baseUrl;
    @Value("${api.elasticsearch.username}")
    private String username;
    @Value("${api.elasticsearch.password}")
    private String password;

    @Bean(name = "ElasticClient")
    public WebClient elasticClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(ExchangeFilterFunctions.basicAuthentication(username, password))
                .build();
    }

}
