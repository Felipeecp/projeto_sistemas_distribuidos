package com.ufma.wmo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestList {

    @Test
    public void test(){
        List<Integer> numbers = new ArrayList<>();

        numbers.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> t = numbers.subList(0, 4);
        Assertions.assertEquals(4, t.size());
    }
}
