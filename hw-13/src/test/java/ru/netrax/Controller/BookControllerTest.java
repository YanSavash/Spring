package ru.netrax.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.netrax.Service.AuthorService;
import ru.netrax.Service.BookService;
import ru.netrax.Service.GenreService;
import ru.netrax.Service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@DisplayName("Тестирование класса BookController")
public class BookControllerTest {
    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin")
    @Test
    public void testAdminAuthenticated() throws Exception {
        mockMvc.perform(get("/bookstore/book")).andExpect(status().isOk());
    }

    @WithMockUser(username = "admin")
    @Test
    @DisplayName("перенаправляет на страницу со всеми книгами")
    void checkSimpleRedirection() throws Exception {
        this.mockMvc.perform(get("/bookstore/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bookstore/book"));
    }

    @WithMockUser(username = "admin", authorities = "ADMIN")
    @Test
    @DisplayName("возвращает форму добавления книги")
    void checkAddBookForm() throws Exception {
        this.mockMvc.perform(get("/bookstore/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("bookDto"));
    }

    @WithMockUser(username = "admin", authorities = "ADMIN")
    @Test
    @DisplayName("удаляет книгу и перенаправляет на общий список")
    void checkDeleteBookForm() throws Exception {
        this.mockMvc.perform(post("/bookstore/delete/")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bookstore/book"));
    }
}
