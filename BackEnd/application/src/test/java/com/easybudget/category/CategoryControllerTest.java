package com.easybudget.category;

import com.easybudget.category.dto.CategoryCreation;
import com.easybudget.category.service.CategoryService;
import com.easybudget.category.type.CategoryType;
import com.easybudget.config.IntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.RequestContextFilter;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("test@test.com")
public class CategoryControllerTest extends IntegrationTestConfig {

    @Autowired
    private CategoryController target;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() {
        this.mockMvc = mockMVCWithSecurity(target, springSecurityFilterChain);
    }

    @Test
    public void saveCategory() throws Exception {
        //given
        CategoryCreation categoryCreation = new CategoryCreation();
        categoryCreation.setName("test");
        categoryCreation.setType("EXPENSE");
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/category/save")
                .content(new ObjectMapper().writeValueAsString(categoryCreation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        Category category = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), Category.class);
        //then
        resultActions.andExpect(status().isOk());
        Optional<Category> savedCategory = categoryService.findById(category.getId());
        assertTrue(savedCategory.isPresent());
        assertEquals("test", savedCategory.get().getName());
        assertEquals(CategoryType.EXPENSE, savedCategory.get().getType());
    }

}
