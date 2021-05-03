package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.category.service.CategoryService;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.template.service.TemplateService;
import com.easybudget.user.person.Person;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class TemplateServiceImplTest {

    @InjectMocks
    private TemplateServiceImpl target;

    @Mock
    private TemplateRepository templateRepository;

    @Mock
    private CategoryService categoryService;

    private static final Long TEMPLATE_ID = 1l;
    public static final String TEMPLATE_NAME = "templateName";

    @Test
    public void create() {
        //given
        Person person = new Person();
        Template template = new Template(TEMPLATE_NAME, person);
        ArgumentCaptor<Template> templateCaptor = ArgumentCaptor.forClass(Template.class);
        doReturn(template).when(templateRepository).save(templateCaptor.capture());
        //when
        target.create(TEMPLATE_NAME, person);
        //then
        template = templateCaptor.getValue();
        assertEquals(TEMPLATE_NAME, template.getName());
        verify(templateRepository).save(eq(template));
        verifyNoMoreInteractions(templateRepository);
    }

    @Test
    public void addCategoryToTemplate_shouldAddCategoryToTemplate_whenCategoryAndTemplateExist() {
        //given
        final Long CATEGORY_ID = 2l;
        Person person = new Person();
        Category category = new Category();
        Template template = new Template(TEMPLATE_NAME, person);
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPerson(anyLong(), any(Person.class));
        doReturn(Optional.of(category)).when(categoryService).findByIdAndPerson(anyLong(), any(Person.class));
        doReturn(template).when(templateRepository).save(any(Template.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person);
        //then
        verify(templateRepository).findByIdAndPerson(TEMPLATE_ID, person);
        verify(categoryService).findByIdAndPerson(CATEGORY_ID, person);
        verify(templateRepository).save(template);
        verifyNoMoreInteractions(templateRepository, categoryService);
    }

    @Test
    public void addCategoryToTemplate_shouldNotAddCategoryToTemplate_whenCategoryAlreadyIsInTemplate() {
        //given
        final Long CATEGORY_ID = 2l;
        Person person = new Person();
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template(TEMPLATE_NAME, person);
        template.addCategory(category);
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPerson(anyLong(), any(Person.class));
        doReturn(Optional.of(category)).when(categoryService).findByIdAndPerson(anyLong(), any(Person.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person);
        //then
        verify(templateRepository).findByIdAndPerson(TEMPLATE_ID, person);
        verify(categoryService).findByIdAndPerson(CATEGORY_ID, person);
        verify(templateRepository, never()).save(template);
        verifyNoMoreInteractions(templateRepository, categoryService);
    }

    @Test(expected = NoSuchElementException.class)
    public void addCategoryToTemplate_shouldThrowException_whenCategoryDoesntExist() {
        //given
        final Long CATEGORY_ID = 2l;
        Person person = new Person();
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template(TEMPLATE_NAME, person);
        template.addCategory(category);
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPerson(anyLong(), any(Person.class));
        doReturn(Optional.empty()).when(categoryService).findByIdAndPerson(anyLong(), any(Person.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person);
        //then THROW EXCEPTION
    }

    @Test(expected = NoSuchElementException.class)
    public void addCategoryToTemplate_shouldThrowException_whenTemplateDoesntExist() {
        //given
        final Long CATEGORY_ID = 2l;
        Person person = new Person();
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template(TEMPLATE_NAME, person);
        template.addCategory(category);
        doReturn(Optional.empty()).when(templateRepository).findByIdAndPerson(anyLong(), any(Person.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person);
        //then THROW EXCEPTION
    }

    @Test
    public void findByIdAndPerson() {
        //given
        Person person = new Person();
        Template template = new Template(TEMPLATE_NAME, person);
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPerson(anyLong(), any(Person.class));
        //when
        target.findByIdAndPerson(TEMPLATE_ID, person);
        //then
        verify(templateRepository).findByIdAndPerson(TEMPLATE_ID, person);
        verifyNoMoreInteractions(templateRepository);
    }

    @Test
    public void findByPerson() {
        //given
        Person person = new Person();
        Template template = new Template(TEMPLATE_NAME, person);
        doReturn(Arrays.asList(template)).when(templateRepository).findByPerson(any(Person.class));
        //when
        target.findByPerson(person);
        //then
        verify(templateRepository).findByPerson(person);
        verifyNoMoreInteractions(templateRepository);
    }
}
