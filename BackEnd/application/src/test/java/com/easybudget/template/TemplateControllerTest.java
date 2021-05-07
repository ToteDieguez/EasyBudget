package com.easybudget.template;

import com.easybudget.config.IntegrationTestConfig;
import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.PersonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("test@test.com")
public class TemplateControllerTest extends IntegrationTestConfig {

    @Autowired
    private TemplateController target;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private static final Long TEMPLATE_ID = 1L;

    private Person person;

    @Before
    public void setup() {
        this.person = personService.findByUsername("test@test.com").orElseThrow(NoSuchElementException::new);
        this.mockMvc = super.mockMVCWithSecurity(target, springSecurityFilterChain);
    }

    @Test
    public void createTemplate() throws Exception {
        //given
        final String TEMPLATE_NAME = "Template Name";
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/template")
                .param("name", TEMPLATE_NAME)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
        Long templateID = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), JsonNode.class).get("templateID").asLong();
        Optional<Template> savedTemplate = templateService.findByIdAndPerson(templateID, this.person.getPersonID());
        assertTrue(savedTemplate.isPresent());
        assertEquals(TEMPLATE_NAME, savedTemplate.get().getName());
    }

    @Test
    public void findAll() throws Exception {
        //given
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/template/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
        List<Template> templates = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);
        assertEquals(1, templates.size());
    }

    @Test
    public void addCategoryToTemplate() throws Exception {
        //given
        Optional<Template> savedTemplate = templateService.findByIdAndPerson(TEMPLATE_ID, this.person.getPersonID());
        assertTrue(savedTemplate.isPresent());
        assertTrue(savedTemplate.get().getCategories().isEmpty());
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/template/"+TEMPLATE_ID+"/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
        Template template = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), Template.class);
        assertEquals(1, template.getCategories().size(), 0);
    }
}
