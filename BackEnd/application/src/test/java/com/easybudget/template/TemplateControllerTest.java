package com.easybudget.template;

import com.easybudget.config.IntegrationTestConfig;
import com.easybudget.template.service.TemplateService;
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
    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() {
        this.mockMvc = super.mockMVCWithSecurity(target, springSecurityFilterChain);
    }

    @Test
    public void createTemplate() throws Exception {
        //given
        String templateName = "Template Name";
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/template/create")
                .param("name", templateName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        Template template = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), Template.class);
        //then
        resultActions.andExpect(status().isOk());
        Optional<Template> savedTemplate = templateService.findById(template.getId());
        assertTrue(savedTemplate.isPresent());
        assertEquals(templateName, savedTemplate.get().getName());
    }
}
