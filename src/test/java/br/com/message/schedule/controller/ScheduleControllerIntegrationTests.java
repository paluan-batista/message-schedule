package br.com.message.schedule.controller;

import br.com.message.schedule.ConstantsTests;
import br.com.message.schedule.domain.model.entity.Schedule;
import br.com.message.schedule.domain.repository.RecipientRepository;
import br.com.message.schedule.domain.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(ConstantsTests.PROFILE_TEST)
class ScheduleControllerIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    private MockMvc mockMvc;

    private String requestBody;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        this.requestBody =
                "{\n" + "    \"message\": \"VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!\",\n"
                        + "    \"recipient\": {\n" + "        \"recipient\": \"paluan.desenvolvimento@gmail.com\"\n"
                        + "    },\n" + "    \"send_date\": \"2020-11-01 23:59:59\",\n"
                        + "    \"type\": \"email\"\n" + "}";
    }

    @Test
    @Order(1)
    void shouldCreateSchedulingRequest() throws Exception {
        this.mockMvc
                .perform(post("/api/schedule/").content(this.requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated()).andExpect(header().exists("Location"));
    }

    @Test
    @Order(2)
    void shouldSearchSavedSchedule() throws Exception {
        mockMvc.perform(get("/api/schedule/pending").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].recipient.recipient", is("paluan.desenvolvimento@gmail.com")))
                .andExpect(
                        jsonPath("$.content[0].message", is("VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!")))
                .andExpect(jsonPath("$.content[0].type", is("EMAIL")))
                .andExpect(jsonPath("$.content[0].status", is("PENDING")))
                .andExpect(jsonPath("$.content[0].send_date", is("2020-11-01 23:59:59")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldDeleteSchedule() throws Exception {
        Schedule schedule = scheduleRepository.findAll().get(0);
        assertNotNull(schedule);

        String uuid = schedule.getUuid().toString();

        this.mockMvc
                .perform(delete("/api/schedule/" + uuid).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/schedule/deleted").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].recipient.recipient", is("paluan.desenvolvimento@gmail.com")))
                .andExpect(
                        jsonPath("$.content[0].message", is("VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!")))
                .andExpect(jsonPath("$.content[0].type", is("EMAIL")))
                .andExpect(jsonPath("$.content[0].status", is("DELETED")))
                .andExpect(jsonPath("$.content[0].send_date", is("2020-11-01 23:59:59")))
                .andExpect(status().isOk());

        scheduleRepository.deleteAll();
        recipientRepository.deleteAll();
    }

}
