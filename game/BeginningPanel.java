// BeginningPanel.java
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeginningPanel extends JPanel {
    private GameFrame parent;
    private ImageIcon start1 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\start1.png");
    private ImageIcon start2 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\start2.png");
    private ImageIcon help1 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\help1.png");
    private ImageIcon help2 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\help2.png");
    private ImageIcon pro1 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\produce1.png");
    private ImageIcon pro2 = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\produce2.png");
    private ImageIcon bgImageicon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\background.png");
    private Image backgroundPanelImage = bgImageicon.getImage();

    public BeginningPanel(GameFrame parent) {
        this.parent = parent;
        setLayout(null);
        // 버튼에 호버시 효과. JButton으로 안 하고 JLabel로 설정
        JLabel startButtonLabel = new JLabel(start1);
        startButtonLabel.setSize(start1.getIconWidth(), start1.getIconHeight());
        startButtonLabel.setLocation(450, 450);
        startButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 0, start2, start1));
        // 게임시작 버튼 누를 시 게임패널로 이동
        startButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Map1Panel map1Panel = Main2.getMap1Panel();
                if (map1Panel != null) {
                    map1Panel.startGameThread();
                }
                parent.swapPanel(0);
            }
        });
        add(startButtonLabel);

        JLabel ruleButtonLabel = new JLabel(help1);
        ruleButtonLabel.setSize(help1.getIconWidth(), help1.getIconHeight());
        ruleButtonLabel.setLocation(450, 550);
        ruleButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 1, help2, help1));
        add(ruleButtonLabel);

        JLabel showLankingButtonLabel = new JLabel(pro1);
        showLankingButtonLabel.setSize(pro1.getIconWidth(), pro1.getIconHeight());
        showLankingButtonLabel.setLocation(450, 650);
        showLankingButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 2, pro2, pro1));
        add(showLankingButtonLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundPanelImage, 200, 0, bgImageicon.getIconWidth(), bgImageicon.getIconHeight(), null);
    }
}
