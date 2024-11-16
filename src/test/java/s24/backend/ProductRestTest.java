package s24.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestTest {

    @Autowired
	private WebApplicationContext webappc;
	private MockMvc mockmvc;

    @BeforeEach
	public void testSetup() {
		mockmvc = MockMvcBuilders.webAppContextSetup(webappc).build();
	}

    @Test
    public void productStatusOk() throws Exception {
        mockmvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void productInTypeJson() throws Exception {
        mockmvc.perform(get("/api/products"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
