package de.brickforceaurora.server.match.gamemode;

import me.lauriichan.snowframe.extension.ExtensionPoint;
import me.lauriichan.snowframe.extension.IExtension;

@ExtensionPoint
public interface IGameNetListener<D extends GameData> extends IExtension {

    default GameNetHandlerContainer<D> newContainer() {
        throw new UnsupportedOperationException();
    }

}
