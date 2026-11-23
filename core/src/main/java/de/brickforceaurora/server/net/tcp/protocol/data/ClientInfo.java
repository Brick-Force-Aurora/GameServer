package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.net.tcp.protocol.data.api.IClientInfo;

public record ClientInfo(int id, String name, int xp, int rank) implements IClientInfo {}
