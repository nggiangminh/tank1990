//package jsd.project.test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class MenuPanel extends JPanel {
//    private Image background; // Hình nền menu
//    private Image tank; // Hình ảnh tank
//    private Image tree; // Hình ảnh cây
//    private int yPos = 600; // Vị trí ban đầu bên dưới màn hình
//    private int stopYPos = 300; // Vị trí dừng lại
//    private int direction = -2; // Hướng di chuyển của dòng chữ
//
//    public MenuPanel() {
//        this.setBackground(Color.BLACK);
//        this.setFocusable(true);
//        loadMenuImages();
//        addTimer();
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (yPos <= stopYPos) {
//                    startGame(); // Gọi phương thức bắt đầu game khi nhấp vào
//                }
//            }
//        });
//    }
//
//    private void addTimer() {
//        Timer timer = new Timer(10, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                yPos += direction;
//                if (yPos <= stopYPos) {
//                    direction = 0; // Dừng lại khi đạt vị trí dừng
//                } else if (yPos > getHeight()) {
//                    yPos = getHeight(); // Giới hạn không cho chạy ra ngoài
//                }
//                repaint();
//            }
//        });
//        timer.start();
//    }
//
//    private void loadMenuImages() {
//        // Tải các hình ảnh trực tiếp từ tài nguyên
//        background = new ImageIcon("src/jsd/project/tank90/images/battle_city.png").getImage();
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(background,
//                getWidth() / 2 - background.getWidth(null) / 2,
//                yPos, this);
//        g.setFont(loadFont());
//        g.setColor(Color.WHITE);
//        g.drawString("PlayGame", getWidth() / 2 - 56,
//                yPos + background.getHeight(null) + 50);
//
//        // Nếu đã dừng, vẽ thêm các thành phần menu khác
//        if (yPos <= stopYPos) {
//            drawMenuComponents(g);
//        }
//    }
//
//    private void drawMenuComponents(Graphics g) {
//        g.drawImage(tree, 10, 50, this);
//        g.drawImage(tree, 10, 90, this);
//        g.drawImage(tank, getWidth() / 2 - 90,
//                yPos + background.getHeight(null) + 25, this);
//    }
//
//    private Font loadFont() {
//        // Đặt font cho dòng chữ
//        return new Font("Arial", Font.BOLD, 30);
//    }
//
//    private void startGame() {
//        // Chuyển sang màn hình game (GamePanel)
//        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        frame.remove(this); // Gỡ bỏ menu panel
//        frame.add(new GamePanel()); // Thêm GamePanel vào frame
//        frame.revalidate(); // Cập nhật lại frame
//        frame.repaint(); // Vẽ lại frame
//    }
//}
