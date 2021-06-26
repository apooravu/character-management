package com.mvp.extract.controller;

import com.mvp.extract.exception.NoFileOrContentFoundException;
import com.mvp.extract.model.Position;
import com.mvp.extract.service.NumericService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RunWith(MockitoJUnitRunner.class)
public class NumericControllerTest {

    @Mock
    private NumericService numericServiceMock;

    @InjectMocks
    private NumericController underTest;

    @Test
    public void extractNumberFromFileSuccess() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "file12fileONE\r4abc".getBytes());

        List<Position> list = new ArrayList<>();
        list.add(new Position("1", 4, 0));
        list.add(new Position("2", 5, 0));
        list.add(new Position("4", 10, 1));
        list.add(new Position("ONE", 4, 0));
        Mockito.when(numericServiceMock.extract(file)).thenReturn(list);

        ResponseEntity<List<Position>> responseEntity = underTest.extractNumbersFromFile(file);
        assertEquals(list.size(), Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals("1",responseEntity.getBody().get(0).getNumber());

    }

    @Test(expected = NoFileOrContentFoundException.class)
    public void extractNumberFromFileNoFileContent() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "".getBytes());
        underTest.extractNumbersFromFile(file);
    }

}
