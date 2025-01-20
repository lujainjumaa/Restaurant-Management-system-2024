package org.example.view;

import javax.swing.border.AbstractBorder;
import java.awt.*;

class RoundedBorder extends AbstractBorder {
    private final int cornerRadius;
    private final Color borderColor;

    public RoundedBorder(int cornerRadius, Color borderColor) {
        this.cornerRadius = cornerRadius;
        this.borderColor = borderColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(6));
        g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, cornerRadius, cornerRadius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(cornerRadius, cornerRadius, cornerRadius, cornerRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = cornerRadius;
        insets.right = cornerRadius;
        insets.top = cornerRadius;
        insets.bottom = cornerRadius;
        return insets;
    }
}
