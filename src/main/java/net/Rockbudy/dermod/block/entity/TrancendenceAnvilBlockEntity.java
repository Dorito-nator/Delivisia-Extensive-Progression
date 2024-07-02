package net.Rockbudy.dermod.block.entity;

import net.Rockbudy.dermod.block.custom.TrancendenceAnvil;
import net.Rockbudy.dermod.items.ModItems;
import net.Rockbudy.dermod.screen.TransendanceAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrancendenceAnvilBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(5);
    private static final int MIDDLE_SLOT = 0;
    private static final int TOP_SLOT = 1;
    private static final int RIGHT_SLOT = 2;
    private static final int BOTTOM_SLOT = 3;
    private static final int LEFT_SLOT = 4;

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress =0;
    private int maxProgress = 78;

    public TrancendenceAnvilBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntitys.TRANSENDANCE_ANVIL_BE.get(),pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> TrancendenceAnvilBlockEntity.this.progress;
                    case 1 -> TrancendenceAnvilBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {

                switch (pIndex){
                    case 0 -> TrancendenceAnvilBlockEntity.this.progress = pValue;
                    case 1 -> TrancendenceAnvilBlockEntity.this.maxProgress = pValue;
                };
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }



    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for(int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.addItem(itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);

    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.dermod.transcendence_anvil");
    }


    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new TransendanceAnvilMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemStackHandler.serializeNBT());
        pTag.putInt("transendance_anvil.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("transendance_anvil.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()){
            increaseCraftingProgress();
            setChanged(pLevel,pPos,pState);

            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        }else {
            resetProgress();
        }
    }

    private void craftItem() {
        ItemStack reult = new ItemStack(ModItems.TESLA_ALLOY.get(), 1);
        for(int i = 1; i == 4; i++){
            this.itemStackHandler.extractItem(i, 1, false);
            this.itemStackHandler.setStackInSlot(i, new ItemStack(reult.getItem(),
                    this.itemStackHandler.getStackInSlot(i).getCount() + reult.getCount()));
            }



    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemStackHandler.getStackInSlot(TOP_SLOT).getItem() == ModItems.TESLA_ALLOY.get()
                && this.itemStackHandler.getStackInSlot(RIGHT_SLOT).getItem() == ModItems.TESLA_ALLOY.get()
                && this.itemStackHandler.getStackInSlot(BOTTOM_SLOT).getItem() == ModItems.TESLA_ALLOY.get()
                && this.itemStackHandler.getStackInSlot(LEFT_SLOT).getItem() == ModItems.TESLA_ALLOY.get();

        ItemStack result = new ItemStack(ModItems.TESLA_ALLOY.get());
        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemStackHandler.getStackInSlot(MIDDLE_SLOT).isEmpty()
                || this.itemStackHandler.getStackInSlot(MIDDLE_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemStackHandler.getStackInSlot(MIDDLE_SLOT).getCount() + count <= this.itemStackHandler.getStackInSlot(MIDDLE_SLOT).getMaxStackSize();
    }

    }

