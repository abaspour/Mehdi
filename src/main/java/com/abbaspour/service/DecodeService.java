package com.abbaspour.service;

import com.abbaspour.model.BitMap;
import com.abbaspour.utils.Decode;
import org.springframework.stereotype.Service;
/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 3, 2023
*/
@Service
public class DecodeService implements DecodeServiceInterface {
    @Override
    public BitMap decode(String hex) {
        return Decode.generateBitMapFromPattern(hex);
    }
}
