

// 메인화면에서 '만든이'를 누르거나 게임이 끝날때 나오는 credit 입니다.


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
            "깃허브주소넣고 할 말 있으면 쓰세요",
            "팀원4: 최진용",
            "팀원3: 임수진",
            "팀원2: 엄세영",
            "팀원1: 김경목",
            "팀 장: 이유진",
            "",
            "<< Project 코딩의 호흡 >>"
    };

    private GameFrame gameFrame;

    public CreditPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        timer = new Timer(30, this);  // delay값 변경으로 스크롤 속도 조절
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("함초롬돋움",Font.BOLD,20));
        FontMetrics fm = g.getFontMetrics();
        for (int i = 0; i < credits.length; i++) {
            int stringWidth = fm.stringWidth(credits[i]);   // 중앙 정렬을 위해서 쓰임
            int x = (getWidth() - stringWidth) / 2;
            g.drawString(credits[i], x, y - 50 * i);  // y값의 숫자 조절해서 문자열 간 간격 조절 가능.
        }
    }

    // y값이 -30 * credits.length 보다 작으면 다시 시작패널로 변경.
    @Override
    public void actionPerformed(ActionEvent e) {
        y--;
        if (y < -30 * credits.length) {
            y = 500;
            gameFrame.swapPanel(GameFrame.BEGINNING_PANEL);
        }
        repaint();
    }
}

