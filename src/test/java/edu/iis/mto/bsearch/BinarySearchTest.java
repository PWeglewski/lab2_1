package edu.iis.mto.bsearch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by piotr on 07.04.2016.
 */
public class BinarySearchTest {
    public static final int POSITION_NOT_FOUND = -1;
    public static final boolean IS_FOUND_SUCCESS = true;
    public static final boolean IS_FOUND_FAIL = false;
    public static final int SEQUENCE_SIZE = 1000;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() throws Exception {
        // given
        int key = 10;
        int[] seq = generateSequence(0);

        // when
        BinarySearch.search(key, seq);

        //then
        // Should throw IllegalArgumentException
    }

    @Test
    public void shouldFindExistingElementSeqLengthOne() throws Exception {
        // given
        int[] seq = generateSequence(1);
        int[] seqCopy = seq.clone();
        int index = 0;
        int value = seq[index];

        // when
        BinarySearch.search(index, seq);
        SearchResult searchResult = BinarySearch.search(value, seq);

        // then
        assertThat(searchResult.isFound(), is(IS_FOUND_SUCCESS));
        assertThat(searchResult.getPosition(), is(equalTo(index)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    @Test
    public void shouldNotFindElementSeqLengthOne() throws Exception {
        // given
        int[] seq = generateSequence(1);
        int[] seqCopy = seq.clone();
        int value = getElementNotIncludedInSequence(seq);

        // when
        SearchResult searchResult = BinarySearch.search(value, seq);

        //then
        assertThat(searchResult.getPosition(), is(equalTo(POSITION_NOT_FOUND)));
        assertThat(searchResult.isFound(), is(equalTo(IS_FOUND_FAIL)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    @Test
    public void shouldFindFirstElementSeqLongerThanOne() throws Exception {
        // given
        int[] seq = generateSequence(SEQUENCE_SIZE);
        int[] seqCopy = seq.clone();
        int index = 0;
        int value = seq[index];

        //when
        SearchResult searchResult = BinarySearch.search(value, seq);

        //then
        assertThat(searchResult.getPosition(), is(equalTo(index)));
        assertThat(searchResult.isFound(), is(equalTo(IS_FOUND_SUCCESS)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    @Test
    public void shouldFindLastElementSeqLongerThanOne() throws Exception {
        // given
        int[] seq = generateSequence(SEQUENCE_SIZE);
        int[] seqCopy = seq.clone();
        int index = SEQUENCE_SIZE - 1;
        int value = seq[index];

        //when
        SearchResult searchResult = BinarySearch.search(value, seq);

        //then
        assertThat(searchResult.getPosition(), is(equalTo(index)));
        assertThat(searchResult.isFound(), is(equalTo(IS_FOUND_SUCCESS)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    @Test
    public void shouldFindMidElementSeqLongerThanOne() throws Exception {
        // given
        int[] seq = generateSequence(SEQUENCE_SIZE);
        int[] seqCopy = seq.clone();
        int index = SEQUENCE_SIZE / 2;
        int value = seq[index];

        //when
        SearchResult searchResult = BinarySearch.search(value, seq);

        //then
        assertThat(searchResult.getPosition(), is(equalTo(index)));
        assertThat(searchResult.isFound(), is(equalTo(IS_FOUND_SUCCESS)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    @Test
    public void shouldNotFindNonExistingKeySeqLongerThanOne() throws Exception {
        // given
        int[] seq = generateSequence(SEQUENCE_SIZE);
        int[] seqCopy = seq.clone();
        int value = getElementNotIncludedInSequence(seq);

        //when
        SearchResult searchResult = BinarySearch.search(value, seq);

        //then
        assertThat(searchResult.getPosition(), is(POSITION_NOT_FOUND));
        assertThat(searchResult.isFound(), is(equalTo(IS_FOUND_FAIL)));
        assertThat(seq, is(equalTo(seqCopy)));
    }

    public int[] generateSequence(int size) {
        return ThreadLocalRandom.current()
                .ints()
                .distinct()
                .limit(size)
                .sorted()
                .toArray();
    }

    public int getElementNotIncludedInSequence(int[] sequence) {
        return ThreadLocalRandom.current()
                .ints()
                .filter(e -> !Arrays.asList(sequence).contains(e))
                .findFirst()
                .getAsInt();
    }
}