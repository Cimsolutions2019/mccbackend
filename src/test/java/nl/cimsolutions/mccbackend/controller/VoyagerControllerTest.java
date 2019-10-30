package nl.cimsolutions.mccbackend.controller;

import nl.cimsolutions.mccbackend.MccbackendApplication;
import nl.cimsolutions.mccbackend.model.Location;
import nl.cimsolutions.mccbackend.model.Voyager;
import nl.cimsolutions.mccbackend.payload.VoyagerResponse;
import nl.cimsolutions.mccbackend.repository.LocationRepository;
import nl.cimsolutions.mccbackend.repository.VoyagerRepository;
import nl.cimsolutions.mccbackend.util.DistanceCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VoyagerController.class)
public class VoyagerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DistanceCalculator distanceCalculator;

    @MockBean
    private VoyagerRepository voyagerRepository;

    @Test
    public void findAll() throws Exception {

//        this.mockMvc.perform(get("/voyagers/sensors"))
//                .andExpect(status().isOk());
//        DistanceCalculator mock = org.mockito.Mockito.mock(DistanceCalculator.class);
//
//        when(mock.testDistance()).thenReturn(2);
//        assertEquals(2, mock.testDistance());
    }


}
