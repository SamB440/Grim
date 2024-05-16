package ac.grim.grimac.utils.collisions;

import ac.grim.grimac.player.GrimPlayer;
import ac.grim.grimac.utils.collisions.datatypes.SimpleCollisionBox;
import ac.grim.grimac.utils.data.VectorData;
import ac.grim.grimac.utils.data.packetentity.PacketEntity;
import ac.grim.grimac.utils.math.GrimMath;
import ac.grim.grimac.utils.nmsutil.GetBoundingBox;
import org.bukkit.util.Vector;

public final class EntityPushing {

    public static void pushAwayFrom(GrimPlayer player, VectorData data, PacketEntity entity) {
        final double entityX = entity.getClientPos().getX();
        final double entityZ = entity.getClientPos().getZ();

        // Don't forget - Still need to apply uncertainty for 0.03 and transaction splits!
        final SimpleCollisionBox box = GetBoundingBox.getPacketEntityBoundingBox(player, entityX, entity.getClientPos().getY(), entityZ, entity);

        // Check if true collision
        if (!box.isIntersected(player.boundingBox)) return;

        double e;

        double d = player.lastX - entityX;
        double f = GrimMath.absMax(d, e = player.lastZ - entityZ);
        if (f >= (double)0.01f) {
            f = Math.sqrt(f);
            d /= f;
            e /= f;
            double g = 1.0 / f;
            if (g > 1.0) {
                g = 1.0;
            }
            d *= g;
            e *= g;
            d *= 0.05f;
            e *= 0.05f;

            // Adds to entity itself (ignore)
//            if (!this.hasPassengers() && this.isPushable()) {
//                this.addVelocity(-d, 0.0, -e);
//            }

//            if (!entity.hasPassengers() && entity.isPushable()) {
            if (entity.passengers.isEmpty()) {
                data.vector.add(new Vector(d, 0, e));
            }
//            }
        }
    }
}
