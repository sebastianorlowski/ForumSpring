package pl.orlowski.sebastian.forumspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@PropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
@WebMvcTest
public class SimpleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnExpected() throws Exception {
        mockMvc
                .perform(get("/index"))
                .andExpect(status().isOk());

    }
}
