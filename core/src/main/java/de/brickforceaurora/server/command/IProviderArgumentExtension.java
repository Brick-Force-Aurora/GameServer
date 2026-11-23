package de.brickforceaurora.server.command;

import me.lauriichan.laylib.command.IProviderArgumentType;
import me.lauriichan.snowframe.extension.ExtensionPoint;
import me.lauriichan.snowframe.extension.IExtension;
import me.lauriichan.snowframe.util.instance.Shared;

/**
 * This should probably be used with {@link Shared}
 */
@ExtensionPoint
public interface IProviderArgumentExtension<E> extends IProviderArgumentType<E>, IExtension {

}
