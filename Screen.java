import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class Screen extends JPanel{
  public Screen(){
    JLabel screen = new JLabel("2+4=6");
    screen.setFont(new Font("Calibri", Font.PLAIN, 38));
    this.add(screen);
  }
}
