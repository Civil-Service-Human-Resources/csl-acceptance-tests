package uk.gov.cslearning.acceptanceTests.API.CourseDB;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import uk.gov.cslearning.acceptanceTests.API.CourseDB.model.Course;

@Service
@Slf4j
public class ElasticsearchClient {

    private final WebClient elasticClient;

    public ElasticsearchClient(@Qualifier("ElasticClient") WebClient elasticClient) {
        this.elasticClient = elasticClient;
    }

    public void createCourse(Course course) {
        elasticClient.put()
                .uri(String.format("/courses/course/%s", course.id))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(course)
                .exchangeToMono(r -> {
                    log.debug(String.format("Elasticsearch response: %s", r.statusCode()));
                    if (!r.statusCode().isError()) {
                        return r.bodyToMono(String.class);
                    }
                    else {
                        throw new RuntimeException(r.statusCode().toString());
                    }
                })
                .block();
    }

    public void deleteCourse(String courseId) {
        elasticClient.delete()
                .uri(String.format("/courses/course/%s", courseId))
                .exchangeToMono(r -> {
                    log.debug(String.format("Elasticsearch response: %s", r.statusCode()));
                    if (!r.statusCode().isError()) {
                        return r.bodyToMono(String.class);
                    }
                    else {
                        throw new RuntimeException(r.statusCode().toString());
                    }
                })
                .block();
    }

    public void deleteAcceptanceCourses() {
        String query = "{\"query\":{\"match_phrase\":{\"id\": \"ACCEPTANCE-COURSE:\"}}}";
        elasticClient.post()
                .uri("/courses/course/_delete_by_query")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(query)
                .exchangeToMono(r -> {
                    log.debug(String.format("Elasticsearch response: %s", r.statusCode()));
                    if (!r.statusCode().isError()) {
                        return r.bodyToMono(String.class);
                    }
                    else {
                        throw new RuntimeException(r.statusCode().toString());
                    }
                })
                .block();
    }
}
