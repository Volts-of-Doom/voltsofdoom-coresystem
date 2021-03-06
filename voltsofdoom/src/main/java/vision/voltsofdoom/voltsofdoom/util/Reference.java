package vision.voltsofdoom.voltsofdoom.util;

import static vision.voltsofdoom.zapbyte.ZapByteReference.*;
import vision.voltsofdoom.zapbyte.ZapByteReference;

/**
 * Holds variables which must be visible to the rest of the game. This primarily includes directory
 * paths. Builds off of {@link ZapByteReference}.
 * 
 * @author GenElectrovise
 *
 */
public class Reference {

  /**
   * Gets the location of the textures folder
   */
  public static String getTexturesDir() {
    return getResources() + "textures" + seperator();
  }

  /**
   * Gets the location of the textures folder
   */
  public static String getAdventuresDir() {
    return getResources() + "adventure" + seperator();
  }

  /**
   * Gets the location of the internal textures folder in a zipped resource pack.
   */
  public static String getTexturePackInternalTextureDir() {
    return "textures" + seperator();
  }

}
