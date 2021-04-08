package vision.voltsofdoom.voltsofdoom.universal.resource.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.stream.JsonReader;
import vision.voltsofdoom.voltsofdoom.universal.main.VoltsOfDoomCoreSystem;

/**
 * Does some of the "grunt work" for reading from ZIP files.
 * 
 * @author GenElectrovise
 *
 */
public class ZipFileReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZipFileReader.class);

  private ZipFile zipFile;

  public ZipFileReader(File file) throws ZipException, IOException {
    this(new ZipFile(file));
  }

  public ZipFileReader(ZipFile zipFile) throws ZipException, IOException {
    Objects.requireNonNull(zipFile, () -> "ZipFile cannot be null for ZipFileReader constructor");
    this.zipFile = zipFile;
  }

  public ZipFile getZipFile() {
    return zipFile;
  }

  public InputStream getStream(String pathToEntry) throws IOException, NullPointerException {
    return getStream(pathToEntry, "Unable to reach file " + pathToEntry);
  }

  public InputStream getStream(String pathToEntry, String failureMessage) {

    pathToEntry.replace("\\", "/");

    InputStream stream = null;

    try {
      ZipEntry entry = zipFile.getEntry(pathToEntry);

      // In case of null entry
      if (entry == null) {
        LOGGER.error("ZipEntry null from path. This will result in a nulled stream.");

        // Log entries
        Enumeration<? extends ZipEntry> allEntries = zipFile.entries();
        VoltsOfDoomCoreSystem.easyDebug("ZipFile entries are as follows:");
        while (allEntries.hasMoreElements()) {
          ZipEntry zipEntry = (ZipEntry) allEntries.nextElement();
          VoltsOfDoomCoreSystem.easyDebug("> " + zipEntry.getName());
        }
      }

      stream = zipFile.getInputStream(entry);

    } catch (Exception e) {
      LOGGER.error("Exception whilst fetching stream from ZipFile!");
      e.printStackTrace();
    }

    return stream;
  }

  public static JsonReader asJsonReader(InputStream stream) {
    return new JsonReader(new InputStreamReader(stream));
  }
}
