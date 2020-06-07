package com.company;

import java.awt.*;

public class NotepadFunctionFormat {
    NotepadGUI notepadGUI;
    Font arial, comic, times;
    String selectedFont;

    public NotepadFunctionFormat(NotepadGUI notepadGUI) {
        this.notepadGUI = notepadGUI;
    }

    public void createWordWrap() {
        if (notepadGUI.wordWrapOn == false) {
            notepadGUI.wordWrapOn = true;
            notepadGUI.textNotepad.setLineWrap(true);
            notepadGUI.textNotepad.setWrapStyleWord(true);
            notepadGUI.wrap.setText("Word wrap: ON");
        } else if (notepadGUI.wordWrapOn) {
            notepadGUI.wordWrapOn = false;
            notepadGUI.textNotepad.setLineWrap(false);
            notepadGUI.textNotepad.setWrapStyleWord(false);
            notepadGUI.wrap.setText("Word wrap: OFF");
        }
    }


    public void createFont(int size) {
        arial = new Font("Arial", Font.PLAIN, size);
        comic = new Font("Comic Sans MS", Font.PLAIN, size);
        times = new Font("Times New Roman", Font.PLAIN, size);

        setFont(selectedFont);
    }

    public void setFont(String font) {
        selectedFont = font;
        switch (selectedFont) {
            case "Arial":
                notepadGUI.textNotepad.setFont(arial);
                break;
            case "Comic Sans MS":
                notepadGUI.textNotepad.setFont(comic);
                break;
            case "Times New Roman":
                notepadGUI.textNotepad.setFont(times);
                break;
        }
    }
}
