package com.easybudget.category;

import com.easybudget.category.dto.CategoryDTO;
import com.easybudget.category.service.CategoryService;
import com.easybudget.category.type.CategoryType;
import com.easybudget.config.IntegrationTestConfig;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.service.PersonService;
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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("test@test.com")
public class CategoryControllerTest extends IntegrationTestConfig {

    @Autowired
    private CategoryController target;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private Person person;

    @Before
    public void setup() {
        this.person = personService.findByUsername("test@test.com").orElseThrow(NoSuchElementException::new);
        this.mockMvc = super.mockMVCWithSecurity(target, springSecurityFilterChain);
    }

    @Test
    public void createCategory_shouldCreateACategory_whenAttributesAreSetProperly() throws Exception {
        //given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("test");
        categoryDTO.setType("EXPENSE");
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/category")
                .content(new ObjectMapper().writeValueAsString(categoryDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
        Category category = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), Category.class);
        Optional<Category> savedCategory = categoryService.findByIdAndPersonID(category.getId(), person.getPersonID());
        assertTrue(savedCategory.isPresent());
        assertEquals("test", savedCategory.get().getName());
        assertEquals(CategoryType.EXPENSE, savedCategory.get().getType());
    }


    @Test
    public void findCategory() throws Exception {
        //given
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/category/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());
        Category category = new ObjectMapper().readValue(resultActions.andReturn().getResponse().getContentAsString(), Category.class);
        assertEquals("House", category.getName());
        assertEquals(CategoryType.EXPENSE, category.getType());
        assertEquals(1, category.getSubCategories().size(), 0);
        assertEquals("Rent", category.getSubCategories().get(0).getName());
    }

}
