package com.cjbooms.testing;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;


public class JapaneseCharactersFileTest {

    private static final String PASSING_FILE = "images/passing/アンドリューは本当に凄いですawesomeだと思います.jpg";
    private static final String FAILING_CHARACTER_FILE = "images/failing/𩸽.jpg";
    private static final String FAILING_MIXED_CHARACTER_FILE = "images/failing/アンドリューは本当に凄いですawesomeだと思います𩸽.jpg";
    private final ClassLoader CLASS_LOADER = getClass().getClassLoader();


    @Test
    public void loadImageWithNoProblemCharacters() throws UnsupportedEncodingException {
        verifyFileExists(PASSING_FILE);
    }



    @Test
    public void loadProblemCharacterImage() {
        verifyFileExists(FAILING_CHARACTER_FILE);
    }



    @Test
    public void loadMultiCharacterImageWithProblemCharacter() {
        verifyFileExists(FAILING_MIXED_CHARACTER_FILE);
    }




    private void verifyFileExists(String relativeFilePath) {
        try {
            System.out.println("Relative File Path is:\n" + relativeFilePath);
            URL fileUrl = CLASS_LOADER.getResource(relativeFilePath);
            System.out.println("File URL is:\n" + fileUrl);
            String decodedFilePath = URLDecoder.decode(fileUrl.getFile(), "UTF-8");
            System.out.println("Decoded File URL is:\n" + decodedFilePath);

            File file = new File(decodedFilePath);

            Assert.assertTrue("File found but exists() failing", file.exists());

        } catch (UnsupportedEncodingException e) {
            Assert.fail("Encoding exception:\n" + e);
        } catch (NullPointerException e) {
            Assert.fail("File not found:\n" + e);
        }

    }

}