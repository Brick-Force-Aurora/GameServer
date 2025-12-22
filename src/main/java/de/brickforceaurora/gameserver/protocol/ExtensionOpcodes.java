package de.brickforceaurora.gameserver.protocol;

public enum ExtensionOpcodes {
    OP_CONNECTED_ACK(1000),
    OP_SLOT_DATA_ACK(1001),
    OP_POST_LOAD_INIT_ACK(1002),
    OP_INVENTORY_REQ(1003),
    OP_INVENTORY_ACK(1004),
    OP_CUSTOM_MESSAGE_ACK(1005),
    OP_DISCONNECT_REQ(1006),
    OP_DISCONNECT_ACK(1007),
    OP_BEGIN_CHUNKED_BUFFER_REQ(1008),
    OP_CHUNKED_BUFFER_REQ(1009),
    OP_END_CHUNKED_BUFFER_REQ(1010),
    OP_BEGIN_CHUNKED_BUFFER_ACK(1011),
    OP_CHUNKED_BUFFER_ACK(1012),
    OP_END_CHUNKED_BUFFER_ACK(1013),
    OP_CHUNKED_BUFFER_THUMBNAIL_REQ(1014),
    OP_RENDEZVOUS_INFO_STEAM_ACK(1015),
    OP_ENTER_STEAM_ACK(1016),
    OP_SLOT_DATA_STEAM_ACK(1017);

    private final int id;

    ExtensionOpcodes(int id) {
        this.id = id;
    }

    public int getOpCode() {
        return id;
    }
}
