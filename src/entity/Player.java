package entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    // Variáveis de imagem do jogador
    BufferedImage up1, down1, left1, right1;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = setup("res/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("res/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("res/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("res/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("res/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("res/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("res/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("res/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Selecionar a imagem com base na direção
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        // Desenhar a imagem na tela
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
