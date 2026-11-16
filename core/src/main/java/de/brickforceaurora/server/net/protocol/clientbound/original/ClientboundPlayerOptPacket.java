package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.CommonOption;
import de.brickforceaurora.server.net.protocol.data.GameMode;
import de.brickforceaurora.server.net.protocol.data.QuickPlayOption;
import de.brickforceaurora.server.util.flag.IFlags;

public final class ClientboundPlayerOptPacket implements IClientboundPacket {

    private IFlags<GameMode> modeOptions;
    private QuickPlayOption quickPlayOption;
    private IFlags<CommonOption> commonOptions;

    public ClientboundPlayerOptPacket modeOptions(final IFlags<GameMode> modeOptions) {
        this.modeOptions = modeOptions;
        return this;
    }

    public IFlags<GameMode> modeOptions() {
        return this.modeOptions;
    }

    public ClientboundPlayerOptPacket quickPlayOption(final QuickPlayOption quickPlayOption) {
        this.quickPlayOption = quickPlayOption;
        return this;
    }

    public QuickPlayOption quickPlayOption() {
        return this.quickPlayOption;
    }

    public ClientboundPlayerOptPacket commonOptions(final IFlags<CommonOption> commonOptions) {
        this.commonOptions = commonOptions;
        return this;
    }

    public IFlags<CommonOption> commonOptions() {
        return this.commonOptions;
    }

    @Override
    public int packetId() {
        return 417;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(modeOptions.value());
        buf.writeInt(quickPlayOption.id());
        buf.writeInt(commonOptions.value());
    }
}