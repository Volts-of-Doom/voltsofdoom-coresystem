package vision.voltsofdoom.astar.world.terrain;

import java.util.function.Supplier;
import vision.voltsofdoom.astar.maths.Difficulties;
import vision.voltsofdoom.astar.world.creature.IAbility;

/**
 * Default {@link ITerrainType}s.
 * 
 * @author GenElectrovise
 *
 */
public class TerrainTypes {

  public static final Supplier<ITerrainType> CLEAR = () -> new ITerrainType() {
    public float getDifficultyForAbility(IAbility ability) {
      return 10;
    }
  };

  public static final Supplier<ITerrainType> IMPASS = () -> new ITerrainType() {
    public float getDifficultyForAbility(IAbility ability) {
      return Difficulties.ALWAYS_IMPOSSIBLE;
    }
  };

}
