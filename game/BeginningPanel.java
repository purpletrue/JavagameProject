

// 시작 화면 입니다


package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeginningPanel extends JPanel {
    private GameFrame parent;
    private ImageIcon start = new ImageIcon(getClass().getResource("/res/start.png"));
    private ImageIcon startH = new ImageIcon(getClass().getResource("../res/startH.png"));
    private ImageIcon help = new ImageIcon(getClass().getResource("../res/help.png"));
    private ImageIcon helpH = new ImageIcon(getClass().getResource("../res/helpH.png"));
    private ImageIcon credit = new ImageIcon(getClass().getResource("../res/credit.png"));
    private ImageIcon creditH = new ImageIcon(getClass().getResource("../res/creditH.png"));
    private ImageIcon bgImageicon = new ImageIcon(getClass().getResource("../res/background.png"));
    private Image backgroundPanelImage = bgImageicon.getImage();

    public BeginningPanel(GameFrame parent) {
        this.parent = parent;
        setLayout(null);
        // 버튼에 호버시 효과. JButton으로 안 하고 JLabel로 설정
        JLabel startButtonLabel = new JLabel(start);
        startButtonLabel.setSize(start.getIconWidth(), start.getIconHeight());
        startButtonLabel.setLocation(450, 450);
        startButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 0, startH, start));
        // 게임시작 버튼 누를 시 게임패널로 이동
        startButtonLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Map1Panel map1Panel = Main.getMap1Panel();
                if (map1Panel != null) {
                    map1Panel.startGameThread();
                }
                parent.swapPanel(0);
            }
        });
        add(startButtonLabel);

        JLabel helpButtonLabel = new JLabel(help);
        helpButtonLabel.setSize(help.getIconWidth(), help.getIconHeight());
        helpButtonLabel.setLocation(450, 550);
        helpButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 1, helpH, help));
        add(helpButtonLabel);

        JLabel showCreditButtonLabel = new JLabel(credit);
        showCreditButtonLabel.setSize(credit.getIconWidth(), credit.getIconHeight());
        showCreditButtonLabel.setLocation(450, 650);
        showCreditButtonLabel.addMouseListener(new ButtonClickedEvent(parent, 2, creditH, credit));
        add(showCreditButtonLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundPanelImage, 200, 0, bgImageicon.getIconWidth(), bgImageicon.getIconHeight(), null);
    }
}