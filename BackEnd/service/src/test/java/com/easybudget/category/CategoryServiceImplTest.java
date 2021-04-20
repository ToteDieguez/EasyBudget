package com.easybudget.category;

import com.easybudget.category.repository.CategoryRepository;
import com.easybudget.user.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void findByIdAndPerson() {
        //given
        final Long CATEGORY_ID = 1l;
        final Person person = new Person();
        doReturn(Optional.of(new Category())).when(categoryRepository).findByIdAndPerson(any(Long.class), any(Person.class));
        //when
        target.findByIdAndPerson(CATEGORY_ID, person);
        //then
        verify(categoryRepository).findByIdAndPerson(CATEGORY_ID, person);
        verifyNoMoreInteractions(categoryRepository);
    }
}
