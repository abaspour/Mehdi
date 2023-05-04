package com.abbaspour.service;

import com.abbaspour.model.BitMap;
import org.springframework.stereotype.Service;


public interface DecodeService {
    BitMap decode(String hex);
}
