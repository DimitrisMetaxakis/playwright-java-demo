package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageObjects.Examples;

public class CodingExamples extends BaseTest {

    @Test
    void test() {
       String updatedString = new Examples().capitalizeLetters("dimitris");
        Assertions.assertTrue(updatedString.equals("DimitriS"));
    }

    @Test
    void test2(){

        Assertions.assertTrue(new Examples().isAnagram("Anna"));

    }

    @Test
    void test3(){
      Assertions.assertEquals(new Examples().printSomething(9),("foo"));

    }

}
