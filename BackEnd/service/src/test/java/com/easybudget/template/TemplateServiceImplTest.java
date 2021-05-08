package com.easybudget.template;

import com.easybudget.category.Category;
import com.easybudget.category.service.CategoryService;
import com.easybudget.template.repository.TemplateRepository;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
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
    private static final Long PERSON_ID = 10l;

    private Person person;

    @Before
    public void setup() {
        this.person = new Person();
        ReflectionTestUtils.setField(this.person, "id", PERSON_ID);
    }

    @Test
    public void create() {
        //given
        Template template = new Template(TEMPLATE_NAME, person.id());
        ArgumentCaptor<Template> templateCaptor = ArgumentCaptor.forClass(Template.class);
        doReturn(template).when(templateRepository).save(templateCaptor.capture());
        //when
        target.create(TEMPLATE_NAME, person.id());
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
        Category category = new Category();
        Template template = new Template(TEMPLATE_NAME, person.id());
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPersonID(anyLong(), any(PersonID.class));
        doReturn(Optional.of(category)).when(categoryService).findByIdAndPersonID(anyLong(), any(PersonID.class));
        doReturn(template).when(templateRepository).save(any(Template.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person.id());
        //then
        verify(templateRepository).findByIdAndPersonID(TEMPLATE_ID, person.id());
        verify(categoryService).findByIdAndPersonID(CATEGORY_ID, person.id());
        verify(templateRepository).save(template);
        verifyNoMoreInteractions(templateRepository, categoryService);
    }

    @Test(expected = NoSuchElementException.class)
    public void addCategoryToTemplate_shouldThrowException_whenCategoryDoesntExist() {
        //given
        final Long CATEGORY_ID = 2l;
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template(TEMPLATE_NAME, person.id());
        template.addCategory(category);
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPersonID(anyLong(), any(PersonID.class));
        doReturn(Optional.empty()).when(categoryService).findByIdAndPersonID(anyLong(), any(PersonID.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person.id());
        //then THROW EXCEPTION
    }

    @Test(expected = NoSuchElementException.class)
    public void addCategoryToTemplate_shouldThrowException_whenTemplateDoesntExist() {
        //given
        final Long CATEGORY_ID = 2l;
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", CATEGORY_ID);
        Template template = new Template(TEMPLATE_NAME, person.id());
        template.addCategory(category);
        doReturn(Optional.empty()).when(templateRepository).findByIdAndPersonID(anyLong(), any(PersonID.class));
        //when
        target.addCategoryToTemplate(TEMPLATE_ID, CATEGORY_ID, person.id());
        //then THROW EXCEPTION
    }

    @Test
    public void findByIdAndPersonID() {
        //given
        Template template = new Template(TEMPLATE_NAME, person.id());
        doReturn(Optional.of(template)).when(templateRepository).findByIdAndPersonID(anyLong(), any(PersonID.class));
        //when
        target.findByIdAndPerson(TEMPLATE_ID, person.id());
        //then
        verify(templateRepository).findByIdAndPersonID(TEMPLATE_ID, person.id());
        verifyNoMoreInteractions(templateRepository);
    }

    @Test
    public void findByPersonID() {
        //given
        Template template = new Template(TEMPLATE_NAME, person.id());
        doReturn(Arrays.asList(template)).when(templateRepository).findByPersonID(any(PersonID.class));
        //when
        target.findByPersonID(person.id());
        //then
        verify(templateRepository).findByPersonID(person.id());
        verifyNoMoreInteractions(templateRepository);
    }
}
