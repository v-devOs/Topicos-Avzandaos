package com.example.todolistapp.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenFile {
    public void openFile(String filename)
    {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
}
