import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnimatedCircleSectorDrawer extends JPanel implements ActionListener {

    private int centerX;
    private int centerY;
    private int radius;
    private double startAngle;
    private double endAngle;
    private Color colorCenter;
    private Color colorOuter;
    private Timer timer;

    private double angleOffset;  // Переменная для анимации

    public AnimatedCircleSectorDrawer() {
        this.centerX = 250;
        this.centerY = 250;
        this.radius = 200;
        this.startAngle = Math.toRadians(45);
        this.endAngle = Math.toRadians(135);
        this.colorCenter = Color.RED;
        this.colorOuter = Color.BLUE;

        this.timer = new Timer(50, this);  // 50 миллисекунд для обновления анимации
        this.angleOffset = 0;
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Очищаем предыдущее изображение
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double currentStartAngle = startAngle + angleOffset;
        double currentEndAngle = endAngle + angleOffset;

        // Рисуем сектор окружности
        g2d.setColor(colorCenter);
        g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                (int) Math.toDegrees(currentStartAngle), (int) Math.toDegrees(currentEndAngle - currentStartAngle));

        g2d.setColor(colorOuter);
        g2d.drawLine(centerX, centerY, (int) (centerX + radius * Math.cos(currentEndAngle)),
                (int) (centerY + radius * Math.sin(currentEndAngle)));

        // Увеличиваем смещение для анимации
        angleOffset += Math.toRadians(1);

        // Проверка на полный оборот (360 градусов)
        if (angleOffset >= 2 * Math.PI)
            angleOffset = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();  // Обновляем анимацию при каждом тике таймера
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated Circle Sector");
        AnimatedCircleSectorDrawer drawer = new AnimatedCircleSectorDrawer();
        frame.add(drawer);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
