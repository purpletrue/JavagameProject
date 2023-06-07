

// 캐릭터 선택 화면에서 필요한 마우스 클릭시 이벤트입니다.


package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ButtonClickedEvent extends MouseAdapter {
    private GameFrame parent;
    private int selectedMenu;
    private ImageIcon enteredIcon;
    private ImageIcon presentIcon;

    public ButtonClickedEvent(GameFrame parent, int selectedMenu, ImageIcon enteredIcon, ImageIcon presentIcon) {
        this.parent = parent;
        this.selectedMenu = selectedMenu;
        this.enteredIcon = enteredIcon;
        this.presentIcon = presentIcon;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        if (!label.getIcon().equals(enteredIcon)) {
            label.setIcon(enteredIcon);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        if (!label.getIcon().equals(presentIcon)) {
            label.setIcon(presentIcon);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        label.setIcon(presentIcon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        parent.swapPanel(selectedMenu);
    }
}
