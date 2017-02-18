package slimeknights.tconstruct.shared.worldgen;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockOre;


public class SurfaceOreGenerator implements IWorldGenerator {

  public static SurfaceOreGenerator INSTANCE = new SurfaceOreGenerator();

  public WorldGenMinable tinGen;
  public WorldGenMinable aluminumGen;
  public WorldGenMinable palladiumGen;
  public WorldGenMinable platinumGen;
  public WorldGenMinable silverGen;
  public WorldGenMinable copperGen;
  public WorldGenMinable chromiumGen;
  public WorldGenMinable zincGen;
  public WorldGenMinable titaniumGen;
  public WorldGenMinable tungstenGen;
  public WorldGenMinable sulfurGen;
  public WorldGenMinable nickelGen;
  public WorldGenMinable leadGen;
  // public WorldGenMinable arditeGen;

  public SurfaceOreGenerator() {
    
    tinGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.TIN.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    aluminumGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.ALUMINUM.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    palladiumGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.PALLADIUM.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    platinumGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.PLATINUM.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    copperGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.COPPER.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    silverGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.SILVER.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    chromiumGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.CHROMIUM.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    zincGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.ZINC.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    titaniumGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.TITANIUM.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    tungstenGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.TUNGSTEN.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    sulfurGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.SULFUR.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    nickelGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.NICKEL.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));    
    leadGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.LEAD.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));
  }

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider.getDimension() == 0) {
      if(Config.genTin) {
        generateSurfaceOre(tinGen, Config.tinRate, random, chunkX, chunkZ, world);
      }
       if(Config.genAluminum) {
        generateSurfaceOre(aluminumGen, Config.aluminumRate, random, chunkX, chunkZ, world);
      }
       if(Config.genPalladium) {
        generateSurfaceOre(palladiumGen, Config.palladiumRate, random, chunkX, chunkZ, world);
      }
       if(Config.genPlatinum) {
        generateSurfaceOre(platinumGen, Config.platinumRate, random, chunkX, chunkZ, world);
      }
       if(Config.genCopper) {
        generateSurfaceOre(copperGen, Config.copperRate, random, chunkX, chunkZ, world);
      }
       if(Config.genSilver) {
        generateSurfaceOre(silverGen, Config.silverRate, random, chunkX, chunkZ, world);
      }
       if(Config.genChromium) {
        generateSurfaceOre(tinGen, Config.chromiumRate, random, chunkX, chunkZ, world);
      }
       if(Config.genZinc) {
        generateSurfaceOre(tinGen, Config.zincRate, random, chunkX, chunkZ, world);
      }
       if(Config.genTitanium) {
        generateSurfaceOre(tinGen, Config.titaniumRate, random, chunkX, chunkZ, world);
      }
       if(Config.genTungsten) {
        generateSurfaceOre(tinGen, Config.tungstenRate, random, chunkX, chunkZ, world);
      }
       if(Config.genSulfur) {
        generateSurfaceOre(tinGen, Config.sulfurRate, random, chunkX, chunkZ, world);
      }
       if(Config.genNickel) {
        generateSurfaceOre(tinGen, Config.nickelRate, random, chunkX, chunkZ, world);
      }
       if(Config.genLead) {
        generateSurfaceOre(tinGen, Config.leadRate, random, chunkX, chunkZ, world);
      }
 

    }
  }

  public void generateSurfaceOre(WorldGenMinable gen, int rate, Random random, int chunkX, int chunkZ, World world) {
    BlockPos pos;
    for(int i = 0; i < rate; i += 5) {
      pos = new BlockPos(chunkX * 16, 16, chunkZ * 16);
      pos = pos.add(random.nextInt(2), random.nextInt(64), random.nextInt(2));
      gen.generate(world, random, pos);

      pos = new BlockPos(chunkX * 16, 16, chunkZ * 16);
      pos = pos.add(random.nextInt(2), random.nextInt(64), random.nextInt(2));
      gen.generate(world, random, pos);
    }
  }
}
