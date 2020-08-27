package vision.voltsofdoom.coresystem.universal.resource.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

import vision.voltsofdoom.coresystem.universal.log.VoltLog;

/**
 * Holds an image for the game, in an expected format, with additional utility
 * methods thrown in for good measure.
 * 
 * @author GenElectrovise
 *
 */
public class VODImage {
	private URL url;
	private BufferedImage image;
	private static final VoltLog LOGGER = new VoltLog(VODImage.class);

	public VODImage(String path) {
		try {
			File f = new File(path);

			URI formattedUri = new URI(f.getAbsolutePath().replace("\\", "/"));
			url = new URL("file://" + formattedUri.getPath());
			LOGGER.debug("URL : " + url.getPath());

			image = ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public URL getUrl() {
		return url;
	}

}
