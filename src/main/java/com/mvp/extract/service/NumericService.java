package com.mvp.extract.service;

import com.mvp.extract.constant.Constants;
import com.mvp.extract.exception.InputStreamOrReadException;
import com.mvp.extract.model.Position;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.mvp.extract.constant.Constants.CONTENT_TYPE_HTML;


@Service
public class NumericService {

    public List<Position> extract(MultipartFile file) {
        List<Position> listOfAllNumericPositions = new ArrayList<>();
        List<Position> positionListByNumbers = extractByNumbers(file);
        List<Position> positionListByAlphaNumbers = extractByAlphaNumbers(file);

        if (!positionListByNumbers.isEmpty()) {
            listOfAllNumericPositions.addAll(positionListByNumbers);
        }
        if (!positionListByAlphaNumbers.isEmpty()) {
            listOfAllNumericPositions.addAll(positionListByAlphaNumbers);
        }
        return listOfAllNumericPositions;
    }


    private List<Position> extractByNumbers(MultipartFile file) {
        BufferedReader bufferedReaderForNumber;
        List<Position> list = new ArrayList<>();
        try {
            bufferedReaderForNumber = new BufferedReader(new InputStreamReader(file.getInputStream()));
            int character;
            int index = 0;
            int line = 0;
            char linebreak = file.getContentType().contains(CONTENT_TYPE_HTML) ? '\n' : '\r';
            while ((character = bufferedReaderForNumber.read()) != -1) {
                if (((char) character == linebreak)) {
                    line++;
                    index = -1;
                    continue;
                }
                if (Character.isDigit((char) character)) {
                    list.add(new Position(String.valueOf((char) character), index, line));
                }
                index++;
            }
        } catch (IOException e) {
            throw new InputStreamOrReadException("Exception while reading or parsing file content.");
        }
        return list;
    }

    private List<Position> extractByAlphaNumbers(MultipartFile file) {
        BufferedReader bufferReaderForAlphaNumber;
        List<Position> list = new ArrayList<>();
        try {
            bufferReaderForAlphaNumber = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String bufferReaderForLine;
            int lineForAlphaNumber = 0;
            while ((bufferReaderForLine = bufferReaderForAlphaNumber.readLine()) != null) {
                for (String numberName : Constants.ALPHA_NUMBER_LIST) {
                    if (bufferReaderForLine.contains(numberName)) {
                        int indexOf = 0;
                        calculateIndexForMultipleOccurrence(list, bufferReaderForLine, lineForAlphaNumber, numberName, indexOf);
                    }
                }
                lineForAlphaNumber++;
            }
        } catch (IOException e) {
            throw new InputStreamOrReadException("Exception while reading or parsing file content.");
        }
        return list;
    }


    private void calculateIndexForMultipleOccurrence(List<Position> list, String bufferReaderForLine, int lineForAlphaNumber, String numberName, int indexOf) {
        while (indexOf != -1) {
            indexOf = bufferReaderForLine.indexOf(numberName, indexOf);
            if (indexOf != -1) {
                list.add(new Position(numberName, indexOf - 1, lineForAlphaNumber));
                indexOf++;
            }
        }
    }
}
