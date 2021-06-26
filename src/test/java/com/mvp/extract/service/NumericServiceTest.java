package com.mvp.extract.service;

import com.mvp.extract.exception.InputStreamOrReadException;
import com.mvp.extract.model.Position;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NumericServiceTest {

    private static final String PATH = "src/main/resources/";

    private NumericService underTest;

    @Before
    public void setUp() {
        underTest = new NumericService();
    }

    @Test
    public void extractSuccess() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "file12fileONE\r4abc".getBytes());
        List<Position> list = underTest.extract(file);
        assertEquals(4, list.size());
    }

    @Test(expected = InputStreamOrReadException.class)
    public void extractWithInputStreamOrReadException() throws IOException {
        MultipartFile fileMock = mock(MultipartFile.class);
        when(fileMock.getInputStream()).thenThrow(new IOException());
        underTest.extract(fileMock);
    }

    @Test
    public void extractWithNull() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "".getBytes());
        List<Position> list = underTest.extract(file);
        assertEquals(0, list.size());

    }

    @Test
    public void shouldReturnNoNumerical() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "abcdef\rg h i j".getBytes());
        List<Position> list = underTest.extract(file);
        assertEquals(0, list.size());

    }

    @Test
    public void shouldReturnNoNumericalForOnlyWhiteSpaces() {
        MockMultipartFile file = new MockMultipartFile("data", "data.txt", "text/plain", "          \r     ".getBytes());
        List<Position> list = underTest.extract(file);
        assertEquals(0, list.size());
    }

    @Test
    public void shouldReturnNumericalForXMLContent() throws IOException {

        File file1 = new File(PATH.concat("xml.xml"));
        InputStream is = new FileInputStream(file1);
        MockMultipartFile file = new MockMultipartFile
                ("xml", "xml.xml", "xml/plain", is);
        List<Position> list = underTest.extract(file);
        assertEquals("1", list.get(0).getNumber());
    }

    @Test
    public void shouldReturnNumericalForHTMLContent() throws IOException {
        File file1 = new File(PATH.concat("html.html"));
        InputStream is = new FileInputStream(file1);
        MockMultipartFile file = new MockMultipartFile
                ("html", "html.html", "html/plain", is);
        List<Position> list = underTest.extract(file);
        assertEquals("8", list.get(4).getNumber());


    }
}
