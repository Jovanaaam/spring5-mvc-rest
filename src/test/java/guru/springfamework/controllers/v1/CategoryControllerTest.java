package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryControllerTest extends TestCase {

    public static final String NAME = "Jim";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception{
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category1.setId(2L);
        category1.setName("Bob");

        List<CategoryDTO> categories = Arrays.asList(category1,category2);

        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categories", Matchers.hasSize(2)));

    }
    @Test
    public void testGetByNameCategories() throws Exception{
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(NAME)));

    }



}