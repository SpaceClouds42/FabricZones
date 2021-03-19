package us.spaceclouds42.zones.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import us.spaceclouds42.zones.data.ZoneManager;
import us.spaceclouds42.zones.data.spec.PosD;
import us.spaceclouds42.zones.data.spec.Zone;

@Mixin(FlowableFluid.class)
public class FlowableFluidMixin {
    @Inject(
        method = "canFlow",
        at = @At(
            value = "TAIL"
        ),
        cancellable = true
    )
    private void disallowFlowingBetweenZones(BlockView blockView, BlockPos fluidPos, BlockState fluidBlockState, Direction flowDirection, BlockPos flowTo, BlockState flowToBlockState, FluidState fluidState, Fluid fluid, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() || !(blockView instanceof World))
            return;

        World world = (World) blockView;
        Zone zoneFrom = ZoneManager.INSTANCE.getZone(world, fluidPos);
        Zone zoneTo = ZoneManager.INSTANCE.getZone(world, flowTo);

        if (zoneFrom != zoneTo) {
            cir.setReturnValue(false);
        }
    }
}