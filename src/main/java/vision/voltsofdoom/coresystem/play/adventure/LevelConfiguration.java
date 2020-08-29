package vision.voltsofdoom.coresystem.play.adventure;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vision.voltsofdoom.coresystem.universal.resource.ResourceLocation;

public class LevelConfiguration {
	private ResourceLocation identifier;
	private String displayName;
	private String description;
	private EntityMap entityMap;
	private TileMap tileMap;
	
	public LevelConfiguration withIdentifier(ResourceLocation identifier) {
		this.identifier = identifier;
		return this;
	}
	
	public ResourceLocation getIdentifier() {
		return identifier;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public EntityMap getEntityMap() {
		return entityMap;
	}
	
	public TileMap getTileMap() {
		return tileMap;
	}

	public static LevelConfiguration fromJson(JsonObject json) {
		return new Gson().fromJson(json, LevelConfiguration.class);
	}
}
