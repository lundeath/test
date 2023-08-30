import com.ukrainets.test.dto.JokeDto;
import com.ukrainets.test.service.JokeRetrievalService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JokeRetrievalServiceTest {

    @Mock
    private JokeRetrievalService service;

    @Before
    public void setUp(){
        service = mock(JokeRetrievalService.class);
    }

    @Test
    public void test1() {
        when(service.getJokes(1)).thenReturn(List.of(new JokeDto("","","",1)));
        verify(service).getJokes(1);
    }
}
