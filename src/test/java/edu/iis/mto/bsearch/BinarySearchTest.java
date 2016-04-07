package edu.iis.mto.bsearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.abs;
import static junit.framework.TestCase.assertEquals;

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

    @Test
    public void shouldFindExistingKey() throws Exception{
        int size = 10;

        int[] seq = generateSequence(size);

        Random random = new Random();

        int index = abs(random.nextInt())%size;

        int value = seq[index];

        SearchResult searchResult = BinarySearch.search(value, seq);

        assertEquals(true, searchResult.isFound());
        assertEquals(index, searchResult.getPosition());
    }

    public int[] generateSequence(int size) {
        int[] sequence = new int[size];

        Random random = new Random();
        boolean duplicateFlag;

        for (int i = 0; i < size; i++) {
            int randomValue;

            do {
                duplicateFlag = false;
                randomValue = random.nextInt();
                for (int j = 0; j < i; j++) {
                    if (sequence[j] == randomValue) {
                        duplicateFlag = true;
                        break;
                    }
                }
            } while (duplicateFlag);

            sequence[i] = randomValue;
        }

        Arrays.sort(sequence);

        return sequence;
    }

}