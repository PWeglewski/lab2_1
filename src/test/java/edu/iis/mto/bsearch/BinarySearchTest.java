package edu.iis.mto.bsearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by piotr on 07.04.2016.
 */
public class BinarySearchTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() throws Exception {
        int key = 10;
        int[] seq = generateSequence(0);

        BinarySearch.search(key, seq);
    }

    public int[] generateSequence(int size) {
        int[] sequence = new int[size];

        Random random = new Random();

        for (int element : sequence) {
            element = random.nextInt();
        }

        Arrays.sort(sequence);

        return sequence;
    }

}