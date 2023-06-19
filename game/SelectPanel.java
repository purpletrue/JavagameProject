

//  캐릭터 선택 화면입니다


package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectPanel extends JPanel {
    GameFrame parent;
    int characterType;
    JLabel homeLabel;
    GameFrame gameFrame;

    ImageIcon umjiro = new ImageIcon(getClass().getResource("/res/characterU1.png"));
    ImageIcon yujinko = new ImageIcon(getClass().getResource("/res/characterY1.png"));
    ImageIcon mokgoku = new ImageIcon(getClass().getResource("/res/characterM1.png"));
    ImageIcon enteredUmjiro = new ImageIcon(getClass().getResource("/res/characterU2.png"));
    ImageIcon enteredYujinko = new ImageIcon(getClass().getResource("/res/characterY2.png"));
    ImageIcon enteredMokgoku = new ImageIcon(getClass().getResource("/res/characterM2.png"));

    ImageIcon selectBackgroundImageicon = new ImageIcon(getClass().getResource("/res/background1.png"));
    Image selectBackgroundImage = selectBackgroundImageicon.getImage();

    class SelectButtonClickedEvent extends ButtonClickedEvent {
        private int characterType;

        public SelectButtonClickedEvent(GameFrame parent, int characterType, ImageIcon enteredIcon, ImageIcon presentIcon) {
            super(parent, characterType, enteredIcon, presentIcon);
            this.characterType = characterType;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(characterType + "번 캐릭터 선택됨");
            SelectPanel.this.characterType = characterType;
            Map1Panel game = null;
            try {
                game = new Map1Panel(parent, characterType);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            parent.setContentPane(game);
            parent.validate();
            game.startGameThread();
            parent.requestFocus();
        }
    }

    public SelectPanel(GameFrame parent) {
        super();
        this.parent = parent;
        setLayout(null);
        setBackground(Color.gray);
        setSize(1152, 864);

        JLabel umjiroLabel = new JLabel(umjiro);
        umjiroLabel.setBounds(110, 230, umjiro.getIconWidth(), umjiro.getIconHeight());
        umjiroLabel.addMouseListener(new SelectButtonClickedEvent(parent, 0, enteredUmjiro, umjiro));
        add(umjiroLabel);

        JLabel yujinkoLabel = new JLabel(yujinko);
        yujinkoLabel.setBounds(455, 230, yujinko.getIconWidth(), yujinko.getIconHeight());
        yujinkoLabel.addMouseListener(new SelectButtonClickedEvent(parent, 1, enteredYujinko, yujinko));
        add(yujinkoLabel);

        JLabel mokgokuLabel = new JLabel(mokgoku);
        mokgokuLabel.setBounds(800, 230, mokgoku.getIconWidth(), mokgoku.getIconHeight());
        mokgokuLabel.addMouseListener(new SelectButtonClickedEvent(parent, 2, enteredMokgoku, mokgoku));
        add(mokgokuLabel);

        setVisible(true);
        ImageIcon homeIcon = new ImageIcon(getClass().getResource("/res/home.png")); // 홈 아이콘 이미지 불러오기
        ImageIcon homeIconEntered = new ImageIcon(getClass().getResource("/res/home.png"));

        homeLabel = new JLabel(homeIcon);
        homeLabel.setBounds(1080, 10, homeIcon.getIconWidth(), homeIcon.getIconHeight()); // 홈 아이콘의 위치와 크기 설정
        add(homeLabel);

        // 마우스 이벤트 처리를 위한 클래스를 생성하고 이를 MouseListener로 추가
        ButtonClickedEvent buttonClickedEvent = new ButtonClickedEvent(parent, GameFrame.BEGINNING_PANEL, homeIconEntered, homeIcon);
        homeLabel.addMouseListener(buttonClickedEvent);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(selectBackgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}
