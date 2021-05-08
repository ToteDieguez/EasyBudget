package com.easybudget.category;

import com.easybudget.category.repository.CategoryRepository;
import com.easybudget.user.person.Person;
import com.easybudget.user.person.PersonID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl target;

    @Mock
    private CategoryRepository categoryRepository;

    private static final Long PERSON_ID = 10l;

    private Person person;

    @Before
    public void setup() {
        this.person = new Person();
        ReflectionTestUtils.setField(this.person, "id", PERSON_ID);
    }

    @Test
    public void save() {
        //given
        Category category = new Category();
        doReturn(category).when(categoryRepository).create(any(Category.class));
        //when
        target.create(category);
        //then
        verify(categoryRepository).create(category);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    public void findByIdAndPersonID() {
        //given
        final Long CATEGORY_ID = 1l;
        doReturn(Optional.of(new Category())).when(categoryRepository).findByIdAndPersonID(any(Long.class), any(PersonID.class));
        //when
        target.findByIdAndPersonID(CATEGORY_ID, person.id());
        //then
        verify(categoryRepository).findByIdAndPersonID(CATEGORY_ID, person.id());
        verifyNoMoreInteractions(categoryRepository);
    }
}
