package com.example.travel.controller;

import com.example.travel.TravelApplication;
import com.example.travel.dto.BrandDto;
import com.example.travel.dto.ModelDto;
import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDto;
import com.example.travel.service.VehicleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.example.travel.utils.ObjectMapperUtils.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TravelApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;


    @Test
    public void given_vehicle_shouldCreateAndReturn_OK() throws Exception {
        //Given
        VehicleCreationRequest vehicleCreationRequest = new VehicleCreationRequest("BMW", "S42", 2000, "X98923", 10000.00d);
        VehicleDto expectedVehicleDto = new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X98923", 10000.00d);
        Mockito.when(vehicleService.creteVehicle(any())).thenReturn(expectedVehicleDto);

        //When
        String jsonString = this.mockMvc
                .perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(vehicleCreationRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        VehicleDto vehicleDto = getMapper().readValue(jsonString, VehicleDto.class);

        //then
        Assert.assertEquals(expectedVehicleDto, vehicleDto);
    }

    @Test
    public void given_vehicles_when_getAllVehicles_shouldReturnVehicles() throws Exception {
        vehicleService.creteVehicle(new VehicleCreationRequest("BMW", "S42", 2000, "X989231", 10000.00d));
        vehicleService.creteVehicle(new VehicleCreationRequest("AUDI", "X42", 2000, "X98923", 10000.00d));
        List<VehicleDto> expectedVehicleDtos = List.of(new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X989231", 10000.00d),
                new VehicleDto(1L, new BrandDto(1L, "AUDI"), new ModelDto(1L, "X42"), 2000, "X98923", 10000.00d));
        Mockito.when(vehicleService.getAllVehicles()).thenReturn(expectedVehicleDtos);

        //When
        String jsonString = this.mockMvc
                .perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        List<VehicleDto> vehicleDtos = Arrays.asList(getMapper().readValue(jsonString, VehicleDto[].class));

        //then
        Assert.assertEquals(expectedVehicleDtos, vehicleDtos);
    }

    @Test
    public void given_vehicles_updateVehicle_should_updateVehicles() throws Exception {
        VehicleCreationRequest vehicleCreationRequest = new VehicleCreationRequest("BMW", "S42", 2000, "X989231", 10000.00d);
        VehicleDto expectedVehicleDto = new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X989231", 10000.00d);
        Mockito.when(vehicleService.updateVehicle(any(), any())).thenReturn(expectedVehicleDto);

        String jsonString = this.mockMvc
                .perform(put("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(vehicleCreationRequest))
                )
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        VehicleDto vehicleDto = getMapper().readValue(jsonString, VehicleDto.class);

        //then
        Assert.assertEquals(expectedVehicleDto, vehicleDto);
    }
}
