package main.noise;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NoiseTest {

	private static final int WIDTH = 512;
	private static final int HEIGHT = 512;
	private static final double FEATURE_SIZE = 50;

	public static void main(String[] args)
		throws IOException {
		
		OpenSimplexNoise noise = new OpenSimplexNoise();
		OpenSimplexNoise noise2 = new OpenSimplexNoise(234);
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				double value = PerlinNoise.noise(3.14,42,7);
				//double value2 = noise2.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
				
				int rgb = 0x010101 * (int)((value + 1) * 127.5);
//				rgb = 0x0279b0; //Grass
//				if (value - value2 > 0.8f) rgb = 0x289600;
//				if (value >= 0.0f) rgb = 0x0279b0; //Grass
//				else if (value >= -0.2f) rgb = 0xfff700;
//				else rgb = 0x289600;
				
				image.setRGB(x, y, rgb);
			}
		}
		ImageIO.write(image, "png", new File("noise.png"));
	}

}
