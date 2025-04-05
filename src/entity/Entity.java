package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {

    public  int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    
    public BufferedImage setup(String imageName, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        File imageFile = new File(imageName + ".png");
        try{
            System.out.println("entrou");
            image = ImageIO.read(imageFile);
            System.out.println("entrou");
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e){
            System.out.println("entrou");
            e.printStackTrace();
        }

        return image;
    }
    
}
