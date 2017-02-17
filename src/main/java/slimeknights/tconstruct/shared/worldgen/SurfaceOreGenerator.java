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

// public class ModWorldGen implements IWorldGenerator {

// 	@Override
// 	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
// 		if (world.provider.getDimension() == 0) { // the overworld
// 			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
// 		}
// 	}

// 	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
// 		// generateOre(ModBlocks.oreCopper.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreAluminum.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.orePlatinum.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreSilver.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreChromium.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		generateOre(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.TIN.getMeta()), 5, BlockMatcher.forBlock(Blocks.STONE));
// 		// generateOre(ModBlocks.oreManganese.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreTitanium.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreVanadium.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreLead.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.oreNickel.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 		// generateOre(ModBlocks.orePalladium.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 16, 64, 4 + random.nextInt(4), 6);
// 	}

// 	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
// 		int deltaY = maxY - minY;

// 		for (int i = 0; i < chances; i++) {
// 			BlockPos pos= new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

// 			WorldGenMinable generator = new WorldGenMinable(ore, size);
// 			generator.generate(world, random, pos);
// 		}
// 	}

// }



public class SurfaceOreGenerator implements IWorldGenerator {

  public static SurfaceOreGenerator INSTANCE = new SurfaceOreGenerator();

  public WorldGenMinable tinGen;
  // public WorldGenMinable arditeGen;

  public SurfaceOreGenerator() {
    tinGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.TIN.getMeta()),
                                    5,
                                    BlockMatcher.forBlock(Blocks.STONE));

    // arditeGen = new WorldGenMinable(TinkerCommons.blockOre.getStateFromMeta(BlockOre.OreTypes.ARDITE.getMeta()),
    //                                 5,
    //                                 BlockMatcher.forBlock(Blocks.NETHERRACK));
  }

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider.getDimension() == 0) {
      if(Config.genTin) {
        generateSurfaceOre(tinGen, Config.tinRate, random, chunkX, chunkZ, world);
      }
      // if(Config.genCobalt) {
      //   generateNetherOre(cobaltGen, Config.cobaltRate, random, chunkX, chunkZ, world);
      // }
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
