package de.brickforceaurora.server.net;

import me.lauriichan.snowframe.extension.ExtensionPoint;
import me.lauriichan.snowframe.extension.IExtension;

@ExtensionPoint
public interface INetListener extends IExtension {

    default NetHandlerContainer newContainer() {
        throw new UnsupportedOperationException();
    }

}
