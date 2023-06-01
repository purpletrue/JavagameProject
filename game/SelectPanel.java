// 캐릭터 선택창 패널입니다.


package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SelectPanel extends MyJPanel {
    private GameFrame parent;
    private int characterType;

//    기본 캐릭터 선택 이미지
    private ImageIcon umjiro = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_um.png");
    private ImageIcon yujinko = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_yu.png");
    private ImageIcon mokgoku = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_mok.png");

//    캐릭터 선택시 강조되는 이미지
    private ImageIcon enteredUmjiro = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_um.png");
    private ImageIcon enteredYujinko = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_yu.png");
    private ImageIcon enteredMokgoku= new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\character_mok.png");

//    배경화면 이미지
    private ImageIcon selectBackgroundImageicon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\background1.png");
    private Image selectBackgroundImage = selectBackgroundImageicon.getImage();

    private int selectType;

//    캐릭터 선택 버튼 클릭 시 실행되는 이벤트 처리를 위한 클래스.
//    선택된 캐릭터 타입 기록, 선택된 캐릭터 타입에 따라 맵 생성과 화면 전환 수행
    private class SelectButtonClickedEvent extends ButtonClickedEvent {
        private int characterType;

        public SelectButtonClickedEvent(GameFrame parent, int characterType, ImageIcon enteredIcon, ImageIcon presentIcon) {
            super(parent, characterType, enteredIcon, presentIcon);
            this.characterType = characterType;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(characterType + "번 캐릭터 선택됨");
            SelectPanel.this.characterType = characterType; // SelectPanel의 characterType 변수 설정
            Map1Panel game = new Map1Panel(parent, characterType);
            parent.setContentPane(game);
            parent.validate();
            game.startGameThread();
            parent.requestFocus();
        }
    }

    public SelectPanel(GameFrame parent) {
        super(parent);
        this.parent = parent;
        setLayout(null);
        this.setBackground(Color.gray);
        setSize(1152, 864);

        JLabel umjiroLabel = new JLabel(umjiro);
        umjiroLabel.setSize(umjiro.getIconWidth(), umjiro.getIconHeight());
        umjiroLabel.setLocation(110, 230);
        umjiroLabel.addMouseListener(new SelectButtonClickedEvent(parent, 0, enteredUmjiro, umjiro));
        add(umjiroLabel);

        JLabel yujinkoLabel = new JLabel(yujinko);
        yujinkoLabel.setSize(yujinko.getIconWidth(), yujinko.getIconHeight());
        yujinkoLabel.setLocation(455, 230);
        yujinkoLabel.addMouseListener(new SelectButtonClickedEvent(parent, 1, enteredYujinko, yujinko));
        add(yujinkoLabel);

        JLabel mokgokuLabel = new JLabel(mokgoku);
        mokgokuLabel.setSize(mokgoku.getIconWidth(), mokgoku.getIconHeight());
        mokgokuLabel.setLocation(800, 230);
        mokgokuLabel.addMouseListener(new SelectButtonClickedEvent(parent, 2, enteredMokgoku, mokgoku));
        add(mokgokuLabel);

        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
//        주석처리된 건 배경이미지 사이즈 그대로 나오게 하게 하는것. 지금 설정된건 이미지를 화면에 꽉 차게 한 것임
//        super.paintComponent(g);
//        g.drawImage(selectBackgroundImage, 0, 0, selectBackgroundImageicon.getIconWidth(), selectBackgroundImageicon.getIconHeight(), null);
        super.paintComponent(g);
        g.drawImage(selectBackgroundImage, 0, 0, getWidth(), getHeight(), null);

    }
}
