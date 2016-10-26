package com.cjbooms.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;


public class JapaneseCharactersFileTest {

    private static final String PASSING_FILE = "images/passing/アンドリューは本当に凄いですawesomeだと思います.jpg";
    private static final String FAILING_CHARACTER_FILE = "images/failing/𩸽.jpg";
    private static final String FAILING_MIXED_CHARACTER_FILE = "images/failing/アンドリューは本当に凄いですawesomeだと思います𩸽.jpg";

    private String encodedPassingFile;
    private String encodedFailingCharacterFile;
    private String encodedFailingMixedCharacterFile;

    @Before
    public void setup() throws UnsupportedEncodingException {
        encodedPassingFile = URLEncoder.encode(PASSING_FILE, "UTF-16");
        encodedFailingCharacterFile = URLEncoder.encode(FAILING_CHARACTER_FILE, "UTF-8");
        encodedFailingMixedCharacterFile = URLEncoder.encode(FAILING_MIXED_CHARACTER_FILE, "UTF-8");

    }

    @Test
    public void loadImageWithNoProblemCharacters() throws UnsupportedEncodingException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL fileUrl = classLoader.getResource(new String(PASSING_FILE.getBytes(), "utf-8"));
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

    @Test
    public void loadImageWithNoProblemCharactersEncoded() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL fileUrl = classLoader.getResource(encodedPassingFile);
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

    @Test
    public void loadProblemCharacterImage() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL fileUrl = classLoader.getResource(encodedFailingCharacterFile);
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

    @Test
    public void loadProblemCharacterImageWithCharacterEncoded() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/failing/\uD867\uDE3D.jpg");
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

    @Test
    public void loadMultiCharacterImageWithProblemCharacter() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL fileUrl = classLoader.getResource(encodedFailingMixedCharacterFile);
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

    @Test
    public void loadMultiCharacterImageWithProblemCharacterEncoded() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL fileUrl = classLoader.getResource("images/failing/アンドリューは本当に凄いですawesomeだと思います\uD867\uDE3D.jpg");
        File file = new File(fileUrl.getFile());

        Assert.assertTrue(file.exists());
    }

}