package com.abbaspour.service;

import com.abbaspour.utils.NumberToEnglishWordConverter;
import com.abbaspour.utils.NumberToFrenchWordConverter;
import org.springframework.stereotype.Service;

@Service
public class IntegerToWordService implements IntegerToWordServiceInterface {
    @Override
    public String toEnglishWordConverter(Long number) {
        return NumberToEnglishWordConverter.convert(number);
    }

    @Override
    public String toFrenchWordConverter(Long number) {
        return NumberToFrenchWordConverter.convert(number);
    }
}
