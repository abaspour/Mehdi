package com.abbaspour.service;

import com.abbaspour.model.BitMap;
/**
 * @author Mehdi Abbaspour
 * @version 1.0
 * created: May 3, 2023
 */

public interface DecodeServiceInterface {
    BitMap decode(String hex);
}
