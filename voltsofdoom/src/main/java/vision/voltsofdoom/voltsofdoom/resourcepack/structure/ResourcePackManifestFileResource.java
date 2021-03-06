package vision.voltsofdoom.voltsofdoom.resourcepack.structure;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Optional;
import org.lwjgl.BufferUtils;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import vision.voltsofdoom.voltsofdoom.resourcepack.JsonObjectResource;

/**
 * Codematic representation of the manifest.json file in a texture pack.
 * 
 * @author GenElectrovise
 *
 */
public class ResourcePackManifestFileResource extends JsonObjectResource {

  /**
   * Internal name -> resource name. i.e. "cobble_tile"="cobble_tile.png"
   */
  private Map<String, String> mappings;

  private Optional<IResourcePack> parentPack = Optional.empty();

  public ResourcePackManifestFileResource(String json, Map<String, String> mappings) {
    super(json);
    this.mappings = mappings;
  }

  public ResourcePackManifestFileResource(JsonObject json, Map<String, String> mappings) {
    super(json);
    this.mappings = mappings;
  }

  @Override
  public ByteBuffer getBytes() {
    byte[] contents = new Gson().toJson(mappings, Map.class).getBytes(); 
    return BufferUtils.createByteBuffer(contents.length).put(contents);
  }

  public Map<String, String> getMappings() {
    return mappings;
  }

  public Optional<IResourcePack> getParentPack() {
    return parentPack;
  }

  /**
   * JSON de/serialisation for {@link ResourcePackManifestFileResource}.
   * 
   * @author GenElectrovise
   *
   */
  public static class Serializer implements JsonSerializer<ResourcePackManifestFileResource>, JsonDeserializer<ResourcePackManifestFileResource> {

    @Override
    public JsonElement serialize(ResourcePackManifestFileResource manifest, Type manifestTypeToken, JsonSerializationContext context) {
      JsonObject object = new JsonObject();

      object.add("mappings", context.serialize(manifest.mappings));

      return object;
    }

    @Override
    public ResourcePackManifestFileResource deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

      if (!(element instanceof JsonObject)) {
        throw new JsonParseException("TexturePackManifests (from manifest.json in a texture pack) must be JsonObjects.");
      }
      JsonObject object = (JsonObject) element;

      Map<String, String> dMappings = context.deserialize(object.get("mappings"), Map.class);

      ResourcePackManifestFileResource manifest = new ResourcePackManifestFileResource(object, dMappings);

      return manifest;
    }

  }

}
