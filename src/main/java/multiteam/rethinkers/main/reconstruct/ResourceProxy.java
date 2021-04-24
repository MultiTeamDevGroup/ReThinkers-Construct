/**
 *  package multiteam.rethinkers.main.reconstruct;
 *
 *  import multiteam.rethinkers.ReThinkersConstruct;
 *
 *  import java.io.File;
 *  import java.io.IOException;
 *  import java.io.InputStream;
 *  import java.util.Collection;
 *  import java.util.HashMap;
 *  import java.util.Map;
 *  import java.util.Set;
 *  import java.util.function.Predicate;
 *  import java.util.stream.Collectors;
 *
 *  import javax.annotation.Nonnull;
 *
 *  import com.google.common.base.Supplier;
 *  import com.google.common.collect.ImmutableSet;
 *
 *  import net.minecraft.client.Minecraft;
 *  import net.minecraft.resources.*;
 *  import net.minecraft.resources.ResourcePackInfo.IFactory;
 *  import net.minecraft.util.ResourceLocation;
 *
 *  public class ResourceProxy extends ResourcePack {
 *
 *  private static final String MINECRAFT = "minecraft";
 *  private static final Set<String> RESOURCE_DOMAINS = ImmutableSet.of(MINECRAFT);
 *
 *  private static final String BARE_FORMAT = "assets/" + MINECRAFT + "/%s/%s/%s";
 *  private static final String OVERRIDE_FORMAT = "/assets/" + ReThinkersConstruct.MOD_ID + "/overrides/%s/%s/%s";
 *
 *  private static ResourceProxy instance;
 *
 *  private final Map<String, ResourceOverride> overrides = new HashMap<>();
 *
 *  public static void init() {
 *  instance = new ResourceProxy();
 *
 *  Minecraft mc = Minecraft.getInstance();
 *  mc.getResourcePackRepository().addPackFinder(new IPackFinder() {
 *
 * @Override
 * public <T extends ResourcePackInfo> void loadPacks(Map<String, T> nameToPackMap, IFactory<T> packInfoFactory) {
 * String name = "rethinkers:resourceproxy";
 * T t = (T) ResourcePackInfo.create(name, true, () -> instance, packInfoFactory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.DEFAULT);
 * nameToPackMap.put(name, t);
 * }
 *
 * });
 *  }
 *
 *  public ResourceProxy() {
 *  super(new File("rethinkers"));
 *
 *  addMetadataResource("pack.mcmeta");
 *  addMetadataResource("pack.png");
 *  }
 *
 *  public void addResource(String type, String path, String value, Supplier<Boolean> isEnabled) {
 *  ResourceOverride res = new ResourceOverride(type, path, value, isEnabled);
 *  overrides.put(res.getPathKey(), res);
 *  }
 *
 *  private void addMetadataResource(String path) {
 *  overrides.put(path, new MetadataResourceOverride(path));
 *  }
 *
 *  @Override
 *  protected InputStream getResource(String p_195766_1_) throws IOException {
 *  return null;
 *  }
 *
 *  @Override
 *  protected boolean hasResource(String p_195768_1_) {
 *  return false;
 *  }
 *
 *  @Override
 *  public Collection<ResourceLocation> getResources(ResourcePackType p_225637_1_, String p_225637_2_, String p_225637_3_, int p_225637_4_, Predicate<String> p_225637_5_) {
 *  return null;
 *  }
 *
 *  @Override
 *  public Set<String> getNamespaces(ResourcePackType p_195759_1_) {
 *  return null;
 *  }
 *
 *  @Override
 *  public void close() {
 *
 *  }
 *
 *  private static class ResourceOverride {
 *
 *  protected final String type, path, file;
 *  private final Supplier<Boolean> isEnabled;
 *
 *  public ResourceOverride(String type, String path, String file, Supplier<Boolean> isEnabled) {
 *  this.type = type;
 *  this.path = path;
 *  this.file = file;
 *  this.isEnabled = isEnabled;
 *  }
 *
 *  String getPathKey() {
 *  return String.format(BARE_FORMAT, type, path, file);
 *  }
 *
 *  String getReplacementValue() {
 *  return String.format(OVERRIDE_FORMAT, type, path, file);
 *  }
 *
 *  boolean isEnabled() {
 *  return isEnabled.get();
 *  }
 *
 *  }
 *
 *  private static class MetadataResourceOverride extends ResourceOverride {
 *
 *  public MetadataResourceOverride(String path) {
 *  super("", path, "/proxy" + path, () -> true);
 *  }
 *
 *  @Override
 *  String getPathKey() {
 *  return path;
 *  }
 *
 *  @Override
 *  String getReplacementValue() {
 *  return file;
 *  }
 *
 *  }
 *  }
 **/