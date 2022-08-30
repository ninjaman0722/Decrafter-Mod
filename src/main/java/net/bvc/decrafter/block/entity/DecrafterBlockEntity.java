package net.bvc.decrafter.block.entity;

import net.bvc.decrafter.screen.DecraftingScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DecrafterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 40;

    public DecrafterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECRAFTER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> DecrafterBlockEntity.this.progress;
                    case 1 -> DecrafterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DecrafterBlockEntity.this.progress = value;
                    case 1 -> DecrafterBlockEntity.this.maxProgress = value;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Decrafter");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DecraftingScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("decrafter.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("decrafter.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, DecrafterBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(DecrafterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(0, 1);

            entity.setStack(1, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(1).getCount() + 1));

            entity.setStack(2, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(2).getCount() + 1));

            entity.setStack(3, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(3).getCount() + 1));

            entity.setStack(4, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(4).getCount() + 1));

            entity.setStack(6, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(6).getCount() + 1));

            entity.setStack(7, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(7).getCount() + 1));

            entity.setStack(8, new ItemStack(Items.REDSTONE,
                    entity.getStack(8).getCount() + 1));

            entity.setStack(9, new ItemStack(Items.COBBLESTONE,
                    entity.getStack(9).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(DecrafterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        return entity.getStack(1).getItem() == Items.DROPPER;
    }

}