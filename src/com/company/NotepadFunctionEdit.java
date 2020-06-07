package com.company;

public class NotepadFunctionEdit {
    NotepadGUI notepadGUI;

    public NotepadFunctionEdit(NotepadGUI notepadGUI) {
        this.notepadGUI = notepadGUI;
    }

    public void undo() {
        notepadGUI.undoManager.undo();

    }

    public void redo() {
        notepadGUI.undoManager.redo();

    }
}
