package com.silicon.library.controller;

import com.silicon.library.DemoServiceApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoServiceApp.class)
@WebAppConfiguration
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllBooks() throws Exception {
        this.mockMvc.perform(get("/api/books/")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void getBookDetails() throws Exception {
        this.mockMvc.perform(get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.book.name").value("Spring Boot in action"))
                .andExpect(jsonPath("$.book.numOfPages").value(600));
    }

    @Test
    public void getNotFoundBook() throws Exception {
        this.mockMvc.perform(get("/api/books/{id}", "100")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiError.detailedErrors.book")
                        .value("book '100' couldn't be found."));
    }

    @Test
    public void deleteBook() throws Exception {

        Long id = 1L;

        this.mockMvc.perform(delete("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());


        this.mockMvc.perform(get("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiError.detailedErrors.book")
                        .value("book '" + id + "' couldn't be found."));
    }

    @Test
    public void deleteNotFoundBook() throws Exception {

        this.mockMvc.perform(delete("/api/books/{id}", 100)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiError.detailedErrors.book")
                        .value("book '100' couldn't be found."));

    }

    @Test
    public void createBook() throws Exception {

        // Happy Scenario
        String validBookJson = "{ \"author\": \"Mohamed\", \"isbn\": \"123\", \"name\": \"Lucene in action\", \"numOfPages\": \"100\", \"publishDate\": \"2019-10-09T23:21:38.399Z\" }";
        this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(validBookJson))
                .andExpect(status().isCreated());

        // verify the size after insert
        this.mockMvc.perform(get("/api/books/")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    public void createBook_withEmptyName() throws Exception {

        // Sad Scenario
        String invalidBookJson = "{ \"author\": \"Mohamed\", \"isbn\": \"123\", \"name\": \"\", \"numOfPages\": \"100\", \"publishDate\": \"2019-10-09T23:21:38.399Z\" }";
        this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(invalidBookJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.apiError.errorNumber").value("1002"))
                .andExpect(jsonPath("$.apiError.detailedErrors.name")
                        .value("must not be empty"));

    }

    @Test
    public void updateBook() throws Exception {

        // Happy Scenario
        String validBookJson = "{ \"author\": \"David_updated\", \"isbn\": \"123\", \"name\": \"Spring Boot in action updated\", \"numOfPages\": \"100\", \"publishDate\": \"2019-10-09T23:21:38.399Z\" }";

        Long id = 1L;

        this.mockMvc.perform(put("/api/books/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(validBookJson))
                .andExpect(status().isOk());

        // verify the updated data
        this.mockMvc.perform(get("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.book.name").value("Spring Boot in action updated"))
                .andExpect(jsonPath("$.book.author").value("David_updated"));
    }

    @Test
    public void updateNotFoundBook() throws Exception {

        // Sad Scenario
        String validBookJson = "{ \"author\": \"David_updated\", \"isbn\": \"123\", \"name\": \"Spring Boot in action updated\", \"numOfPages\": \"100\", \"publishDate\": \"2019-10-09T23:21:38.399Z\" }";

        Long id = 100L;

        this.mockMvc.perform(put("/api/books/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(validBookJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiError.detailedErrors.book")
                        .value("book '100' couldn't be found."));

    }

}
