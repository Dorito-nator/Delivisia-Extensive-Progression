package net.Rockbudy.dermod.block.custom;

import net.Rockbudy.dermod.block.entity.ModBlockEntitys;
import net.Rockbudy.dermod.block.entity.TrancendenceAnvilBlockEntity;
import net.Rockbudy.dermod.datagen.loot.ModBlcokLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;



public class TrancendenceAnvil extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(0,0,0,32,16,16);

    public TrancendenceAnvil(Properties pProperties) {
        super(pProperties);
    }

    public VoxelShape setShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext){
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState){

        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof TrancendenceAnvilBlockEntity){
                ((TrancendenceAnvilBlockEntity) blockEntity).drops();
            }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof TrancendenceAnvilBlockEntity){
                NetworkHooks.openScreen(((ServerPlayer) pPlayer),(TrancendenceAnvilBlockEntity)entity, pPos);
            }else {
                throw new IllegalStateException("Container provider is missing");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TrancendenceAnvilBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide){
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntitys.TRANSENDANCE_ANVIL_BE.get(),
                (pLevel1, pPos, pState1, pBLockEntity) -> pBLockEntity.tick(pLevel1, pPos, pState1));
    }

}
