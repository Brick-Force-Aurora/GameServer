package de.brickforceaurora.server.command;

import me.lauriichan.laylib.command.IArgumentType;
import me.lauriichan.snowframe.extension.ExtensionPoint;
import me.lauriichan.snowframe.extension.IExtension;

@ExtensionPoint
public interface IArgumentExtension<E> extends IArgumentType<E>, IExtension {

}
