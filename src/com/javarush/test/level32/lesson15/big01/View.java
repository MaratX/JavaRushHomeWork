package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HMF on 25.11.2016.
 */
import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener  {

    private Controller controller;

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    //��� ����� ������ � ����� ���������
    private JTabbedPane tabbedPane = new JTabbedPane();
    //��� ����� ��������� ��� ����������� �������������� html
    private JTextPane htmlTextPane = new JTextPane();
    //��� ����� ��������� ��� �������������� html � ���� ������, �� ����� ���������� ��� html (���� � �� ����������)
    private JEditorPane plainTextPane = new JEditorPane();


    //constructor
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
    }



    //methods
    @Override
    public void actionPerformed(ActionEvent e) {
        //������ �� ������� ������� � ������� ������ getActionCommand(). ��� ����� ������� ������.
        //�� ���� ������ �� ������ ������ ����� ����� ���� ������ ������ �������.
        String command = e.getActionCommand();

        switch (command) {

            case "�����":
                controller.createNewDocument();
                break;
            case "�������":
                controller.openDocument();
                break;
            case "���������":
                controller.saveDocument();
                break;
            case "��������� ���...":
                controller.saveDocumentAs();
                break;
            case "�����":
                controller.exit();
                break;
            case "� ���������":
                showAbout();
                break;
        }
    }


    public void init() {
        initGui();
        //��������� ��������� ������� ������ ����
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        //���������� ���� ����
        setVisible(true);

    }

    public void initMenuBar() {
        //��������� ����� ������ ���� JMenuBar. ��� � ����� ���� ������ ����
        JMenuBar jMenuBar = new JMenuBar();
        //� ������� MenuHelper ���������������� ����
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        //��������� � ������� ����� ������ �������� �������� ������ ���� ������ ����, ���������� ����, ��� ��� �� ������ � ������� �������
        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    public void initEditor() {

        //������������� �������� "text/html" � �������� ���� �������� ��� ���������� htmlTextPane
        htmlTextPane.setContentType("text/html");

        //��������� ����� ��������� ��������� JScrollPane �� ���� htmlTextPane
        //��������� ������� � ������ tabbedPane � ������ "HTML" � ����������� �� ����������� ������
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));

        //��������� ����� ��������� ��������� JScrollPane �� ���� plainTextPane
        //��������� ��� ���� ������� � tabbedPane � ������ "�����" � ����������� �� ����������� ������
        tabbedPane.addTab("�����", new JScrollPane(plainTextPane));

        //������������� ���������������� ������ ������ tabbedPane
        tabbedPane.setPreferredSize(new Dimension(800, 600));

        //��������� ������ ������ TabbedPaneChangeListener � ������������� ��� � �������� ��������� ��������� � tabbedPane
        tabbedPane.addChangeListener(new TabbedPaneChangeListener (this));

        //��������� �� ������ ������ �������� �������� ������ ���� ������ � ���������
        getContentPane().add(tabbedPane,BorderLayout.CENTER);


    }

    public void initGui() {
        initMenuBar();
        initEditor();

        pack();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {

        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        //�������� html ������� (������������� �� ���)
        tabbedPane.setSelectedIndex(0);
        //���������� ��� ������ � ������� ������
        resetUndo();
    }

    //���� ����� ����������, ����� ��������� ����� ��������� �������
    public void selectedTabChanged() {
        //����� ������ ���������, ����� ������� ������ ��������� ���������
        //���� ������� ������� � �������� 0 (html �������)
        if (isHtmlTabSelected()) {
            //������ ��� ����� �������� ����� �� plainTextPane � ���������� ��� � ���������� � ������� ������ setPlainText
            controller.setPlainText(plainTextPane.getText());
        }
        //��� ������� ������� � �������� 1 (������� � html �������)
        else {
            //���������� �������� ����� � ����������� � ������� ������ getPlainText() � ���������� ��� � ������ plainTextPane
            plainTextPane.setText(controller.getPlainText());
        }
        //�������� ������
        resetUndo();
    }

    //������ � ������������� ����� update(), ������� ������ �������� �������� � ����������� � ������������� ��� � ������ �������������� htmlTextPane.
    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this, "HTML Editor", "About", JOptionPane.INFORMATION_MESSAGE);
    }



    //������ � ������������� ����� exit(), �� ������ �������� exit() � �����������
    public void exit() {
        controller.exit();
    }

    //setter and getter
    public com.javarush.test.level32.lesson15.big01.Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }
}
