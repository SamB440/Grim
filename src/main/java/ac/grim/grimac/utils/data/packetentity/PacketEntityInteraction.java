package ac.grim.grimac.utils.data.packetentity;

import ac.grim.grimac.player.GrimPlayer;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;

public class PacketEntityInteraction extends PacketEntity {

    public float width = 1.0f, height = 1.0f;

    public PacketEntityInteraction(GrimPlayer player, double x, double y, double z) {
        super(player, EntityTypes.INTERACTION, x, y, z);
    }
}
