package tacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class) //HomeController 웹페이지테스트
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; //MockMvc 주입

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(get("/")) //Get /를 수행한다
                .andExpect(status().isOk()) //HTTP 200이 되어야 한다
                .andExpect(view().name("home")) //home 뷰가 있어야 한다.
                .andExpect(content().string(containsString("Welcome Test")) //콘텐츠에 Welcome Test가 포함되어있어야한다
                );
    }
}
