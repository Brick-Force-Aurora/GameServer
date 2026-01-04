package de.brickforceaurora.maven;

import static me.lauriichan.maven.sourcemod.api.SourceTransformerUtils.*;

import java.util.List;

import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.NetHandler;
import de.brickforceaurora.gameserver.net.NetHandlerContainer;
import de.brickforceaurora.gameserver.net.PacketHandler;
import me.lauriichan.maven.sourcemod.api.ISourceTransformer;

public final class PacketListenerTransformer implements ISourceTransformer {

    @Override
    public boolean canTransform(JavaSource<?> source) {
        if (!(source instanceof final JavaClassSource classSource)) {
            return false;
        }
        return !classSource.isAbstract() && !classSource.isRecord() && classSource.hasInterface(INetListener.class);
    }

    @Override
    public void transform(JavaSource<?> source) {
        final JavaClassSource clazz = (JavaClassSource) source;

        StringBuilder containerBuilder = new StringBuilder("""
            @Override
            public NetHandlerContainer newContainer() {
                return new NetHandlerContainer(this, new NetHandler[] {
            """);
        int amount = 0;
        for (final MethodSource<JavaClassSource> method : clazz.getMethods()) {
            if (!method.hasAnnotation(PacketHandler.class)
                || !(method.getReturnType().isType(void.class) || method.getReturnType().isType(Void.class))) {
                continue;
            }
            List<ParameterSource<JavaClassSource>> params = method.getParameters();
            if (params.size() != 1) {
                continue;
            }
            Type<JavaClassSource> paramType = params.get(0).getType();
            if (!paramType.isType(NetContext.class) || !paramType.isParameterized()) {
                continue;
            }
            Type<JavaClassSource> packetType = paramType.getTypeArguments().get(0);
            if (amount++ != 0) {
                containerBuilder.append(",");
            }
            containerBuilder.append("\n\t\tnew NetHandler<>(").append(packetType.getQualifiedName()).append(".class, this::")
                .append(method.getName()).append(", ")
                .append(Boolean.parseBoolean(method.getAnnotation(PacketHandler.class).getLiteralValue())).append(')');
        }
        if (amount == 0) {
            return;
        }

        removeMethod(clazz, "newContainer");

        importClass(clazz, NetHandlerContainer.class);
        importClass(clazz, NetHandler.class);

        containerBuilder.append('\n').append("""
                });
            }
            """);
        clazz.addMethod(containerBuilder.toString());
        containerBuilder = null;
    }

}
