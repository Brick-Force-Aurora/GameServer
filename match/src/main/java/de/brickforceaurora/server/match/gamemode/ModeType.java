package de.brickforceaurora.server.match.gamemode;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.brickforceaurora.server.net.protocol.data.RoomType;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ModeType {
    
    RoomType value();

}
