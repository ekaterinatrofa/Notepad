package com.company;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NotepadGUI extends JFrame implements ActionListener {

    /**Text area**/
    public JTextArea textNotepad;
    public JScrollPane scrollPane;
    boolean wordWrapOn = false;

    /**Top menu bar**/
    public JMenuBar menuBar;
    public JMenu fileMenu, editMenu, formatMenu, cipherMenu;

    /**format**/
    public JMenu fontMenu;
    public JMenuItem wrap, fontArial, fontComic, fontTimes;

    /**edit**/
    public JMenuItem undo, redo;

    /**cipher**/
    public JMenuItem encrypt, decrypt;

    public JMenuItem exitMenuItem;

    NotepadFunctionFile file = new NotepadFunctionFile(this);
    NotepadFunctionFormat format = new NotepadFunctionFormat(this);
    NotepadFunctionEdit edit = new NotepadFunctionEdit(this);
    NotepadFunctionCipher cipher = new NotepadFunctionCipher(this);

    UndoManager undoManager = new UndoManager();

    public NotepadGUI() {
        super("Notepad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        initComponents();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createCipherMenu();

        format.selectedFont="Times New Roman";
        format.createFont(14);
        setVisible(true);
    }

    private void initComponents() {
        textNotepad = new JTextArea();
        textNotepad.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textNotepad,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        formatMenu = new JMenu("Format");
        menuBar.add(formatMenu);

        cipherMenu = new JMenu("Caesar Cipher");
        menuBar.add(cipherMenu);

        setJMenuBar(menuBar);
    }

    private void createFileMenu() {
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        newMenuItem.setActionCommand("New");
        fileMenu.add(newMenuItem);

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(this);
        openMenuItem.setActionCommand("Open");
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        saveMenuItem.setActionCommand("Save");
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new JMenuItem("Save as");
        saveAsMenuItem.addActionListener(this);
        saveAsMenuItem.setActionCommand("Save as");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        exitMenuItem.setActionCommand("Exit");
        fileMenu.add(exitMenuItem);
    }

    private void createEditMenu() {
        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        undo.setActionCommand("Undo");
        editMenu.add(undo);

        redo = new JMenuItem("Redo");
        redo.addActionListener(this);
        redo.setActionCommand("Redo");
        editMenu.add(redo);
    }

    private void createFormatMenu() {
        wrap = new JMenuItem("Word wrap: OFF");
        wrap.addActionListener(this);
        wrap.setActionCommand("Word wrap");
        formatMenu.add(wrap);

        fontMenu = new JMenu("Font");
        formatMenu.add(fontMenu);

        fontArial = new JMenuItem("Arial");
        fontArial.addActionListener(this);
        fontArial.setActionCommand("Arial");
        fontMenu.add(fontArial);

        fontComic = new JMenuItem("Comic Sans MS");
        fontComic.addActionListener(this);
        fontComic.setActionCommand("Comic Sans MS");
        fontMenu.add(fontComic);

        fontTimes = new JMenuItem("Times New Roman");
        fontTimes.addActionListener(this);
        fontTimes.setActionCommand("Times New Roman");
        fontMenu.add(fontTimes);

        JMenu fontSizeMenu = new JMenu("Font size");
        formatMenu.add(fontSizeMenu);

        JMenuItem size14 = new JMenuItem("14");
        size14.addActionListener(this);
        size14.setActionCommand("14");
        fontSizeMenu.add(size14);

        JMenuItem size16 = new JMenuItem("16");
        size16.addActionListener(this);
        size16.setActionCommand("16");
        fontSizeMenu.add(size16);

        JMenuItem size18 = new JMenuItem("18");
        size18.addActionListener(this);
        size18.setActionCommand("18");
        fontSizeMenu.add(size18);
    }

    private void createCipherMenu() {
        encrypt = new JMenuItem("Encrypt");
        encrypt.addActionListener(this);
        encrypt.setActionCommand("Encrypt");
        cipherMenu.add(encrypt);

        decrypt = new JMenuItem("Decrypt");
        decrypt.addActionListener(this);
        decrypt.setActionCommand("Decrypt");
        cipherMenu.add(decrypt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.openFile();
                break;
            case "Save":
                file.saveFile();
                break;
            case "Save as":
                file.saveAsFile();
                break;
            case "Exit":
                file.exit();
                break;
            case "Undo":
                edit.undo();
                break;
            case "Redo":
                edit.redo();
                break;
            case "Word wrap":
                format.createWordWrap();
                break;
            case "Arial":
                format.setFont(command);
                break;
            case "Times New Roman":
                format.setFont(command);
                break;
            case "Comic Sans MS":
                format.setFont(command);
                break;
            case "14":
                format.createFont(14);
                break;
            case "16":
                format.createFont(16);
                break;
            case "18":
                format.createFont(18);
                break;
            case "Encrypt":
                cipher.createEncrypt();
                break;
            case "Decrypt":
                cipher.createDecrypt();
                break;
        }
    }
}
