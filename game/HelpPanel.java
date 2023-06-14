

// 시작 화면에서 '도움말' 클릭시 나오는 화면입니다.


package game;


import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
private GameFrame gameFrame;
protected JLabel homeLabel;

    public HelpPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setLayout(null);

        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/res/home.png")); // 홈 아이콘 이미지 불러오기
        ImageIcon homeIconEntered = new ImageIcon(getClass().getResource("/res/home.png")); // 마우스를 올렸을 때의 아이콘도 설정해줍니다. 필요에 따라 다른 아이콘으로 변경 가능합니다.

        homeLabel = new JLabel(homeIcon);
        homeLabel.setBounds(1080, 10, homeIcon.getIconWidth(), homeIcon.getIconHeight()); // 홈 아이콘의 위치와 크기 설정
        add(homeLabel);

        // 마우스 이벤트 처리를 위한 클래스를 생성하고 이를 MouseListener로 추가
        ButtonClickedEvent buttonClickedEvent = new ButtonClickedEvent(gameFrame, GameFrame.BEGINNING_PANEL, homeIconEntered, homeIcon);
        homeLabel.addMouseListener(buttonClickedEvent);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);

    }

    private void drawBackground(Graphics2D g2) {
        ImageIcon backgroundIcon;
        backgroundIcon = new ImageIcon(getClass().getResource("/res/helpbg.png"));
        Image backgroundImage = backgroundIcon.getImage();
        g2.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
    }
    public void returnToBeginningPanel() {
        SwingUtilities.invokeLater(() -> {
            if (gameFrame != null) {
                gameFrame.swapPanel(GameFrame.BEGINNING_PANEL);
            }
        });
    }
    

}
