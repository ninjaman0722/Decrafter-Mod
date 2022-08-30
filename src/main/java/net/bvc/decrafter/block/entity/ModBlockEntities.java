package net.bvc.decrafter.block.entity;

import net.bvc.decrafter.Decrafter;
import net.bvc.decrafter.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<DecrafterBlockEntity> DECRAFTER;

    public static void registerBlockEntities() {
        DECRAFTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Decrafter.MOD_ID, "decrafter"),
                FabricBlockEntityTypeBuilder.create(DecrafterBlockEntity::new,
                        ModBlocks.DECRAFTER).build(null));
    }

}
