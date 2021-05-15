package vision.voltsofdoom.zapbyte.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vision.voltsofdoom.zapbyte.resource.IResourceLocation;

/**
 * An abstract {@link RegistryEntry}. Contains concrete implementations of the methods in
 * {@link IRegistryEntry}. Unless you think that my methods are deeply flawed in some way, best
 * practise is to extend this class if you want to create a new registerable type.
 * 
 * @author GenElectrovise
 *
 * @param <T>
 */
public abstract class RegistryEntry<T extends IRegistryEntry2<T>> implements IRegistryEntry2<T> {

  public IResourceLocation identifier;
  private static final Logger LOGGER = LoggerFactory.getLogger(RegistryEntry.class);

  @Override
  public IResourceLocation getIdentifier() {

    if (identifier == null) {
      LOGGER.warn("Registry entry returning a null identifier! Going on with it, but this may cause issues later!");
    }

    return identifier;
  }

  @Override
  public void setIdentifier(IResourceLocation identifier) {
    if (getIdentifier() != null) {
      throw new IllegalStateException("Cannot assign new identifier ('" + identifier + "') to a RegistryEntry which already has an identifier ('" + getIdentifier() + "')!");
    }

    this.identifier = identifier;
  }

}
