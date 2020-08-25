package vision.voltsofdoom.coresystem.universal.resource.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

/**
 * Does some of the "grunt work" for reading from ZIP files.
 * 
 * @author GenElectrovise
 *
 */
public class ZipFileReader {

	private ZipFile zipFile;
	private JsonObject jsonObject;

	public ZipFileReader(ZipFile zipFile) {
		this.zipFile = zipFile;
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public InputStream getStream(String pathToEntry) throws IOException {
		return zipFile.getInputStream(zipFile.getEntry(pathToEntry));
	}

	/**
	 * Gets a queryable {@link JsonObject} from an {@link InputStream}.
	 * 
	 * @param stream
	 * @return
	 */
	public static JsonObject asJson(InputStream stream) {
		return new Gson().fromJson(new JsonReader(new InputStreamReader(stream)), JsonObject.class);
	}
}