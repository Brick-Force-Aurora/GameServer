package de.brickforceaurora.gameserver.maps;

import java.io.Serializable;

public record Snipets(
        int cmtSeq,
        String nickNameCmt,
        String cmt,
        byte likeOrDislike
) implements Serializable {
}
