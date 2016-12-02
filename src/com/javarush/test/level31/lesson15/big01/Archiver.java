package com.javarush.test.level31.lesson15.big01;


import com.javarush.test.level31.lesson15.big01.command.Command;
import com.javarush.test.level31.lesson15.big01.command.ExitCommand;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by HMF on 29.11.2016.
 */
public class Archiver
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            System.out.println("������� ������ ���� � ����� ������ (���� ����� �������): ");
            BufferedReader archiveNameReader = new BufferedReader(new InputStreamReader(System.in));
            String archivePath = archiveNameReader.readLine();
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(archivePath));
            System.out.println("������� ���� � �����, ������� ����� ������������ (��� ����� �������): ");
            String archivedFile = archiveNameReader.readLine();
            zipFileManager.createZip(Paths.get(archivedFile));
            new ExitCommand().execute();
        }catch (Exception e){

        }
    }



}
