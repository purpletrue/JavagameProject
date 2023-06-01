package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


//홈버튼 이미지는 모든 패널이 기본적으로 갖고 있는것이므로
//JPanel을 상속받아 새로운 패널을 작성하여 시작화면패널을 제외한 나머지 패널들은
//해당 패널을 상속받아서 작성하도록 함
public class MyJPanel extends JPanel{
    private GameFrame parent;

    //배경 이미지
    private ImageIcon bgImageicon = new ImageIcon("src/images/background.png");
    private Image backgroundPanelImage = bgImageicon.getImage();

    //홈버튼 이미지
    private ImageIcon homeButtonIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\home.png");
    private ImageIcon homeButtonEnteredIcon = new ImageIcon("D:\\workspace_IntelliJ_IDEA\\codeRed\\codeGreen\\src\\res\\home.png");

    public MyJPanel(GameFrame parent) {
        this.parent = parent; //프레임 래퍼랜스 저장 => swapPanel()을 사용하기 위함

        setSize(1152,864);
        //원하는 위치에 버튼을 붙이기 위해 배치관리자 제거
        setLayout(null);

        //뒤로가기 버튼 => 다시 시작 메뉴로 되돌아가기 위해
        //홈 버튼
        JLabel homeButton = new JLabel(homeButtonIcon);
        homeButton.setSize(homeButtonIcon.getIconWidth(),homeButtonIcon.getIconHeight());
        homeButton.setLocation(1100,20);
        homeButton.addMouseListener(new ButtonClickedEvent(parent,parent.BEGINNING_PANEL,homeButtonEnteredIcon,homeButtonIcon));
        add(homeButton);

        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //그래픽 컴포넌트 설정
        //배경 이미지
        g.drawImage(backgroundPanelImage, 0, 0, bgImageicon.getIconWidth(),bgImageicon.getIconHeight(),null);
    }
}

