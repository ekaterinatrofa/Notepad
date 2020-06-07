package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class NotepadFunctionFile {
    NotepadGUI notepadGUI;
    private String fileName;
    private String fileAddress;

    public NotepadFunctionFile(NotepadGUI notepadGUI) {
        this.notepadGUI = notepadGUI;
    }

    public void newFile() {
        notepadGUI.textNotepad.setText("");
        notepadGUI.setTitle("New");
        fileName = null;
        fileAddress = null;
    }

    public void openFile() {
        FileDialog fileDialog = new FileDialog(notepadGUI, "Open", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            notepadGUI.setTitle(fileName);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));//address to read the file
            notepadGUI.textNotepad.setText("");
            String line = null;
            while ((line = br.readLine()) != null) {
                notepadGUI.textNotepad.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("FILE NOT OPENED!");
        }
    }

    public void saveFile() {
        if (fileName == null) {
            saveAsFile();
        } else {
            try {
                FileWriter fileWriter = new FileWriter(fileAddress + fileName);
                fileWriter.write(notepadGUI.textNotepad.getText());
                notepadGUI.setTitle(fileName);
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }
    }

    public void saveAsFile() {
        FileDialog fileDialog = new FileDialog(notepadGUI, "Save", FileDialog.SAVE);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getFile();
            fileAddress = fileDialog.getDirectory();
            notepadGUI.setTitle(fileName);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileAddress + fileName);
            fileWriter.write(notepadGUI.textNotepad.getText());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("ERROR!");
        }
    }

    public void exit() {
        String message = "Are you sure?";
        String title = "Exit";
        int reply = JOptionPane.showConfirmDialog(notepadGUI, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}


