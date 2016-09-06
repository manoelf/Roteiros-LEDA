package sorting.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
 
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

import sorting.divideAndConquer.MergeSort;
import sorting.divideAndConquer.QuickSort;
 
public class StudentSortingTest {
 
    private final String LOG_DIRECTORY = "logger";
    private final String LOG_FILE_NAME = "log";
 
    private final String EXEC_RESULT_DIRECTORY = "statistics";
    private final String EXEC_RESULT_FILE_NAME = "execResult";
 
    private final int TEST_TIME = 3;// in seconds
 
    Random randomer;
    private QuickSort sorting;// PUT YOUR SORTING CLASS HERE.
    private int qtdTests;
    private boolean success;
 
    private Integer[] arrayGenerator() {
        int length = randomer.nextInt(20);
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = randomer.nextInt(100);
            if (randomer.nextBoolean())
                array[i] *= -1;
        }
 
        return array;
    }
 
    private void execResultWriter(String execResult) {
        textWriter(EXEC_RESULT_DIRECTORY, EXEC_RESULT_FILE_NAME, execResult, true);
    }
 
    private void execResultWriter(String execResult, boolean nextLine) {
        textWriter(EXEC_RESULT_DIRECTORY, EXEC_RESULT_FILE_NAME, execResult, nextLine);
    }
 
    @Test
    public void start() {
        execResultWriter("---------------------BEGIN----------------------");
        execResultWriter(sorting.getClass().getSimpleName());
         
        long begin = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - begin < TEST_TIME * 1000) {
                qtdTests++;
                simpleInput();
                indexhandler();
            }
            success = true;
        } catch (Throwable error) {
            success = false;
            logger(error);
            throw error;
        } finally {
            execResultWriter("Total of tests: " + qtdTests + " | ", false);
            execResultWriter("Total time: " + ((System.currentTimeMillis() - begin) / 1000) + "s |", false);
            execResultWriter("("+ (success ? "SUCCESS" : "FAIL") + ")");
            execResultWriter("======================END=======================");
        }
    }
 
 
    private void logger(Throwable error) {
        textWriter(LOG_DIRECTORY, LOG_FILE_NAME, error.toString(), true);
    }
 
    private void execResult(String input, String expeted, String obtained, int leftIndex, int rightindex) {
        String sl = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer();
        sb.append("Test(" + qtdTests + ") - leftIndex: " + leftIndex + " rightIndex: " + rightindex);
        sb.append(sl + sl);
 
        sb.append("Input:    ");
        sb.append(input);
        sb.append(sl);
 
        sb.append("Expected: ");
        sb.append(expeted);
        sb.append(sl);
 
        sb.append("Obtained: ");
        sb.append(obtained);
        sb.append(sl);
 
        execResultWriter(sb.toString());
    }
 
    private void indexhandler() {
        Integer[] array = arrayGenerator();
        String arrayToString = Arrays.toString(array);
 
        Integer[] assistArray = Arrays.copyOf(array, array.length);
 
        int length = array.length;
        int leftIndex = randomer.nextInt(length == 0 ? 1 : length);
        int rightIndex = randomer.nextInt(length == 0 ? 1 : length);
 
        if (randomer.nextBoolean())
            leftIndex *= -1;
 
        if (randomer.nextBoolean())
            rightIndex *= -1;
 
        try {
            if (length > 0)
                Arrays.sort(assistArray, leftIndex, rightIndex + 1);
        } catch (Throwable erro) {
            logger(erro);
        }
 
        try {
            sorting.sort(array, leftIndex, rightIndex);
            Assert.assertArrayEquals(array, assistArray);
 
        } catch (ArrayComparisonFailure error) {
 
            execResult(arrayToString, Arrays.toString(assistArray), Arrays.toString(array), leftIndex, rightIndex);
            throw error;
 
        } catch (Throwable erro) {
            execResult(arrayToString, Arrays.toString(assistArray), erro.toString(), leftIndex, rightIndex);
            throw erro;
        }
 
    }
 
    @Before
    public void setUp() {
        this.qtdTests = 0;
        randomer = new Random();
        this.sorting = new QuickSort(); //IMPLEMENTATION HERE
    }
 
    private void simpleInput() {
        Integer[] array = arrayGenerator();
        String arrayToString = Arrays.toString(array);
 
        Integer[] assistArray = Arrays.copyOf(array, array.length);
 
        Arrays.sort(assistArray);
        try {
            sorting.sort(array, 0, array.length - 1);
 
            Assert.assertArrayEquals(assistArray, array);
        } catch (ArrayComparisonFailure error) {
 
            execResult(arrayToString, Arrays.toString(assistArray), Arrays.toString(array), 0, array.length - 1);
            throw error;
 
        } catch (Throwable erro) {
            execResult(arrayToString, Arrays.toString(assistArray), erro.toString(), 0, array.length - 1);
            throw erro;
        }
    }
 
 
    private void textWriter(String directoryName, String fileName, String text, boolean nextLine) {
        try {
            File directory = new File(directoryName);
            if (!(directory.exists())) {
                directory.mkdirs();
            }
 
            File file = new File(directory.getPath() + "/" + fileName + ".txt");
            FileWriter outStream = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(outStream);
 
            outStream.write(text + (nextLine ? "\n" : ""));
 
            writer.close();
 
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
 
    }
 
}