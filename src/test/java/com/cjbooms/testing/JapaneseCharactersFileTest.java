package com.cjbooms.testing;

import org.junit.Test;

import java.io.File;
import java.net.URL;


public class JapaneseCharactersFileTest {
    @Test
    public void loadPassingImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/passing/アンドリューは本当に凄いですawesomeだと思います.jpg");
        File file = new File(fileUrl.getFile());
    }

    @Test
    public void loadProblemCharacterImageWithCharacterEncoded() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/failing/\uD867\uDE3D.jpg");
        File file = new File(fileUrl.getFile());
    }

    @Test
    public void loadProblemCharacterImage() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/failing/𩸽.jpg");
        File file = new File(fileUrl.getFile());
    }

    @Test
    public void loadMultiCharacterImageWithProblemCharacter() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/failing/アンドリューは本当に凄いですawesomeだと思います𩸽.jpg");
        File file = new File(fileUrl.getFile());
    }

    @Test
    public void loadMultiCharacterImageWithProblemCharsEncoded() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/failing/アンドリューは本当に凄いですawesomeだと思います\uD867\uDE3D.jpg");
        File file = new File(fileUrl.getFile());
    }

}