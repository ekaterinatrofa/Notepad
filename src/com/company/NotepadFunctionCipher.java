package com.company;

import javax.swing.*;

public class NotepadFunctionCipher {
    NotepadGUI notepadGUI;
    private String textBefore;
    private String textAfter;
    private String dialog;
    private int key;

    public NotepadFunctionCipher(NotepadGUI notepadGUI) {
        this.notepadGUI = notepadGUI;
    }

    public void createEncrypt() {

        textBefore = notepadGUI.textNotepad.getText();
        textBefore = textBefore.toLowerCase();
        textBefore = textBefore.replaceAll("[^a-zA-Z]+", "");

        dialog = JOptionPane.showInputDialog(notepadGUI, "Please enter the key number between - 25 and 25");

        try {

            key = Integer.parseInt(dialog);

            if ((key >= -25) && (key <= 25)) {

                notepadGUI.textNotepad.setText(null);
                char[] chars = textBefore.toCharArray();

                for (int i = 0; i < textBefore.length(); i++) {
                    char c = chars[i];
                    char encrypted = encrypt(c, key);
                    textAfter = Character.toString(encrypted);
                    textAfter.split("\\s");
                    String[] outputArray = {textAfter};

                    for (int index = 0; index < outputArray.length; index++) {
                        notepadGUI.textNotepad.append(outputArray[index]);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(notepadGUI,"The key number must be between -25 and 25!","Error",JOptionPane.WARNING_MESSAGE);
                createEncrypt();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(notepadGUI,"Please enter the key number between -25 and 25","Error",JOptionPane.WARNING_MESSAGE);
            createEncrypt();
        }
    }

    public char encrypt(char c, int key)/***** encrypt Method ******/
    {
        char[] alphabet = new char[26];
        int i = 0;

        for (char ch = 'a'; ch <= 'z'; ++ch)//fills alphabet array with the alphabet
        {
            alphabet[ch - 'a'] = ch;
        }
        while (i < 26)
        {
            if (c == alphabet[i])
                return alphabet[(i + key + 26)%26];
            i++;
        }
        return c;
    }

    public void createDecrypt() {

        textAfter = notepadGUI.textNotepad.getText();
        dialog = JOptionPane.showInputDialog(notepadGUI, "Please enter the key number between - 25 and 25");

        try
        {
            key = Integer.parseInt(dialog);

            if((key >=-25) &&(key<= 25)) {
                notepadGUI.textNotepad.setText(null);
                char[] chars = textAfter.toCharArray();

                for(int i = 0; i < textAfter.length(); i++)
                {
                    char c = chars[i];
                    char decrypted = decrypt(c, key);
                    textBefore = Character.toString(decrypted);
                    notepadGUI.textNotepad.append(textBefore);//Decrypted Output
                }
            }
            else {
                JOptionPane.showMessageDialog(notepadGUI,"The key number must be between -25 and 25!","Error",JOptionPane.WARNING_MESSAGE);
                createDecrypt();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(notepadGUI,"Please enter the key number between -25 and 25!","Error",JOptionPane.WARNING_MESSAGE);
            createDecrypt();
        }
    }

    public char decrypt(char c, int key) {
        char[] alphabet = new char[26];
        int i = 0;

        for(char ch = 'a'; ch <= 'z'; ++ch) {
            alphabet[ch-'a']=ch;//fills the array with the alphabet
        }

        while (i < 26) {
            if (c == alphabet[i])
                return alphabet[(i - key + 26)%26];
            i++;
        }
        return c;
    }
}

