package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {

    private View view;
    private HTMLDocument document;

    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }


    public void init() {
        createNewDocument();
    }

    public void resetDocument() {
        if (document != null) {
            //������� � �������� ��������� document ��������� ������ ������� ����� ��������/�������
            document.removeUndoableEditListener(view.getUndoListener());
        }
        //��������� ����� �������� �� ��������� � ����������� ��� ���� document
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        //��������� ������ ��������� ��������� ������
        document.addUndoableEditListener(view.getUndoListener());
        //�������� � ������������� ����� update()
        view.update();
    }

    //�� ����� ���������� ���������� ����� � html ������ � �������� document
    public void setPlainText(String text) {
        //������ ��������
        resetDocument();
        //������ ����� ������ StringReader �� ���� ����������� ������
        StringReader stringReader = new StringReader(text);

        try {
            //������ ����� read() �� ������ HTMLEditorKit, ������� �������� ������ �� ������� � �������� document
            new HTMLEditorKit().read(stringReader, document, 0);

        } catch (Exception e) {
            //��������, ����� ����� �� ����� ����������. �� ���������� ������ ����������
            ExceptionHandler.log(e);
        }
    }

    //�� ������ �������� ����� �� ��������� �� ����� html ������
    public String getPlainText() {
        //������ ������ StringWriter
        StringWriter stringWriter = new StringWriter();
        try {
            //�������� ��� ���������� �� ��������� document � ��������� ������ � ������� ������ write ������ HTMLEditorKit
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());

        } catch (Exception e) {
            //��� ������, ����� �� ������ ������ ����������
            ExceptionHandler.log(e);
        }

        return stringWriter.toString();
    }

    public void createNewDocument() {
        //�������� html ������� � �������������
        view.selectHtmlTab();
        //���������� ������� ��������
        resetDocument();
        //������������� ����� ��������� ����
        view.setTitle("HTML ��������");
        //���������� ������ � Undo ���������
        view.resetUndo();
        //�������� ���������� currentFile
        currentFile = null;

    }

    public void openDocument() {

        // ����� ������ �������� ���������� ������ saveDocumentAs(), � ��� �����, ������� �������� �� ����� �����

        //����������� ������������� �� html �������
        view.selectHtmlTab();
        //��������� ����� ������ ��� ������ ����� JFileChooser
        JFileChooser jFileChooser = new JFileChooser();
        //������������� ��� � �������� ������� ������ HTMLFileFilter
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //���������� ���������� ���� "Save File" ��� ������ �����
        int n = jFileChooser.showOpenDialog(view);

        //����� ���� ������, ����������
        if (n == JFileChooser.APPROVE_OPTION) {
            //���������� ����� �������� currentFile
            currentFile = jFileChooser.getSelectedFile();
            //�������� ��������
            resetDocument();
            //���������� ��� ����� � ��������� � �������������
            view.setTitle(currentFile.getName());

            //������� FileReader, ��������� currentFile
            try (FileReader fileReader = new FileReader(currentFile)) {
                //�������� ������ �� FileReader-� � �������� document � ������� ������� ������
                new HTMLEditorKit().read(fileReader, document, 0);
                //�������� ������
                view.resetUndo();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void saveDocument() {

        // ����� ������ �������� �����, ��� saveDocumentAs(), �� �� ����������� ���� � ������������,
        // � ������������ currentFile. ���� currentFile ����� null, �� �������� ����� saveDocumentAs().

        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
            //����������� ������������� �� html �������
            view.selectHtmlTab();

            //��������� FileWriter �� ���� currentFile
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //������������ ������ �� ��������� document � ������� FileWriter-� ���������� ����, ��� �� ��� ������ � ������ getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {

        //����������� ������������� �� html �������
        view.selectHtmlTab();
        //��������� ����� ������ ��� ������ ����� JFileChooser
        JFileChooser jFileChooser = new JFileChooser();
        //������������� ��� � �������� ������� ������ HTMLFileFilter
        jFileChooser.setFileFilter(new HTMLFileFilter());
        //���������� ���������� ���� "Save File" ��� ������ �����
        int n = jFileChooser.showSaveDialog(view);

        //���� ������������ ���������� ����� �����:
        if (n == JFileChooser.APPROVE_OPTION) {
            //��������� ��������� ���� � ���� currentFile
            currentFile = jFileChooser.getSelectedFile();
            //������������� ��� ����� � �������� ��������� ���� �������������
            view.setTitle(currentFile.getName());

            //��������� FileWriter �� ���� currentFile
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //������������ ������ �� ��������� document � ������� FileWriter-� ���������� ����, ��� �� ��� ������ � ������ getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void exit() {
        System.exit(0);
    }


    //psvm
    public static void main(String[] args) {

        //��������� ������ �������������
        View view = new View();
        //��������� ����������, ��������� �������������
        Controller controller = new Controller(view);
        //������������� � ������������� ����������
        view.setController(controller);
        //���������������� �������������
        view.init();
        //���������������� ����������. ���������� ������ ������������������ ����� �������������
        controller.init();

    }


    //setters and getters
    public HTMLDocument getDocument() {
        return document;
    }


}
