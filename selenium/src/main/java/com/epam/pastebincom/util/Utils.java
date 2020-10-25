package com.epam.pastebincom.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utils {

    public static boolean isFilesHaveTheSameContent(File file1, File tempFile) throws IOException {
        Path pathFile1 = file1.toPath();
        Path pathTempFile = tempFile.toPath();

        final long size = Files.size(pathFile1);
        if (size != Files.size(pathTempFile))
            return false;

        try (InputStream is1 = new BufferedInputStream(Files.newInputStream(pathFile1));
             InputStream isTempFile = new BufferedInputStream(Files.newInputStream(pathTempFile))) {
            int data;
            while ((data = is1.read()) != -1)
                if (data != isTempFile.read())
                    return false;
        }
        return true;
    }

    public static File writeTextOfWebElementsToFile(List<WebElement> elements) throws IOException {
        File tempFile = new File("src/test/resources/tempFile.txt");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                for (WebElement element : elements) {
                    if(element.getText().replace("\u00a0", "").trim().isEmpty()) {
                        break;
                    }
                    bw.write(element.getText());
                    bw.newLine();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return tempFile;
    }

    public static boolean isColorOfWebElementsCorrect(List<WebElement> elements){
        for (WebElement element : elements) {
            if (element.getText().trim().startsWith("git")) {
                if (!Color.fromString(element.getCssValue("color")).asHex().equals("#c20cb9")){
                    System.out.println("element "+element.getText()+" has color = "+Color.fromString(element.getCssValue("color")).asHex());
                    return false;
                }
            }
            if (element.getText().trim().startsWith("\"") && element.getText().trim().endsWith("\"")){
                if(!Color.fromString(element.getCssValue("color")).asHex().equals("#ff0000")){
                    System.out.println("element "+element.getText()+" has color = "+Color.fromString(element.getCssValue("color")).asHex());
                    return false;
                }
            }
        }
        return true;
    }

}
