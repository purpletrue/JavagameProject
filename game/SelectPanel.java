package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.ButtonClickedEvent;

public class SelectPanel extends MyJPanel {
    private GameFrame parent;
    private int characterType;

    private ImageIcon sangsangBugiIcon = new ImageIcon("res/character_um.png");
    private ImageIcon hansungNyanIicon = new ImageIcon("res/character_yu.png");
    private ImageIcon kkokkokkukkuIcon = new ImageIcon("res/character_mok.png");

    private ImageIcon enteredBugi = new ImageIcon("res/character_um.png");
    private ImageIcon enteredHansungNyangi = new ImageIcon("res/character_yu.png");
    private ImageIcon enteredKKKK = new ImageIcon("res/character_mok.png");

    private ImageIcon selectBackgroundImageicon = new ImageIcon("src/images/selectBackgroundImage.png");
    private Image selectBackgroundImage = selectBackgroundImageicon.getImage();

    private int selectType;

    private class SelectButtonClickedEvent extends ButtonClickedEvent {
        private int characterType;

        public SelectButtonClickedEvent(GameFrame parent, int characterType, ImageIcon enteredIcon, ImageIcon presentIcon) {
            super(parent, characterType, enteredIcon, presentIcon);
            this.characterType = characterType;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(characterType + "번 캐릭터 선택됨");
            Map1Panel game = new Map1Panel(parent, characterType);
            parent.setContentPane(game);
            parent.validate();
        }
    }

    public SelectPanel(GameFrame parent) {
        super(parent);
        this.parent = parent;
        setLayout(null);
        this.setBackground(Color.gray);
        setSize(1152, 864);

        JLabel sangsangBugiLabel = new JLabel(sangsangBugiIcon);
        sangsangBugiLabel.setSize(sangsangBugiIcon.getIconWidth(), sangsangBugiIcon.getIconHeight());
        sangsangBugiLabel.setLocation(110, 230);
        sangsangBugiLabel.addMouseListener(new SelectButtonClickedEvent(parent, 0, enteredBugi, sangsangBugiIcon));
        add(sangsangBugiLabel);

        JLabel hansungNyanILabel = new JLabel(hansungNyanIicon);
        hansungNyanILabel.setSize(hansungNyanIicon.getIconWidth(), hansungNyanIicon.getIconHeight());
        hansungNyanILabel.setLocation(455, 230);
        hansungNyanILabel.addMouseListener(new SelectButtonClickedEvent(parent, 1, enteredHansungNyangi, hansungNyanIicon));
        add(hansungNyanILabel);

        JLabel kkokkokkukkuLabel = new JLabel(kkokkokkukkuIcon);
        kkokkokkukkuLabel.setSize(kkokkokkukkuIcon.getIconWidth(), kkokkokkukkuIcon.getIconHeight());
        kkokkokkukkuLabel.setLocation(800, 230);
        kkokkokkukkuLabel.addMouseListener(new SelectButtonClickedEvent(parent, 2, enteredKKKK, kkokkokkukkuIcon));
        add(kkokkokkukkuLabel);

        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(selectBackgroundImage, 0, 0, selectBackgroundImageicon.getIconWidth(), selectBackgroundImageicon.getIconHeight(), null);
    }
}
