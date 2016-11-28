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
public class View extends JFrame implements ActionListener
{
    private Controller controller;


    //��� ����� ������ � ����� ���������
    private JTabbedPane tabbedPane = new JTabbedPane();
    //��� ����� ��������� ��� ����������� �������������� html
    private JTextPane htmlTextPane = new JTextPane();
    //��� ����� ��������� ��� �������������� html � ���� ������, �� ����� ���������� ��� html (���� � �� ����������)
    private JEditorPane plainTextPane = new JEditorPane();

    @Override
    public void actionPerformed(ActionEvent e)
    {

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

    }

    public void initEditor(){


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


    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() {

    }
}
