package com.cjbooms.testing;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;


public class JapaneseCharactersFileTest {

    private static final String PASSING_FILE = "simple/アンドリューは本当に凄いですawesomeだと思います.jpg";
    private static final String EMOJI = "supplementary/😀.jpg";
    private static final String EMOJI_ESCAPED = "supplementary/\uD83D\uDE00.jpg";
    private static final String FAILING_CHARACTER_FILE = "supplementary/𩸽.jpg";
    private static final String FAILING_MIXED_CHARACTER_FILE = "supplementary/アンドリューは本当に凄いですawesomeだと思います𩸽.jpg";
    private final ClassLoader CLASS_LOADER = getClass().getClassLoader();


    @Test
    public void loadImageWithNoProblemCharacters() throws UnsupportedEncodingException {
        File file = loadFile(PASSING_FILE);

        Assert.assertTrue("File found but exists() failing", file.exists());
    }

    @Test
    public void loadImageWithProblemCharacterFileName() {
        File file = loadFile(FAILING_CHARACTER_FILE);

        ifNotOsxCheckIfFileExists(file);
    }

    @Test
    public void loadImageWithProblemCharactersInFileName() {
        File file = loadFile(FAILING_MIXED_CHARACTER_FILE);

        ifNotOsxCheckIfFileExists(file);
    }

    @Test
    public void loadImageWithEmojiAsFileName() {
        File file = loadFile(EMOJI);

        ifNotOsxCheckIfFileExists(file);
    }


    @Test
    public void loadImageWithEscapedEmojiAsFileName() {
        File file = loadFile(EMOJI_ESCAPED);

        ifNotOsxCheckIfFileExists(file);
    }

    private File loadFile(String relativeFilePath) {
        try {
            System.out.println("Relative File Path is: " + relativeFilePath);

            URL fileUrl = CLASS_LOADER.getResource(relativeFilePath);
            System.out.println("File URL is: " + fileUrl);

            String decodedFilePath = URLDecoder.decode(fileUrl.getFile(), "UTF-8");
            System.out.println("Decoded File URL is: " + decodedFilePath);

            return new File(decodedFilePath);

        } catch (UnsupportedEncodingException e) {
            Assert.fail("Encoding exception: " + e);
            throw new IllegalStateException(e);
        } catch (NullPointerException e) {
            Assert.fail("File not found: " + e);
            throw new IllegalStateException(e);
        }
    }

    private void ifNotOsxCheckIfFileExists(File file) {
        if (!SystemUtils.IS_OS_MAC_OSX) {
            Assert.assertTrue("File found but exists() failing", file.exists());
        } else {
            System.out.println("Skipping File Exists check due to JDK MAC OSX bug: http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4733494");
        }
    }


}