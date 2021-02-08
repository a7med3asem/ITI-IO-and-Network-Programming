module org.asim {
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.java;
    requires java.rmi;
    requires jdk.naming.dns;
    requires javafx.fxml;
    requires java.naming;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    // add icon pack modules
    requires org.kordamp.ikonli.fontawesome;

    exports Day1.NotepadCharBased;
    exports Day1.NotepadByteBased;
    exports Day1.ReadingWritingConsol;
    exports Day1.Serialization;

    exports Day2.Assignment1;
    exports Day2.Assignment2;
    exports Day2.Demo1TCP.ClientServer;
    exports Day2.Demo1TCP.MultipleClientOneServer;
    exports Day2.Demo2UDP;
    exports Day2.Demo3URLConnection;

    exports Day3.Example1.BuffersExample;
    exports Day3.Example2.ExplicitChannelRead;
    exports Day3.Example3.MappedChannelRead;
    exports Day3.Example4.ExplicitChannelWrite;
    exports Day3.Example5.PathsDemo;
    exports Day3.Example6.AsynchronousClientServer;

    exports Day4.Lab1.HelloRMIApplication.Demo1.ClientSide;
    exports Day4.Lab1.HelloRMIApplication.Demo1.ServerSide;
    exports Day4.Lab1.HelloRMIApplication.Demo2.ClientSide;
    exports Day4.Lab1.HelloRMIApplication.Demo2.ServerSide;
    exports Day4.Lab1.ListAvailableServices.ClientSide;
    exports Day4.Lab1.ListAvailableServices.ServerSide;
    exports Day4.Lab2.Calculator.ClientSide;
    exports Day4.Lab2.Calculator.ServerSide;
    exports Day4.Lab3.RemoteDatabaseApplication.ClientSide;
    exports Day4.Lab3.RemoteDatabaseApplication.Models;
    exports Day4.Lab3.RemoteDatabaseApplication.ServerSide;
    exports Day4.Lab3.RemoteDatabaseApplication.ServerSide.DB;

    exports ChatApplication.ClientSide;
    exports ChatApplication.ServerSide;
    exports ChatApplication.Interfaces;
    exports ChatApplication.Models;

    opens ChatApplication.UI.Controllers to javafx.fxml;
}