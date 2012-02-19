
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class tardisShell extends JFrame {

  String[] titles = { "Tasks", "People", "Tree", "GANTT"};

  JTabbedPane tabbedPane;
  
  
  public tardisShell() {
    super("TARDIS Task Manager");
    tabbedPane = new JTabbedPane() {

    };
    for (int i = 0; i < titles.length; i++) {
      tabbedPane.addTab(titles[i], createPane(titles[i]));
    }
    tabbedPane.setSelectedIndex(0);

    tabbedPane.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        tabbedPane.revalidate();
      }
    });
    getContentPane().add(tabbedPane);
  }

  
  JPanel createPane(String title) {
    JPanel panel = new JPanel();
    JLabel label = new JLabel(title);
    JButton quitButton = new JButton("Quit");
    quitButton.setBounds(50, 60, 80, 30);
    quitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
       }
    });
    label.setOpaque(true);
    panel.add(label);
    panel.add(quitButton);
    return panel;
  }


  
  public static void main(String[] args) {
    JFrame frame = new tardisShell();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(360, 100);
    frame.setVisible(true);
  }
}