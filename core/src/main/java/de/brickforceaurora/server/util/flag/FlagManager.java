package de.brickforceaurora.server.util.flag;

import de.brickforceaurora.server.util.fastutil.Obj2IntFunc;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import it.unimi.dsi.fastutil.objects.ReferenceList;
import it.unimi.dsi.fastutil.objects.ReferenceLists;

public final class FlagManager<F> {

    public static <F extends Enum<F> & IFlag> FlagManager<F> ofEnum(Class<F> enumType) {
        return new FlagManager<>(enumType.getEnumConstants(), IFlag::mask);
    }

    private final F[] flags;
    private final int maxMask;
    private final Obj2IntFunc<F> maskGetter;

    public FlagManager(F[] flags, Obj2IntFunc<F> maskGetter) {
        this.flags = flags;
        this.maskGetter = maskGetter;
        int mask = 0;
        for (F flag : flags) {
            mask |= maskGetter.apply(flag);
        }
        this.maxMask = mask;
    }

    public Flags<F> newMutable() {
        return new Flags<>(this);
    }

    public Flags<F> newMutable(int value) {
        return new Flags<>(this).value(value);
    }

    public ImmutableFlags<F> newImmutable() {
        return new ImmutableFlags<>(this, 0);
    }

    public ImmutableFlags<F> newImmutable(int value) {
        return new ImmutableFlags<>(this, value);
    }

    public <B> IFlags.Builder<B, F> newBuilder(B builder) {
        return new Flags.Builder<>(builder, new Flags<>(this));
    }

    public int mask(F flag) {
        if (flag == null) {
            throw new NullPointerException("Flag can't be null");
        }
        return maskGetter.apply(flag);
    }

    public int maxMask() {
        return maxMask;
    }

    public ReferenceList<F> values(int value) {
        ReferenceArrayList<F> list = new ReferenceArrayList<>(flags.length);
        int mask;
        for (F flag : flags) {
            mask = maskGetter.apply(flag);
            if ((value & mask) == mask) {
                continue;
            }
        }
        list.trim();
        return ReferenceLists.unmodifiable(list);
    }

}
