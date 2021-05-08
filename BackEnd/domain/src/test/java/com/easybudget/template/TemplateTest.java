package com.easybudget.template;

import com.easybudget.category.Category;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

public class TemplateTest {

    @Test
    public void addCategory_shouldAddCategory_whenCategoryIsNotInTemplate() {
        //given
        final Long CATEGORY_ID = 2l;
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template();
        assertTrue(template.getCategories().isEmpty());
        //when
        template.addCategory(category);
        //then
        assertEquals(1, template.getCategories().size(), 0);
    }

    @Test
    public void addCategory_shouldNotAddCategory_whenCategoryIsInTemplate() {
        //given
        final Long CATEGORY_ID = 2l;
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template();
        template.addCategory(category);
        assertEquals(1, template.getCategories().size(), 0);
        //when
        template.addCategory(category);
        //then
        assertEquals(1, template.getCategories().size(), 0);
    }
}
