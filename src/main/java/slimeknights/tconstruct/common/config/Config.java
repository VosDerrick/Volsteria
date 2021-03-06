package slimeknights.tconstruct.common.config;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Logger;

import java.util.List;

import slimeknights.mantle.pulsar.config.ForgeCFG;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.Util;

public final class Config {

  public static ForgeCFG pulseConfig = new ForgeCFG("TinkerModules", "Modules");
  public static Config instance = new Config();
  public static Logger log = Util.getLogger("Config");

  private Config() {
  }


  public static boolean forceRegisterAll = false; // enables all common items, even if their module is not present

  // Tools and general
  public static boolean spawnWithBook = true;
  public static boolean reuseStencil = true;
  public static boolean craftCastableMaterials = false;
  public static boolean chestsKeepInventory = true;
  public static boolean autosmeltlapis = true;
  public static boolean obsidianAlloy = true;
  public static boolean claycasts = true;
  public static boolean castableBricks = true;
  public static boolean leatherDryingRecipe = true;
  public static boolean gravelFlintRecipe = true;
  public static double oreToIngotRatio = 2;
  public static String[] craftingStationBlacklist = new String[] {
      "de.ellpeck.actuallyadditions.mod.tile.TileEntityItemViewer"
  };
  //public static List<String> craftingStationBlacklist = Collections.emptyList();

  // Worldgen
  public static boolean genSlimeIslands = true;
  public static boolean genIslandsInSuperflat = false;
  public static int slimeIslandsRate = 730; // Every x-th chunk will have a slime island. so 1 = every chunk, 100 = every 100th
  public static int magmaIslandsRate = 100; // Every x-th chunk will have a slime island. so 1 = every chunk, 100 = every 100th
  public static int[] slimeIslandBlacklist = new int[]{-1, 1};
  public static boolean slimeIslandsOnlyGenerateInSurfaceWorlds = true;
  public static boolean genCobalt = true;
  public static int cobaltRate = 20; // max. cobalt per chunk
  public static boolean genArdite = true;
  public static int arditeRate = 20; // max. ardite per chunk
  public static boolean genTin = true;
  public static int tinRate = 20;
  public static boolean genAluminum = true;
  public static int aluminumRate = 35;
  public static boolean genPalladium = true;
  public static int palladiumRate = 10;
  public static boolean genPlatinum = true;
  public static int platinumRate = 15;
  public static boolean genCopper = true;
  public static int copperRate = 25;
  public static boolean genSilver = true;
  public static int silverRate = 10;
  public static boolean genChromium = true;
  public static int chromiumRate = 25;
  public static boolean genZinc = true;
  public static int zincRate = 25;
  public static boolean genTitanium = true;
  public static int titaniumRate = 15;
  public static boolean genTungsten = true;
  public static int tungstenRate = 15;
  public static boolean genSulfur = true;
  public static int sulfurRate = 15;
  public static boolean genNickel = true;
  public static int nickelRate = 30;
  public static boolean genLead = true;
  public static int leadRate = 30;


  // Clientside configs
  public static boolean renderTableItems = true;
  public static boolean extraTooltips = true;
  public static boolean listAllTables = true;
  public static boolean listAllMaterials = true;
  public static boolean enableForgeBucketModel = true; // enables the forge bucket model by default
  public static boolean dumpTextureMap = false; // requires debug module

  /* Config File */

  static Configuration configFile;

  static ConfigCategory Modules;
  static ConfigCategory Gameplay;
  static ConfigCategory Worldgen;
  static ConfigCategory ClientSide;

  public static void load(FMLPreInitializationEvent event) {
    configFile = new Configuration(event.getSuggestedConfigurationFile(), "0.1", false);

    MinecraftForge.EVENT_BUS.register(instance);

    syncConfig();
  }

  @SubscribeEvent
  public void update(ConfigChangedEvent.OnConfigChangedEvent event) {
    if(event.getModID().equals(TConstruct.modID)) {
      syncConfig();
    }
  }


  public static boolean syncConfig() {
    Property prop;

    // Modules
    {
      Modules = pulseConfig.getCategory();
      /*
      List<String> propOrder = Lists.newArrayList();
      // convert pulse config to MC compatible config for GUI config
      Modules = new ConfigCategory("modules");
      for(PulseMeta pm : TConstruct.pulseManager.getAllPulseMetadata()) {
        if(pm.isForced()) continue;
        prop = new Property(pm.getId(), pm.isDefaultEnabled() ? "true" : "false", Property.Type.BOOLEAN);
        prop.setValue(pm.isEnabled());
        prop.setRequiresMcRestart(true);
        Modules.put(pm.getId(), prop);
        propOrder.add(prop.getName());
      }
      Modules.setPropertyOrder(propOrder);*/
    }
    // Gameplay
    {
      String cat = "gameplay";
      List<String> propOrder = Lists.newArrayList();
      Gameplay = configFile.getCategory(cat);

      prop = configFile.get(cat, "spawnWithBook", spawnWithBook);
      prop.setComment("Players who enter the world for the first time get a Tinkers' Book");
      spawnWithBook = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "reuseStencils", reuseStencil);
      prop.setComment("Allows to reuse stencils in the stencil table to turn them into other stencils");
      reuseStencil = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "chestsKeepInventory", chestsKeepInventory);
      prop.setComment("Pattern and Part chests keep their inventory when harvested.");
      chestsKeepInventory = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "enableClayCasts", claycasts);
      prop.setComment("Adds single-use clay casts.");
      claycasts = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "allowBrickCasting", castableBricks);
      prop.setComment("Allows the creation of bricks from molten clay");
      castableBricks = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "AutosmeltFortuneInteraction", autosmeltlapis);
      prop.setComment("Fortune increases drops after harvesting a block with autosmelt");
      autosmeltlapis = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "craftCastableMaterials", craftCastableMaterials);
      prop.setComment("Allows to craft all tool parts of all materials in the part builder, including materials that normally have to be cast with a smeltery.");
      craftCastableMaterials = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "registerAllItems", forceRegisterAll);
      prop.setComment("Enables all items, even if the Module needed to obtain them is not active");
      forceRegisterAll = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "obsidianAlloy", obsidianAlloy);
      prop.setComment("Allows the creation of obsidian in the smeltery, using a bucket of lava and water.");
      obsidianAlloy = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "addLeatherDryingRecipe", leatherDryingRecipe);
      prop.setComment("Adds a recipe that allows you to get leather from drying cooked meat");
      leatherDryingRecipe = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "addFlintRecipe", gravelFlintRecipe);
      prop.setComment("Adds a recipe that allows you to craft 3 gravel into a flint");
      gravelFlintRecipe = prop.getBoolean();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "oreToIngotRatio", oreToIngotRatio);
      prop.setComment("Determines the ratio of ore to ingot, or in other words how many ingots you get out of an ore. This ratio applies to all ores (including poor and dense). The ratio can be any decimal, including 1.5 and the like, but can't go below 1. THIS ALSO AFFECTS MELTING TEMPERATURE!");
      prop.setMinValue(1);
      oreToIngotRatio = prop.getDouble();
      prop.setRequiresMcRestart(true);
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "craftingStationBlacklist", craftingStationBlacklist);
      prop.setComment("Blacklist of TE classnames for the crafting station to connect to. Mainly for compatibility.");
      craftingStationBlacklist = prop.getStringList();
      propOrder.add(prop.getName());


    }
    // Worldgen
    {
      String cat = "worldgen";
      List<String> propOrder = Lists.newArrayList();
      Worldgen = configFile.getCategory(cat);

      // Slime Islands
      prop = configFile.get(cat, "generateSlimeIslands", genSlimeIslands);
      prop.setComment("If true slime islands will generate");
      genSlimeIslands = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "generateIslandsInSuperflat", genIslandsInSuperflat);
      prop.setComment("If true slime islands generate in superflat worlds");
      genIslandsInSuperflat = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "slimeIslandRate", slimeIslandsRate);
      prop.setComment("One in every X chunks will contain a slime island");
      slimeIslandsRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "magmaIslandRate", magmaIslandsRate);
      prop.setComment("One in every X chunks will contain a magma island in the nether");
      magmaIslandsRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "slimeIslandBlacklist", slimeIslandBlacklist);
      prop.setComment("Prevents generation of slime islands in the listed dimensions");
      slimeIslandBlacklist = prop.getIntList();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "slimeIslandsOnlyGenerateInSurfaceWorlds", slimeIslandsOnlyGenerateInSurfaceWorlds);
      prop.setComment("If true, slime islands wont generate in dimensions which aren't of type surface. This means they wont generate in modded cave dimensions like the deep dark.");
      slimeIslandsOnlyGenerateInSurfaceWorlds = prop.getBoolean();
      propOrder.add(prop.getName());


      // Surface ore generation
      prop = configFile.get(cat, "genTin", genTin);
      prop.setComment("If true, tin will generate in the surface");
      genTin = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "tinRate", tinRate);
      prop.setComment("Approx Ores per chunk");
      tinRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genAluminum", genAluminum);
      prop.setComment("If true, Aluminum will generate in the surface");
      genAluminum = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "aluminumRate", aluminumRate);
      prop.setComment("Approx Ores per chunk");
      aluminumRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genPalladium", genPalladium);
      prop.setComment("If true, Palladium will generate in the surface");
      genPalladium = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "palladiumRate", palladiumRate);
      prop.setComment("Approx Ores per chunk");
      palladiumRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genPlatinum", genPlatinum);
      prop.setComment("If true, Platinum will generate in the surface");
      genPlatinum = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "platinumRate", platinumRate);
      prop.setComment("Approx Ores per chunk");
      platinumRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genCopper", genCopper);
      prop.setComment("If true, copper will generate in the surface");
      genCopper = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "copperRate", copperRate);
      prop.setComment("Approx Ores per chunk");
      copperRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genSilver", genSilver);
      prop.setComment("If true, silver will generate in the surface");
      genSilver = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "silverRate", silverRate);
      prop.setComment("Approx Ores per chunk");
      silverRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genChromium", genChromium);
      prop.setComment("If true, chromium will generate in the surface");
      genChromium = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "chromiumRate", chromiumRate);
      prop.setComment("Approx Ores per chunk");
      chromiumRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genZinc", genZinc);
      prop.setComment("If true, zinc will generate in the surface");
      genZinc = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "zincRate", zincRate);
      prop.setComment("Approx Ores per chunk");
      zincRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genTitanium", genTitanium);
      prop.setComment("If true, titanium will generate in the surface");
      genTitanium = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "titaniumRate", titaniumRate);
      prop.setComment("Approx Ores per chunk");
      titaniumRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genTungsten", genTungsten);
      prop.setComment("If true, tungsten will generate in the surface");
      genTungsten = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "tungstenRate", tungstenRate);
      prop.setComment("Approx Ores per chunk");
      tungstenRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genSulfur", genTin);
      prop.setComment("If true, sulfur will generate in the surface");
      genSulfur = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "sulfurRate", sulfurRate);
      prop.setComment("Approx Ores per chunk");
      sulfurRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genNickel", genNickel);
      prop.setComment("If true, nickel will generate in the surface");
      genNickel = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "nickelRate", nickelRate);
      prop.setComment("Approx Ores per chunk");
      nickelRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genLead", genLead);
      prop.setComment("If true, lead will generate in the surface");
      genLead = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "leadRate", tinRate);
      prop.setComment("Approx Ores per chunk");
      leadRate = prop.getInt();
      propOrder.add(prop.getName());


      // Nether ore generation
      prop = configFile.get(cat, "genCobalt", genCobalt);
      prop.setComment("If true, cobalt ore will generate in the nether");
      genCobalt = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "genArdite", genArdite);
      prop.setComment("If true, ardite ore will generate in the nether");
      genArdite = prop.getBoolean();
      propOrder.add(prop.getName());


      prop = configFile.get(cat, "cobaltRate", cobaltRate);
      prop.setComment("Approx Ores per chunk");
      cobaltRate = prop.getInt();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "arditeRate", arditeRate);
      arditeRate = prop.getInt();
      propOrder.add(prop.getName());

      Worldgen.setPropertyOrder(propOrder);
    }
    // Clientside
    {
      String cat = "clientside";
      List<String> propOrder = Lists.newArrayList();
      ClientSide = configFile.getCategory(cat);

      // rename renderTableItems to renderInventoryInWorld
      configFile.renameProperty(cat, "renderTableItems", "renderInventoryInWorld");

      prop = configFile.get(cat, "renderInventoryInWorld", renderTableItems);
      prop.setComment("If true all of Tinkers' blocks with contents (tables, basin, drying racks,...) will render their contents in the world");
      renderTableItems = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "extraTooltips", extraTooltips);
      prop.setComment("If true tools will show additional info in their tooltips");
      extraTooltips = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "listAllTables", listAllTables);
      prop.setComment("If true all variants of the different tables will be listed in creative. Set to false to only have the oak variant for all tables.");
      listAllTables = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "listAllMaterials", listAllMaterials);
      prop.setComment("If true all material variants of the different parts, tools,... will be listed in creative. Set to false to only have the first found material for all items (usually wood).");
      listAllMaterials = prop.getBoolean();
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "enableForgeBucketModel", enableForgeBucketModel);
      prop.setComment("If true tools will enable the forge bucket model on startup and then turn itself off. This is only there so that a fresh install gets the buckets turned on by default.");
      enableForgeBucketModel = prop.getBoolean();
      if(enableForgeBucketModel) {
        prop.set(false);
        ForgeModContainer.replaceVanillaBucketModel = true;
        Property forgeProp = ForgeModContainer.getConfig().getCategory(Configuration.CATEGORY_CLIENT).get("replaceVanillaBucketModel");
        if(forgeProp != null) {
          forgeProp.set(true);
          ForgeModContainer.getConfig().save();
        }
      }
      propOrder.add(prop.getName());

      prop = configFile.get(cat, "dumpTextureMap", dumpTextureMap);
      prop.setComment("REQUIRES DEBUG MODULE. Will do nothing if debug module is disabled. If true the texture map will be dumped into the run directory, just like old forge did.");
      dumpTextureMap = prop.getBoolean();
      propOrder.add(prop.getName());

      ClientSide.setPropertyOrder(propOrder);
    }

    // save changes if any
    boolean changed = false;
    if(configFile.hasChanged()) {
      configFile.save();
      changed = true;
    }
    if(pulseConfig.getConfig().hasChanged()) {
      pulseConfig.flush();
      changed = true;
    }
    return changed;
  }
}
