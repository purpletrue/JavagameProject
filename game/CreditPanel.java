package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditPanel extends JPanel implements ActionListener {

    private int y = 500;
    private Timer timer;
    private String[] credits = {
            "감사합니다..",
            "할 말 있으면 쓰세요",
            "팀원4: 최진용 (https://github.com/LikeAyong)",
            "팀원3: 임수진 (https://github.com/Sujin-Lim)",
            "팀원2: 엄세영 (https://github.com/strictworldaflower)",
            "팀원1: 김경목 (https://github.com/kic0412)",
            "팀 장: 이유진 (https://github.com/purpletrue)",
            "",
            "<< Project 코딩의 호흡 >>"
    };

    private GameFrame gameFrame;
    private JLabel homeLabel;

    public CreditPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        timer = new Timer(30, this);  // delay값 변경으로 스크롤 속도 조절
        timer.start();

        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/res/home.png")); // 홈 아이콘 이미지 불러오기
        ImageIcon homeIconEntered = new ImageIcon(getClass().getResource("/res/home.png"));

        setLayout(null);

        int panelWidth = 1152;
        int panelHeight = 864;

        homeLabel = new JLabel(homeIcon);
        homeLabel.setBounds(panelWidth - homeIcon.getIconWidth() - 20, 10, homeIcon.getIconWidth(), homeIcon.getIconHeight()); // 홈 아이콘의 위치와 크기 설정
        add(homeLabel);

        // 마우스 이벤트 처리를 위한 클래스를 생성하고 이를 MouseListener로 추가
        ButtonClickedEvent buttonClickedEvent = new ButtonClickedEvent(gameFrame, GameFrame.BEGINNING_PANEL, homeIconEntered, homeIcon);
        homeLabel.addMouseListener(buttonClickedEvent);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("함초롬돋움", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < credits.length; i++) {
            int stringWidth = fm.stringWidth(credits[i]);
            int x = (getWidth() - stringWidth) / 2;
            int y = this.y - 50 * i;
            g.drawString(credits[i], x, y);
        }
    }

    // y값이 0이면 다시 시작패널로 변경.
    @Override
    public void actionPerformed(ActionEvent e) {
        y--;
        if (y < 0) {
            y = 0;
            timer.stop();
            gameFrame.swapPanel(GameFrame.BEGINNING_PANEL);
        }
        repaint();
    }
}
